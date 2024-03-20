package com.yxj.gulimall.common.test;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomDemo {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//            生成一样的随机数
                    System.out.println(Thread.currentThread().getName() + ": " + random.nextInt(10));
//            生成不一样的随机数
                    System.out.println(Thread.currentThread()
                    .getName() + ": " + ThreadLocalRandom.current().nextInt(100));
                }
            }).start();
        }
    }

}