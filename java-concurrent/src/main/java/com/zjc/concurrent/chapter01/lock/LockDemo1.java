package com.zjc.concurrent.chapter01.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/5 19:48
 */
public class LockDemo1 {

    private volatile int i=0;

    public void add(){
        i++;
    };

    public static void main(String[] args) {
        LockDemo1 lockDemo1 = new LockDemo1();

        for (int i=0;i<2;i++){
            new Thread(() ->{
                for (int j=0;j<10000;j++){
                    lockDemo1.add();
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(lockDemo1.i);


    }
}
