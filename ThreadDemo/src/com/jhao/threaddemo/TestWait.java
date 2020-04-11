package com.jhao.threaddemo;

import sun.applet.Main;

/**
 * @author JiangHao
 * @date 2020/4/11
 * @describe
 */
public class TestWait {

    public static void main(String[] args) {
        TestWait testWait = new TestWait();
        testWait.startThread();

    }

    /**
     * 线程锁
     */
    private final Object object = new Object();

    public void startThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始执行...");
                System.out.println("进入等待状态...");
                synchronized (object) {
                    try {
                        object.wait();
                        //并不会执行，不要想太多
                        System.out.println("I runned , ha ?");
                        for (int i = 0; i < 10000; i++) {
                            System.out.println("should i run ?");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程结束...");
            }
        });
        thread.start();
    }
}
