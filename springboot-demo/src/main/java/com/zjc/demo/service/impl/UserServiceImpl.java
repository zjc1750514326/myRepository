package com.zjc.demo.service.impl;

import com.zjc.demo.bean.dbparam.UserParam;
import com.zjc.demo.common.ExceptionString;
import com.zjc.demo.dao.UserDao;
import com.zjc.demo.entity.User;
import com.zjc.demo.event.UserRegisterEvent;
import com.zjc.demo.exception.DBException;
import com.zjc.demo.exception.NullEntityException;
import com.zjc.demo.exception.RepeatException;
import com.zjc.demo.service.UserService;
import com.zjc.demo.vo.PageRecords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
    private UserDao userDao;

    /**
     * 用户注册
     * @param user
     */
    @Override
    public void register(User user){
        //添加数据库
        Integer affectedRows = addUser(user);
        if (affectedRows == 1){
            //发布UserRegisterEvent事件
            applicationContext.publishEvent(new UserRegisterEvent(this,user));
        }
    }

    @Override
    public Integer addUser(User user) {
        // 直接编写业务逻辑
        if (user == null){
            log.info(ExceptionString.EXCEPTION_LEVEL_ALARM+ExceptionString.NULL_PARAM_EXCEPTION_STR);
            throw new NullEntityException();
        }

        // 查询是否存在重名的账号
        User userByUsername = getUserByUsername(user.getAccount());
        if (userByUsername != null){
            log.info(ExceptionString.EXCEPTION_LEVEL_PROMPT+ExceptionString.REPEAT_EXCEPTION_STR+" ,重复账号: "+userByUsername.getAccount());
            throw new RepeatException(user.getAccount()+" 已经存在");
        }

        // 添加数据库 返回添加的用户ID  影响行数affectedRows
        Integer affectedRows;
        try {
            affectedRows = userDao.addUser(user);
        }catch (DataAccessException e){
            log.info(ExceptionString.EXCEPTION_LEVEL_ERROR+ ExceptionString.SQL_EXCEPTION_STR+e.getMessage());
            throw new DBException(ExceptionString.ADD_USERS_FAIL_EXCEPTION_STR+user);
        }
        return affectedRows;
    }

    @Override
    public User getUserById(Integer userId) {

        User user;
        try {
            //...查询数据库 或者第三方接口
            user = userDao.getUserById(userId);
        }catch (DataAccessException e){
            log.info(ExceptionString.EXCEPTION_LEVEL_ERROR+ ExceptionString.SQL_EXCEPTION_STR+e.getMessage());
            throw new DBException(ExceptionString.FIND_USERS_FAIL_EXCEPTION_STR+userId);
        }
        if (user == null){
            return new User();
        }
        return user;
    }

    /**
     * 根据账号查询用户
     * @param username 账号
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    /**
     * 查询所有用户，可根据条件
     * @param userParam
     * @return
     */
    @Override
    public PageRecords<User> getAllUser(UserParam userParam) {
        PageRecords pageRecords = new PageRecords(userParam.getStartIndex(), userParam.getLineSize());
        try {
            /** 设置分页参数 */
            userParam.setStartIndex(((userParam.getPageIndex() - 1) * userParam.getPageSize()));
            userParam.setLineSize(userParam.getPageSize());

            /** 查询数据库用户数据 */
            List<User> allUser = userDao.getAllUser(userParam);

            /** 封装查询结果 */
            pageRecords.setRecords(allUser);
            pageRecords.setTotal(userDao.getAllUserCount(userParam));
        }catch (DataAccessException e){
            log.info(ExceptionString.EXCEPTION_LEVEL_ERROR+ ExceptionString.SQL_EXCEPTION_STR+e.getMessage());
            throw new DBException(ExceptionString.FIND_USERS_EXCEPTION_STR+userParam);
        }catch (NullPointerException e){
            log.info(ExceptionString.EXCEPTION_LEVEL_ERROR+ExceptionString.NULL_POINTER_EXCEPTION_STR);
            e.printStackTrace();
            throw new NullEntityException("getAllUser(UserParam userParam): "+ExceptionString.FIND_USERS_NULL_EXCEPTION_STR);
        }
        return pageRecords;
    }
}
