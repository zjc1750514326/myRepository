package com.zjc.demo.exception;

import com.zjc.demo.common.enums.ResultCode;
import lombok.Getter;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

/**
 * @author RudeCrab
 * @description 自定义异常
 */
@Getter //只要getter方法，无需setter
public class DBException extends DataAccessException {
    private int code;
    private String msg;

    public DBException() {
        this(ResultCode.DB_ERROR.getCode(),ResultCode.DB_ERROR.getMsg());
    }

    public DBException(String msg) {
        this(2001, msg);
    }

    public DBException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
