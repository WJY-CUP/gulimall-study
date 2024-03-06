package com.yxj.gulimall.common.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastjsonTest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        private String name;
        private String nameEn;
    }

    //简单类型
    static String arrayStr = "[{\"name\":\"Zhangsan\"},{\"nameEn\":\"Lisi\"}]";
    static String jsonStr = "{\"name\":\"a\",\"nameEn\":\"b\"}";

    //复杂类型
    private static String getComplex1() throws Exception{
        List<Map<String,User>> list = new ArrayList<>();
        Map<String,User> m1 = new HashMap<>();
        m1.put("1", new User("zs","zs_en"));
        Map<String,User> m2 = new HashMap<>();
        m2.put("3", new User("ls","ls_en"));
        list.add(m1);
        list.add(m2);

        return new ObjectMapper().writeValueAsString(list);
    }
    private static String getComplex2() throws Exception{
        Map<String,List<User>> m = new HashMap<>();
        List<User> list1 = new ArrayList<>();
        list1.add(new User("tt","tt1"));
        m.put("1", list1);

        List<User> list2 = new ArrayList<>();
        list2.add(new User("ww","ww1"));
        m.put("2", list2);

        return new ObjectMapper().writeValueAsString(m);
    }

    public static void main(String[] args) throws Exception {

        /**
         * readValue(String content, Class<T>  valueType)
         */

//bean
        User uu = new ObjectMapper().readValue(jsonStr, User.class);
        System.out.println(uu);//User(name=a, nameEn=b)
//map
        Map mm = new ObjectMapper().readValue(jsonStr, Map.class);
        System.out.println(mm);//{name=a, nameEn=b}

//默认jackson将每个json对象封装成LinkedHashMap，然后放到list中
        List<Map> readValue = new ObjectMapper().readValue(arrayStr, List.class);
        System.out.println(readValue);//[{name=Zhangsan}, {nameEn=Lisi}]

//List<bean> 无法构造

//复杂类型
//        String complexStr1 = getComplex1(); //List<Map<String, User>>
//        String complexStr2 = getComplex2(); //Map<String,List<User>>
//
//        List<Map> list1 = new ObjectMapper().readValue(complexStr1, List.class);
//        System.out.println(list1); //[{1={name=zs, nameEn=zs_en}}, {3={name=ls, nameEn=ls_en}}]
//
//        Map map1 = new ObjectMapper().readValue(complexStr2, Map.class);
//        System.out.println(map1); //{1=[{name=tt, nameEn=tt1}], 2=[{name=ww, nameEn=ww1}]}

    }
}
