package com.zjc.demo.configuration;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author - zjc
 * @Description - 异步监听配置
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 14:48
 */
@Configuration
@EnableAsync
public class ListenerAsyncConfiguration implements AsyncConfigurer {

    /**
     * 获取异步线程池执行对象
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {

        /** 使用Spring内置线程池任务对象 */
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        /** 设置线程池参数 */
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();

        return taskExecutor;
    }


    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
