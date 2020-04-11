package com.zjc.concurrent.singleton;

/**
 * @author - zjc
 * @Description - 饿汉式
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/11 15:11
 */
public class User1 {

    private static User1 user1 = new User1();

    private User1(){

    }

    public static User1 getInstance(){
        return user1;
    }


    public static void main(String[] args) {
        User1 u1 = User1.getInstance();
        User1 u2 = User1.getInstance();
        System.out.println(u1 == u2);
    }

}
