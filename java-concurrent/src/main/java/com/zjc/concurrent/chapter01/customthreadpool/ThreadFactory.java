package com.zjc.concurrent.chapter01.customthreadpool;

/**
 * @author - zjc
 * @Description - 创建线程工厂
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/4 21:43
 */
@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
