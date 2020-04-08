package com.zjc.demo.service.impl;

import com.zjc.demo.bean.UserBean;
import com.zjc.demo.bean.dbparam.UserParam;
import com.zjc.demo.dao.UserDao;
import com.zjc.demo.entity.User;
import com.zjc.demo.event.UserRegisterEvent;
import com.zjc.demo.exception.DBException;
import com.zjc.demo.exception.NullEntityException;
import com.zjc.demo.service.UserService;
import com.zjc.demo.vo.PageRecords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 14:14
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserDao userDao;

    /**
     * 用户注册方法
     * @param userBean
     */
    @Override
    public void register(UserBean userBean){

        //省略其他逻辑.....

        //发布UserRegisterEvent事件
        applicationContext.publishEvent(new UserRegisterEvent(this,userBean));
    }

    @Override
    public Integer addUser(User user) {
        // 直接编写业务逻辑
        if (user == null){
            throw new NullEntityException("传入的 User 实体类为空 ");
        }
        // 添加数据库 返回添加的用户ID  影响行数affectedRows
        Integer userId;
        try {
            userId = userDao.addUser(user);
        }catch (DataAccessException e){
            log.info("----->>>错误信息 mgs: "+e.getMessage());
            throw new DBException("添加user: "+user+" 失败 ");
        }
        log.info("userId:  "+userId);
        return userId;
    }

    @Override
    public User getUserById(Integer userId) {

        User user;
        try {
            //...查询数据库 或者第三方接口
            user = userDao.getUserById(userId);
        }catch (DataAccessException e){
            log.info("----->>>错误信息 mgs: "+e.getMessage());
            throw new DBException(" 查询userId = "+userId+" 的用户失败！ ");
        }
        if (user == null){
            return new User();
        }
        return user;
    }

    @Override
    public PageRecords<User> getAllUser(UserParam userParam) {
        PageRecords pageRecords = new PageRecords(userParam.getPageIndex(), userParam.getPageSize());
        try {
            /** 设置分页查询参数 */
            userParam.setStartIndex(((userParam.getPageIndex() - 1) * userParam.getPageSize()));
            userParam.setLineSize(userParam.getPageSize());

            /** 请求数据库 */
            List<User> allUser = userDao.getAllUser(userParam);

            /** 将结果封装分页实体类 */
            pageRecords.setRecords(allUser);
            pageRecords.setTotal(userDao.getAllUserCount(userParam));
        }catch (DataAccessException e){
            log.info("----->>>错误信息 mgs: "+e.getMessage());
            throw new DBException(" 条件查询用户列表失败! 条件 UserParam = "+userParam);
        }catch (NullPointerException e){
            e.printStackTrace();
            throw new NullEntityException();
        }
        return pageRecords;
    }
}
