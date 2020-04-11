package com.zjc.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 14:14
 */
@Data
@ApiModel("用户")
public class User {
    @ApiModelProperty(value = "用户id",hidden = true)
    private Long id;

    @ApiModelProperty(value = "用户账号")
    @NotNull(message = "用户账号不能为空")
    @Size(min = 6, max = 11, message = "账号长度必须是6-11个字符")
    private String account;

    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "用户密码不能为空")
    @Size(min = 6, max = 11, message = "密码长度必须是6-16个字符")
    private String password;

    @ApiModelProperty(value = "用户邮箱")
    @NotNull(message = "用户邮箱不能为空")
    @Email(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$" ,message = "邮箱格式不正确")
    private String email;


    @ApiModelProperty(value = "删除标志 0未删除 1已删除",hidden = true)
    private Integer deleteFlag;

    @ApiModelProperty(value = "审核标志 0未审核 1已审核",hidden = true)
    private Integer checkFlag;
}
