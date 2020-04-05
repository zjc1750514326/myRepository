package com.zjc.concurrent.chapter01.customthreadpool;

/**
 * @author - zjc
 * @Description - 主要用于存放提交的runnable
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/4 21:27
 */
public interface RunnableQueue {

    // 当有新的任务进来，会先offer到队列中
    void offer(Runnable runnable);

    // 工作线程通过take获取线程
    Runnable take() throws InterruptedException;

    // 获取队列中任务的数量
    int getSize();
}
