package com.zjc.demo.service;

import com.zjc.demo.bean.dbparam.UserParam;
import com.zjc.demo.entity.User;
import com.zjc.demo.vo.PageRecords;


public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    void register(User user);


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
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    PageRecords<User> getAllUser(UserParam userParam);
}
