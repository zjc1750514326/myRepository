package com.zjc.demo.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author - zjc
 * @Description - 响应码枚举
 *
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 15:27
 */
@Getter
public enum ResultCode {

    SUCCESS(1000, "操作成功"),
    FAILED(1001, "响应失败"),
    VALIDATE_FAILED(1002, "参数校验失败"),
    NULL_ENTITY(1003, "空实体"),
    ACCOUNT_REPEAT(1004, "重复异常"),

    DB_ERROR(2001,"数据库操作异常"),

    ERROR(5000, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }




}
