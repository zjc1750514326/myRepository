package com.zjc.demo.service;

import com.zjc.demo.bean.UserBean;
import com.zjc.demo.bean.dbparam.UserParam;
import com.zjc.demo.entity.User;
import com.zjc.demo.vo.PageRecords;


public interface UserService {

    /**
     * 注册用户
     * @param userBean
     */
    void register(UserBean userBean);


    /**
     *
     * @param user 用户对象
     * @return 成功则返回"Integer"，返回影响的行数
     */
    Integer addUser(User user);

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 查询所有用户
     * @return
     */
    PageRecords<User> getAllUser(UserParam userParam);
}
