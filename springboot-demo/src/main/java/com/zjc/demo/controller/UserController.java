package com.zjc.demo.controller;

import com.zjc.demo.bean.UserBean;
import com.zjc.demo.bean.dbparam.UserParam;
import com.zjc.demo.entity.User;
import com.zjc.demo.service.UserService;
import com.zjc.demo.vo.PageRecords;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 14:17
 */
@Api(tags = "用户管理层")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册控制方法
     * @param user 用户对象
     * @return
     */
    @ApiOperation("注册")
    @PostMapping(value = "/register")
    public String register(@RequestBody UserBean user) {
        //调用注册业务逻辑
        userService.register(user);
        return "注册成功.";
    }



    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public Integer addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    @ApiOperation("获得所有用户")
    @GetMapping("/getUsers")
    public PageRecords<User> getUserAll(UserParam userParam) {

        return userService.getAllUser(userParam);
    }


    @ApiOperation("获得单个用户根据UserId")
    @GetMapping("/getUserByUserId")
    public User getUserByUserId(@RequestParam Integer userId) {
        return userService.getUserById(userId);
    }
}
