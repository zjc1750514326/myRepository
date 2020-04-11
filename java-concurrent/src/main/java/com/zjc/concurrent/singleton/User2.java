package com.zjc.concurrent.singleton;

/**
 * @author - zjc
 * @Description - 懒汉模式
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/11 15:11
 */
public class User2 {

    private static User2 user2 = null;

    private User2(){

    }

    public static synchronized User2 getInstance(){
        if (user2 == null){
            user2 = new User2();
        }
        return user2;
    }


    public static void main(String[] args) {
        User2 u1 = User2.getInstance();
        User2 u2 = User2.getInstance();
        System.out.println(u1 == u2);
    }

}
