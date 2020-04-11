package com.zjc.concurrent.singleton;

/**
 * @author - zjc
 * @Description - 枚举方式
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/11 15:11
 */
public class User5 {

    /** 构造函数私有化 */
    private User5(){

    }

    /** 构建静态方法，获取实例 */
    public static User5 getInstance(){
        return SingletonEnum.INSTANCE.getInstanceEnum();
    }

    /** 枚举类 */
    private enum SingletonEnum{
        INSTANCE;

        private User5 user5;

        SingletonEnum(){
            user5 = new User5();
        }

        private User5 getInstanceEnum(){
            return this.user5;
        }
    }


    public static void main(String[] args) {
        User5 u1 = User5.getInstance();
        User5 u2 = User5.getInstance();

        System.out.println(u1 == u2);
    }



}
