package com.zjc.concurrent.chapter01.customthreadpool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author - zjc
 * @Description - 初始化线程池
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/4 22:36
 */
public class BaseThreadPool implements ThreadPool {
    //为了不暴露Thread类的方法, 使用私有内部类WorkThread来继承Thread类
    private WorkThread workThread;
    //线程池的基本属性
    private final int initSize;
    private final int maxSize;
    private final int coreSize;
    private int activeCount;
    //线程工厂引用
    private final ThreadFactory threadFactory;
    //队列引用
    private final RunnableQueue runnableQueue;
    //线程池销毁标识
    private volatile boolean isShutdown = false;
    //工作线程的队列, 使用ArrayDeque实现
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();
    //定义了一个默认的拒绝策略
    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DisCardDenyPolicy();
    //定义了一个默认的工厂对象
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;
    private final TimeUnit timeUnit;
    //默认的构造器, 只需要传入初始容量, 最大容量, 核心容量和队列上限
    public BaseThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, queueSize, DEFAULT_THREAD_FACTORY,
                DEFAULT_DENY_POLICY,10, TimeUnit.SECONDS);
    }
    //完整构造器
    public BaseThreadPool(int initSize, int maxSize, int coreSize, int queueSize, ThreadFactory threadFactory,
                          DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.workThread = new WorkThread();
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }
    //线程池的初始化方法, 在构造器中被调用, 用于启动工作线程
    private void init() {
        workThread.start();
        for(int i = 0; i < initSize; i++) {
            newThread();
        }
    }
    //封装了工作线程的启动方法:
    //1. 使用InternalTask封装RunnableQueue对象
    //2. 通过工厂方法制造工作线程并启动
    //3. 工作线程入队, 工作线程队列计数器+1
    private void newThread() {
        InternalTask internalTask = new InternalTask(this.runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }
    //工作线程出队的方法
    private void removeThread() {
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }
    //核心:通过内部类继承Thread方法, 设计了自动扩容的机制.
    //为了防止过快增加到Max容量, 使用continue来退出循环
    private class WorkThread extends Thread{
        @Override
        public void run() {
            while(!isShutdown && !isInterrupted()) {
                try {
                    timeUnit.sleep(keepAliveTime);
                } catch (InterruptedException e) {
                    isShutdown = true;
                    break;
                }
                synchronized (this) {
                    if(isShutdown) {
                        break;
                    }
                    if(runnableQueue.getSize() > 0 && activeCount < coreSize) {
                        for(int i = initSize; i<coreSize;i++) {
                            newThread();
                        }
                        continue;
                    }
                    if(runnableQueue.getSize() > 0 && activeCount < maxSize) {
                        for(int i = coreSize; i<maxSize;i++) {
                            newThread();
                        }
                    }
                    if(runnableQueue.getSize()==0 && activeCount > coreSize) {
                        for(int i = coreSize; i < activeCount; i++) {
                            removeThread();
                        }
                    }

                }
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        //如果线程池已经销毁, 将抛出异常
        if(this.isShutdown) {
            throw new IllegalStateException("the thread pool is destoried");
        }
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized(this) {
            //防止重复销毁
            if(isShutdown) {
                return;
            }
            //重置关闭标识
            isShutdown = true;
            //关闭任务工作线程
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            //关闭线程池的工作线程
            this.workThread.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if(isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if(isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if(isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if(isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return runnableQueue.getSize();
    }

    @Override
    public int getActiveSize() {
        synchronized (this){
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }


    private static class ThreadTask{
        Thread thread;
        InternalTask internalTask;
        public ThreadTask(Thread thread,InternalTask internalTask){
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }
}
