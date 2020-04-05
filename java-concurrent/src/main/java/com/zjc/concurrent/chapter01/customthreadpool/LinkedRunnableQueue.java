package com.zjc.concurrent.chapter01.customthreadpool;

import java.util.LinkedList;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/4 22:24
 */
public class LinkedRunnableQueue implements RunnableQueue {

    // 队列的最大容量，在构造时传入
    private final int limit;

    // 若任务执行的队列已满，则需要拒绝策略
    private final DenyPolicy denyPolicy;

    // 存放任务的队列
    private final LinkedList<Runnable> runnableLinkedList = new LinkedList<>();

    private final ThreadPool threadPool;


    public LinkedRunnableQueue(int limit,DenyPolicy denyPolicy,ThreadPool threadPool){
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {

        synchronized (runnableLinkedList){
            if (runnableLinkedList.size() >= limit){
                // 无法容纳新的队列时，拒绝策略
                denyPolicy.reject(runnable,threadPool);
            }else {
                // 将任务加到队尾，并且唤醒阻塞中的线程
                runnableLinkedList.addLast(runnable);
                runnableLinkedList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException{

        synchronized (runnableLinkedList){
            while (runnableLinkedList.isEmpty()){
                try {
                    // 如果任务中没有可以执行的队列，任务挂起，并且进入等待唤醒（也是等待新的任务到来）
                    runnableLinkedList.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
        }
        // 从队列中头部移除一个任务
        return runnableLinkedList.removeFirst();
    }

    @Override
    public int getSize() {
        synchronized (runnableLinkedList) {
            //返回队列中的任务数量
            return runnableLinkedList.size();
        }
    }
}
