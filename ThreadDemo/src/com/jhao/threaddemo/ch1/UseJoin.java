package com.jhao.threaddemo.ch1;

import com.jhao.threaddemo.tools.SleepTools;

/**
 * @author JiangHao
 * @date 2021/4/5
 * @describe 演示Join（）方法的使用
 */
public class UseJoin {

    static class Goddess implements Runnable {
        /**
         * 即将插队的线程
         */
        private Thread thread;

        public Goddess(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {

            System.out.println("Goddess start running ...");
            try {
                if (thread != null) {
                    //插队到当前线程
                    thread.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Goddess  done.");
        }
    }

    static class Goddess1 implements Runnable {
        @Override
        public void run() {
            System.out.println("Goddess1 start running ...");
            SleepTools.second(3);
            System.out.println("Goddess1  done.");
        }
    }

    public static void main(String[] args) throws Exception {
//        Thread man = Thread.currentThread();
//        Goddess1 goddess1 = new Goddess1();
//        Thread threadG1 = new Thread(goddess1);
//        Goddess goddess = new Goddess(threadG1);
//
//        Thread threadG = new Thread(goddess);
//        threadG.start();
//        threadG1.start();
//        System.out.println("main  start work ...");
//        //插队到主线程
//        threadG.join();
//        SleepTools.second(3);
//        System.out.println("main  work finished.");

        //普通使用
        Thread man = Thread.currentThread();
        Goddess1 goddess1 = new Goddess1();
        Thread threadG1 = new Thread(goddess1);


        System.out.println("main  start work ...");
        //插队到主线程
        threadG1.join();
        //join 后 start() 才起效
        threadG1.start();
        SleepTools.second(3);
        System.out.println("main  work finished.");

    }
}
