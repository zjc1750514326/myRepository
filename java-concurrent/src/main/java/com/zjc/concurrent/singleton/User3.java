package com.zjc.concurrent.singleton;

/**
 * @author - zjc
 * @Description - 双重检验锁，弊端是JVM会进行重排序，导致多次创建对象。非安全
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/11 15:11
 */
public class User3 {

    private static User3 user2 = null;

    private User3(){

    }

    public static synchronized User3 getInstance(){
        if (user2 == null){
            synchronized (User3.class){
                if (user2 == null){
                    user2 = new User3();
                }
            }
        }
        return user2;
    }


    public static void main(String[] args) {
        User3 u1 = User3.getInstance();
        User3 u2 = User3.getInstance();
        System.out.println(u1 == u2);
    }

}
