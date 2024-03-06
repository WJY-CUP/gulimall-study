package com.yxj.gulimall.common.test;

import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureTest {
    public static ExecutorService service = Executors.newFixedThreadPool(10);
    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            10,
            100,
            1000,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(100000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main start");

//        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("runAsync 当前线程" + Thread.currentThread());
//                int i = 10 / 2;
//                System.out.println("计算结果：" + i);
//            }
//        }, service).whenComplete(new BiConsumer<Void, Throwable>() {
//            @Override
//            public void accept(Void result, Throwable throwable) {
//                System.out.println("runAsync result = " + result);
//                System.out.println("runAsync 异常 = " + throwable.getMessage());
//            }
//        });

//        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                System.out.println("supplyAsync 当前线程" + Thread.currentThread());
//                int i = 10 / 5;
//                System.out.println("计算结果：" + i);
//                return i;
//            }
//        }, service).whenComplete(new BiConsumer<Integer, Throwable>() {
//            @Override
//            public void accept(Integer result, Throwable throwable) {
//                System.out.println("supplyAsync 结果 = " + result);
//                System.out.println("supplyAsync 异常 = " + throwable);
//            }
//        }).exceptionally(new Function<Throwable, Integer>() {
//            @Override
//            public Integer apply(Throwable throwable) {
//                System.out.println("supplyAsync 异常详细信息 = " + throwable.getMessage());
////                返回一个默认结果
//                return 10;
//            }
//        });

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("任务1启动");
                System.out.println("supplyAsync 当前线程" + Thread.currentThread().getId());
                int i = 10 / 5;
                System.out.println("计算结果：" + i);
                return i;
            }
        }, service).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer result, Throwable throwable) {
                System.out.println("handle 当前线程" + Thread.currentThread().getId());

                System.out.println("supplyAsync 结果 = " + result);
                System.out.println("supplyAsync 异常 = " + throwable);
                //                返回一个默认结果
                if (throwable != null) {
                    return 3;
                }
                return result;
            }
        }).thenApply(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer result) {
                System.out.println("任务2启动");
                System.out.println("thenApplyAsync 当前线程" + Thread.currentThread().getId());

                return result*2;
            }
        });
        System.out.println(future1.get());

//        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
//
//            @Override
//            public Integer get() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("task 1 begin");
//                System.out.println("task 1 thread = "+Thread.currentThread().getId());
//                System.out.println("task 1 end");
//                return 10 / 2;
//            }
//        }, service);
//
//        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("task 2 begin");
//                System.out.println("task 2 thread = "+Thread.currentThread().getId());
//                System.out.println("task 2 end");
//                return 2000;
//            }
//        }, service);
//
//        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("task 3 begin");
//                System.out.println("task 3 thread = "+Thread.currentThread().getId());
//                System.out.println("task 3 end");
//                return 3000;
//            }
//        }, service);
////
//        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2, future3);
//        anyOf.join();


        System.out.println("main thread = " + Thread.currentThread().getId());

//        FutureTask<Object> futureTask = new FutureTask<Object>(new Callable01());
//        new Thread(futureTask).start();

//        future1.runAfterBoth(future2, new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("task 3 begin");
//                System.out.println("task 3 thread = "+Thread.currentThread().getId());
//                System.out.println("task 3 end");
//            }
//        });

//        future1.thenAcceptBothAsync(future2, new BiConsumer<Integer, Object>() {
//            @Override
//            public void accept(Integer integer, Object o) {
//                System.out.println("task 1 result = "+integer);
//                System.out.println("task 2 result = "+o);
//            }
//        },service);

//        CompletableFuture<Object> future3 = future1.thenCombineAsync(future2, new BiFunction<Integer, Object, Object>() {
//            @Override
//            public Object apply(Integer integer, Object o) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("task 1 result = " + integer);
//                System.out.println("task 2 result = " + o);
//                return integer.toString()+"---"+o.toString();
//            }
//        }, service);
//        System.out.println(future3.get());

//        future1.runAfterEitherAsync(future2, new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("任务1或者2执行完了");
//            }
//        },service);

//        future1.acceptEitherAsync(future2, new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println("task 1 or 2 result = "+integer);
//            }
//        },service);

//        future1.runAfterEitherAsync(future2, new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("task 1 or 2 end");
//            }
//        },service);



        System.out.println("main end ");
//        System.out.println("main end "+supplyAsync.get());

//        service.shutdown();

//        MyThreadTest myThreadTest = new MyThreadTest();
//        Thread01 thread01 = myThreadTest.new Thread01();
//        Thread01 thread01 = new Thread01();
//        thread01.start();

//        Runnable01 runnable01 = new Runnable01();
//        new Thread(runnable01).start();

//        Callable01 callable01 = new Callable01();
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable01);
//        new Thread(futureTask).start();
//        Integer result = futureTask.get();


//        Object result = service.submit(new Runnable01()).get();
//        Object result = service.submit(new Callable01()).get();
//        Object result = service.submit(new Thread01()).get();
//        service.execute(new Runnable01());
//        System.out.println("main end, result = "+result);

//        for (int i = 0; i < 100; i++) {
//            service.submit(new Callable01());
//        }
//        while (true) {
//            service.submit(new Callable01());
//        }

//        threadPool.submit(new Runnable01());
//        threadPool.execute(new Thread01());

    }

    public static class Thread01 extends Thread {
        @Override
        public void run() {
            System.out.println("当前线程" + Thread.currentThread());
            int i = 10 / 2;
            System.out.println("计算结果：" + i);
        }
    }

    public static class Runnable01 implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程" + Thread.currentThread());
            int i = 10 / 2;
            System.out.println("计算结果：" + i);
        }
    }

    public static class Callable01 implements Callable {
        @Override
        public Object call() throws Exception {
            System.out.println("当前线程 = " + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("计算结果：" + i);
            return i;
        }
    }

}
