package com.zjc.demo.common;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/9 11:56
 */
public class ExceptionString {

    /***********************************统一异常常数************************************/
    /** 异常等级 错误，告警，提示*/
    public final static String EXCEPTION_LEVEL_ERROR = "UM_EXCEPTION-ERROR-->: ";
    public final static String EXCEPTION_LEVEL_ALARM = "UM_EXCEPTION-ALARM-->: ";
    public final static String EXCEPTION_LEVEL_PROMPT = "UM_EXCEPTION-PROMPT-->: ";

    /** 数据库异常 */
    public final static String SQL_EXCEPTION_STR = "数据库异常";
    /** 空指针异常 */
    public final static String NULL_POINTER_EXCEPTION_STR = "空指针异常";
    /** NULL空实体类异常 */
    public final static String NULL_PARAM_EXCEPTION_STR = "空实体类(null)异常";
    /** 重复异常 */
    public final static String REPEAT_EXCEPTION_STR = "重复异常";

    /***********************************用户相关异常常数************************************/
    /** 查询用户数据异常 */
    public final static String FIND_USERS_EXCEPTION_STR = " 条件查询用户列表失败! 条件 UserParam = ";
    public final static String FIND_USERS_NULL_EXCEPTION_STR = "查询用户空指针 ";
    public final static String FIND_USERS_FAIL_EXCEPTION_STR = "查询用户失败,userId: ";
    public final static String ADD_USERS_FAIL_EXCEPTION_STR = "添加用户失败,user: ";

}
