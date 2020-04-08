package com.zjc.demo.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * @author - zjc
 * @Description - 打印系统的全部环境变量
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 15:27
 */
@Component
public class ApplicationStartedEventListener implements SmartApplicationListener {


    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        // 判断事件的类型，只监听ApplicationStartedEvent事件类型
        return aClass == ApplicationStartedEvent.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        // 将ApplicationEvent转换为ApplicationStartedEvent实例
        ApplicationStartedEvent startedEvent = (ApplicationStartedEvent) applicationEvent;
        ConfigurableEnvironment environment = startedEvent.getApplicationContext().getEnvironment();

        // 获取系统环境变量
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();

        Iterator<String> iterator = systemEnvironment.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = systemEnvironment.get(key);
            System.out.println("Key : " + key + " , Value : " + value);
        }
        System.out.println("启动成功了.");

    }
}
