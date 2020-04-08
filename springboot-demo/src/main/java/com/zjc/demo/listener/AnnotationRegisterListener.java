package com.zjc.demo.listener;

import com.zjc.demo.bean.UserBean;
import com.zjc.demo.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 14:19
 */
@Component
@Slf4j
public class AnnotationRegisterListener {

    /**
     * 注册监听实现方法 注解EventListener开启事件监听
     *  事件监听是无序的，监听到的事件先后顺序完全随机出现的
     * @param userRegisterEvent
     */
    @EventListener
    @Async
    public void register(UserRegisterEvent userRegisterEvent){

        // 获取注册用户对象
        UserBean userBean = userRegisterEvent.getUserBean();
        //../省略逻辑

        // 输出注册用户信息
        log.info("注解方式监听:  getUsername:  "+userBean.getUsername()+" ,getPassword: "+userBean.getPassword());
    }
}
