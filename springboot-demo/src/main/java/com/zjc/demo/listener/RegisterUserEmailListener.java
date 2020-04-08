package com.zjc.demo.listener;

import com.zjc.demo.bean.UserBean;
import com.zjc.demo.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author - zjc
 * @Description - 监听注册时发送邮件
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 14:38
 */
@Component
@Slf4j
public class RegisterUserEmailListener {


    /**
     * 监听到注册时发送邮件
     * @param userRegisterEvent
     */
    @EventListener
    @Async
    public void sendMail(UserRegisterEvent userRegisterEvent){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserBean userBean = userRegisterEvent.getUserBean();
        log.info("注解方式监听:  "+userBean.getUsername()+" 注册成功, sendMail .... 发送邮件");
    }
}
