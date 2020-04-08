package com.zjc.demo.event;

import com.zjc.demo.bean.UserBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 14:08
 */
@Data
@Slf4j
public class UserRegisterEvent extends ApplicationEvent {

    /** 注册用户对象 */
    private UserBean userBean;

    /**
     * 重写构造函数
     * @param source 发生时间的对象
     * @param userBean 注册用户对象
     */
    public UserRegisterEvent(Object source,UserBean userBean) {
        super(source);
        this.userBean = userBean;
        log.info("注册事件发布");
    }
}
