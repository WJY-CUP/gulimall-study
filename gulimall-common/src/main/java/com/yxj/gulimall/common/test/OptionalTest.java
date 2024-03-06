package com.yxj.gulimall.common.test;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OptionalTest {

    public static void main(String[] args) throws Throwable {
        Optional<Integer> of = Optional.of(1);
        Optional<Object> empty = Optional.empty();
        Optional<Object> ofNull = Optional.ofNullable("hello");
//        System.out.println(of);
//        System.out.println(empty);
//        System.out.println(ofNull);

//        System.out.println(of.get());//1
//        System.out.println(empty.get());//NoSuchElementException
//        System.out.println(ofNull.get());//NoSuchElementException

        System.out.println(of.orElse(2));//1
        System.out.println(empty.orElse("empty"));//empty
        System.out.println(ofNull.orElse("ofNull"));//ofNull

        System.out.println(of.orElseGet(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 20 / 4;
            }
        }));
        System.out.println(empty.orElseGet(new Supplier<Object>() {
            @Override
            public Object get() {
                return "I am empty";
            }
        }));
        System.out.println(ofNull.orElseGet(new Supplier<Object>() {
            @Override
            public Object get() {
                return "I am ofNull";
            }
        }));


//        System.out.println(of.orElseThrow(new Supplier<Throwable>() {
//            @Override
//            public Throwable get() {
//                return new RuntimeException("Throwable of");
//            }
//        }));
//        System.out.println(empty.orElseThrow(new Supplier<Throwable>() {
//            @Override
//            public Throwable get() {
//                return new RuntimeException("Throwable empty");
//            }
//        }));
//        System.out.println(ofNull.orElseThrow(new Supplier<Throwable>() {
//            @Override
//            public Throwable get() {
//                return new RuntimeException("Throwable ofNull");
//            }
//        }));

        System.out.println(of.isPresent());
        System.out.println(empty.isPresent());
        System.out.println(ofNull.isPresent());

        of.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("consumer of has value");
            }
        });
        empty.ifPresent(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.println("consumer empty has value");
            }
        });
        ofNull.ifPresent(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.println("consumer ofNull has value");
            }
        });




    }
}
