package com.leetcode.hot100.rubbish;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TreadCreateDemo {
    public static void main(String[] args) {

    }
}

// 方法1：集成thread类
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread.run");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

//方法2：实现runable接口
class Mythread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Mythread2.run");
    }
    public static void main(String[] args) {
        Mythread2 myThread2 = new Mythread2();
        myThread2.run();
    }
}

//方法3：实现callable接口
class Mythread3 implements Callable {
    @Override
    public String call() throws Exception {
        System.out.println("Mythread3.run");
        return "ok";
    }

    public static void main(String[] args) throws Exception {
        Mythread3 mythread3 = new Mythread3();
        mythread3.call();
    }
}

//方法4：线程池创建
class MyExecutors implements Runnable{
    @Override
    public void run() {
        System.out.println("MyExecutors.run");
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        threadPool.submit(new MyExecutors());

        threadPool.shutdown();

    }
}
