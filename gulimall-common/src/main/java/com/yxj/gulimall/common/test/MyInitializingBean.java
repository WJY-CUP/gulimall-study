package com.yxj.gulimall.common.test;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 初始化执行顺序：constructor(构造器) -> @PostConstruct(JDK) -> InitializingBean(Spring接口) -> init-method(@Bean属性)
 * 销毁执行顺序：                       @PreDestroy(JDK) -> DisposableBean(Spring接口) -> destroy-method(@Bean属性)
 *
 * https://blog.csdn.net/chyang1999/article/details/121198707
 *
 *    在Spring框架中，静态代码块会在component组件的依赖初始化之前就开始执行其中的内容，
 *    因而导致很多的空指针异常，使用@PostConstruct注解可以避免这样异常的发生。
 */
public class MyInitializingBean implements InitializingBean, DisposableBean {
    public MyInitializingBean() {
        System.out.println("我是MyInitializingBean构造方法执行...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我是 InitializingBean afterPropertiesSet方法执行...");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("我是postConstruct方法执行...");
    }

    public void init(){
        System.out.println("我是init方法执行...");
    }
    public void destroy01(){
        System.out.println("我是 @Bean配置项 destroy 方法执行...");
    }

    @Bean(initMethod = "init", destroyMethod = "destroy01")
    public MyInitializingBean test() {
        return new MyInitializingBean();
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("我是 DisposableBean destroy 方法执行...");
    }

    @PreDestroy
    public void destroy02() {
        System.out.println("我是 @PreDestroy 方法执行...");
    }
}
