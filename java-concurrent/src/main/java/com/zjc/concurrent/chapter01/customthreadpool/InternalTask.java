package com.zjc.concurrent.chapter01.customthreadpool;

/**
 * @author - zjc
 * @Description - 是Runnable的一个实现，只要用于线程池内部，该类用到RunnableQueue,
 *                然后不断的从queue取出runnable,并运行runnable的run方法
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/4 22:10
 */
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue){
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {

        // 如果当前任务运行，并且没有被中断，则不断的从queue中获取Runnable，然后执行run()方法
        while (running && !Thread.currentThread().isInterrupted()){

            try {
                Runnable take = runnableQueue.take();
                take.run();
            }catch (Exception e){
                running = false;
                break;
            }

        }
    }

    /**
     * 停止当前任务
     */
    public void stop(){
        this.running = false;
    }
}
