package com.zjc.demo.common.enums;

import lombok.Getter;

/**
 * @author RudeCrab
 * @description 响应码枚举
 */
@Getter
public enum ResultCode {

    SUCCESS(1000, "操作成功"),
    FAILED(1001, "响应失败"),
    VALIDATE_FAILED(1002, "参数校验失败"),
    NULL_ENTITY(1003, "空实体"),

    DB_ERROR(2001,"数据库操作异常"),

    ERROR(5000, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
