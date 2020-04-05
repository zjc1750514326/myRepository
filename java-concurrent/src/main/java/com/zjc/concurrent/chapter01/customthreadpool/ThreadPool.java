package com.zjc.concurrent.chapter01.customthreadpool;

/**
 * @author - zjc
 * @Description - 定义线程池具备的基本操作方法
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/4 21:23
 */
public interface ThreadPool {

    // 提交任务到线程池
    void execute(Runnable runnable);

    // 关闭线程池
    void shutdown();

    // 获取线程池的初始化大小
    int getInitSize();

    // 获取线程池的最大线程数
    int getMaxSize();

    // 获取线程池的核心线程数
    int getCoreSize();

    // 获取线程池中缓存任务队列的大小
    int getQueueSize();

    // 获取线程池中活跃的线程数
    int getActiveSize();

    // 查看线程池
    // 是否已经被shutdown
    boolean isShutdown();
}
