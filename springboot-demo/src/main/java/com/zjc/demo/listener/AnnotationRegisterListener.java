package com.zjc.demo.listener;

import com.zjc.demo.bean.UserBean;
import com.zjc.demo.entity.User;
import com.zjc.demo.event.UserRegisterEvent;
import com.zjc.demo.exception.NullEntityException;
import com.zjc.demo.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    @Autowired
    IMailService iMailService;

    /**
     * 注册监听实现方法 注解EventListener开启事件监听
     *  事件监听是无序的，监听到的事件先后顺序完全随机出现的
     * @param userRegisterEvent
     */
    @EventListener
    @Async
    public void register(UserRegisterEvent userRegisterEvent){
        // 获取注册用户对象
        User user = userRegisterEvent.getUser();
        if (user == null){
            throw new NullEntityException("注册的用户为空");
        }
        // 发送邮件
        iMailService.sendSimpleMail(user.getEmail(),"注册成功",
                "您好，你已成功注册XX平台，请保存重要信息。\n"
                        +"账号："+user.getAccount()+"\n 密码："+user.getPassword());
        // 输出注册用户信息
        log.info("邮件发送成功,注解方式监听:  getUsername:  "+user.getAccount()+" ,getPassword: "+user.getPassword());

    }
}
