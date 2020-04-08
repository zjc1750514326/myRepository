package com.zjc.demo.listener;

import com.zjc.demo.bean.UserBean;
import com.zjc.demo.event.UserRegisterEvent;
import com.zjc.demo.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 14:59
 */
@Component
@Slf4j
public class RegisterApplicationListener implements SmartApplicationListener {


    /**
     *  该方法返回true&supportsSourceType同样返回true时，才会调用该监听内的onApplicationEvent方法
     * @param aClass 接收到的监听事件类型
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        //只有UserRegisterEvent监听类型才会执行下面逻辑
        return aClass == UserRegisterEvent.class;
    }


    /**
     *  该方法返回true&supportsEventType同样返回true时，才会调用该监听内的onApplicationEvent方法
     * @param sourceType
     * @return
     */
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        //只有在UserService内发布的UserRegisterEvent事件时才会执行下面逻辑
        return sourceType == UserServiceImpl.class;
    }

    /**
     *  supportsEventType & supportsSourceType 两个方法返回true时调用该方法执行业务逻辑
     * @param applicationEvent 具体监听实例，这里是UserRegisterEvent
     */
    @Override
    @Async
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        UserRegisterEvent userRegisterEvent = (UserRegisterEvent)applicationEvent;

        UserBean userBean = userRegisterEvent.getUserBean();

        log.info("实现SmartApplicationListener 方式的监听事件 onApplicationEvent  username: "+userBean.getUsername());

    }


    /**
     *  同步情况下监听执行的顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
