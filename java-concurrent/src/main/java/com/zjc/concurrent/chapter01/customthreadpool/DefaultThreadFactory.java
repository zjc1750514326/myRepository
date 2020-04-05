package com.zjc.concurrent.chapter01.customthreadpool;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultThreadFactory implements ThreadFactory {
     //定义原子类的Integer作为线程组的计数
     private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
     //定义线程组对象
     private static final ThreadGroup group = new ThreadGroup("MyThreadPool-"+ GROUP_COUNTER.getAndDecrement());
     //定义生产的线程计数
     private static final AtomicInteger COUNTER = new AtomicInteger(0);
 
     @Override
     public Thread createThread(Runnable runnable) {
         return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndDecrement());
     }
}