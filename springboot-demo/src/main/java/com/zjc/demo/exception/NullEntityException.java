package com.zjc.demo.exception;

import com.zjc.demo.common.enums.ResultCode;
import lombok.Getter;

/**
 * @author RudeCrab
 * @description 自定义异常
 */
@Getter //只要getter方法，无需setter
public class NullEntityException extends RuntimeException {
    private int code;
    private String msg;

    public NullEntityException() {
        this(ResultCode.NULL_ENTITY.getCode(), "空指针异常");
    }

    public NullEntityException(String msg) {
        this(1003, msg);
    }

    public NullEntityException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
