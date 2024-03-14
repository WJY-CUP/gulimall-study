package com.yxj.gulimall.common.test;

import java.util.function.Supplier;

/***
 * 看每个销售员可以出售多少套房子
 */
class House{
    /**
     initialValue():返回此线程局部变量的当前线程的"初始值"
     对于initialValue()较为老旧,jdk1.8又加入了withInitial()方法
     ThreadLocal<Integer>threadLocal=new ThreadLocal<Integer>() {
    @Override
    protected Integer initialValue() {
    return 0;
    }
    };*/
    //public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier)
    //withInitial(Supplier<? extends S> supplier):创建线程局部变量
    //ThreadLocal本地线程变量,线程自带的变量副本
    ThreadLocal<Integer> threadLocal=
            ThreadLocal.withInitial(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    return 0;
                }
            });


    public void saleHouse(){
        //T get():返回当前线程的此线程局部变量的副本中的值。
        Integer value = threadLocal.get();
        value++;
        //void set(T value):将当前线程的此线程局部变量的副本设置为指定的值。
        threadLocal.set(value);
    }
}
public class ThreadLocalTest {
    public static void main(String[] args) {
        House house = new House();
        new Thread(()->{
            try{
                for (int i = 1; i <=30; i++) {
                    house.saleHouse();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"卖出:"+house.threadLocal.get());
            }catch (Exception e){
                e.getStackTrace();
            }finally {
                //void remove():删除此线程局部变量的当前线程的值
                //在阿里巴巴手册中有说明,尽量在代理中使用try-finally块进行回收
                house.threadLocal.remove();
                //下面获取到的值是线程的初始值0
                System.out.println("**********"+house.threadLocal.get());
            }
        },"t1").start();

        new Thread(()->{
            try{
                for (int i = 1; i <=20; i++) {
                    house.saleHouse();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"卖出:"+house.threadLocal.get());
            }catch (Exception e){
                e.getStackTrace();
            }finally {
                house.threadLocal.remove();
            }
        },"t2").start();

        new Thread(()->{
            try{
                for (int i = 1; i <=10; i++) {
                    house.saleHouse();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"卖出:"+house.threadLocal.get());
            }catch (Exception e){
                e.getStackTrace();
            }finally {
                house.threadLocal.remove();
            }
        },"t3").start();
        System.out.println(Thread.currentThread().getName()+"\t"+"卖出了:"+house.threadLocal.get());
    }
}
/**
 * t1	卖出:3
 * t2	卖出:5
 * **********0
 * main	卖出了:0
 * t3	卖出:8
 * */
