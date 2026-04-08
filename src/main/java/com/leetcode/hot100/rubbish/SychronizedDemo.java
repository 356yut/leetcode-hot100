package com.leetcode.hot100.rubbish;

public class SychronizedDemo {
    static Object lock = new Object();
    int ticketNum = 10;
    public void getTicket() {
        synchronized (lock) {
            if (ticketNum > 0) {
                ticketNum--;
                System.out.println(Thread.currentThread().getName()+"买了一张票，剩余"+ticketNum);
            }
        }
    }
    public static void main(String[] args) {
        SychronizedDemo demo = new SychronizedDemo();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                demo.getTicket();
            }).start();
        }
    }
}
