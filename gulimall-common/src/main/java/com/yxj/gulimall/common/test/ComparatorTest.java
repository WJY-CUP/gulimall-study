package com.yxj.gulimall.common.test;

import lombok.Builder;
import lombok.experimental.Accessors;
import org.springframework.util.comparator.Comparators;

import java.util.*;
import java.util.stream.Collectors;

public class ComparatorTest {

    public static List<People> compareTest(List<People> arr){
        Collections.sort(arr, new Comparator<People>(){
            public int compare(People p1,People p2){
                int a = p1.age;    //比较的是age
                int b = p2.age;
                return Integer.compare(a, b);    //当a<b返回-1，a==b返回0，a>b返回1
            }
        });
        return arr;
    }


    public static void main(String[] args) {
        Arrays.asList(3, 2, 1).sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }.thenComparing(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        }).reversed());






    }
}



class  People  implements Comparable {
    String name;
    int age;
    int id;

    public People(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        People p = (People) o;
        // 防止空指针异常，确保传入的Person对象不为null
        if (p == null) {
            throw new IllegalArgumentException("The person to compare against cannot be null.");
        }
        // 直接使用Java的数值比较方法简化代码
        return Integer.compare(this.age, p.age);

    }

}
