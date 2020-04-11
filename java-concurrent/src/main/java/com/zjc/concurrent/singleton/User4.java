package com.zjc.concurrent.singleton;

/**
 * @author - zjc
 * @Description - 静态单例模式
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/11 15:11
 */
public class User4 {

    private static class StaticInnerSingleton{
        private static User4 user4 = new User4();
    }

    private User4(){

    }

    public static synchronized User4 getInstance(){

        return StaticInnerSingleton.user4;
    }


    public static void main(String[] args) {
        User4 u1 = User4.getInstance();
        User4 u2 = User4.getInstance();
        System.out.println(u1 == u2);
    }

}
