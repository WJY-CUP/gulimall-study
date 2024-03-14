package com.yxj.gulimall.common.test;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {

//        只有3个座位
        Semaphore semaphore=new Semaphore(3);
//        10个人抢3个座位
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"\t抢占了车位");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}

