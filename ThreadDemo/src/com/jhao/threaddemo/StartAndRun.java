package com.jhao.threaddemo;

import com.jhao.threaddemo.tools.SleepTools;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe start和run方法的区别
 */
public class StartAndRun {

    public static class ThreadRun extends Thread {
        @Override
        public void run() {
            int i = 90;
            while (i > 0) {
                SleepTools.ms(1000);
                System.out.println("I am " + Thread.currentThread().getName()
                        + " and now the i=" + i--);
            }
        }
    }

    private static class User {
        public void us() {

        }
    }

    public static void main(String[] args) {
        ThreadRun threadRun = new ThreadRun();
        threadRun.setName("thread_run");

        new User().us();
        threadRun.start();
    }
}
