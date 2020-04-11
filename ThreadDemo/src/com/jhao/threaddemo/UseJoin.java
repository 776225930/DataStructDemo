package com.jhao.threaddemo;

import com.jhao.threaddemo.tools.SleepTools;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe 演示下join方法的使用
 */
public class UseJoin {

    static class JumpQueue implements Runnable {
        private Thread thread;//用来插队

        public JumpQueue(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            System.out.println(thread.getName() + " will be join before " + Thread.currentThread().getName());
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminted.");
        }
    }

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            //i=0,previous 是主线程，i=1;previous是i=0这个线程
            Thread thread = new Thread(new JumpQueue(previous));
            thread.start();
            previous = thread;
        }
        SleepTools.second(2);//让主线程休眠2秒
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }
}
