package com.zjc.demo.configuration;

import com.zjc.demo.common.enums.ResultCode;
import com.zjc.demo.exception.APIException;
import com.zjc.demo.exception.DBException;
import com.zjc.demo.exception.NullEntityException;
import com.zjc.demo.exception.RepeatException;
import com.zjc.demo.vo.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author - zjc
 * @Description - 全局异常处理类
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 14:14
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }

    @ExceptionHandler(NullEntityException.class)
    public ResultVO<String> NullEntityExceptionHandler(NullEntityException e) {
        return new ResultVO<>(ResultCode.NULL_ENTITY, e.getMsg());
    }

    @ExceptionHandler(DBException.class)
    public ResultVO<String> DBExceptionHandler(DBException e) {
        return new ResultVO<>(ResultCode.DB_ERROR, e.getMsg());
    }

    @ExceptionHandler(RepeatException.class)
    public ResultVO<String> RepeatExceptionHandler(RepeatException e) {
        return new ResultVO<>(ResultCode.ACCOUNT_REPEAT, e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

}
