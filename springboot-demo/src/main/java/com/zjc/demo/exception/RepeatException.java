package com.zjc.demo.exception;

import com.zjc.demo.common.enums.ResultCode;
import lombok.Data;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/9 15:32
 */
@Data
public class RepeatException extends RuntimeException {
    private int code;
    private String msg;

    public RepeatException() {
        this(ResultCode.ACCOUNT_REPEAT.getCode(), "重复异常");
    }

    public RepeatException(String msg) {
        this(1004, msg);
    }

    public RepeatException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }


}
