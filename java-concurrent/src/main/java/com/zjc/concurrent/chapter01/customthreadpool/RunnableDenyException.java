package com.zjc.concurrent.chapter01.customthreadpool;

/**
 * @author - zjc
 * @Description - 通知任务提交者，任务队列已经无法再提交任务
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/4 22:01
 */
public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String message){
        super(message);
    }
}
