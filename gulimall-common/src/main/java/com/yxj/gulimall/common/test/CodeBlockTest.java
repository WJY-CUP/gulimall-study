package com.yxj.gulimall.common.test;

public class CodeBlockTest {

    public static void main(String[] args) {

        String desc = Person.desc;
        System.out.println(desc);
/*        hello,static block-1
        我是一个快乐的人。
        hello,static block-2
        static changed field*/

        Person p1 = new Person();
        System.out.println(Person.desc);
/*       每次new对象都会执行一次非静态代码块
        hello,block-2
        hello,block-1
        吃饭
        我是一个快乐的人。
        no static changed field*/

        Person p2 = new Person();
        System.out.println(p1.age);
//
        Person.info();
    }
}

class Person{
    //属性
    String name;
    int age;
    static String desc = "initial static field";

    //构造器
    public Person(){}

    //static 的代码块
    static{
        System.out.println("hello,static block-1");
        //只能调用静态结构
        desc = "static changed field";
        info();
        //不能调用非静态结构
//		eat();
//		name = "Tom";
    }

    static{
        System.out.println("hello,static block-2");
    }

    //非 static 的代码块
    {
        System.out.println("hello,block-2");
    }
    {
        System.out.println("hello,block-1");
        //调用非静态结构
        age = 1;
        eat();
        //调用静态结构
        desc = "no static changed field";
        info();
    }

    //方法
    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    public void eat(){
        System.out.println("吃饭");
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
    public static void info(){
        System.out.println("我是一个快乐的人。");
    }

}

