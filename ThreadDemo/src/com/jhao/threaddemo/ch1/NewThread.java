package com.jhao.threaddemo.ch1;

import java.util.concurrent.ExecutionException;

/**
 * @author JiangHao
 * @date 2021/4/5
 * @describe 开启线程的方式(There are two ways to create a new thread of execution.)；
 *          1.new Thread()
 *          2.实现Runnable
 */
public class NewThread {


    /*扩展自Thread类*/
    private static class UseThread extends Thread{
        @Override
        public void run() {
            super.run();
            // do my work;
            System.out.println("I am extendec Thread");
        }
    }


    /*实现Runnable接口*/
    private static class UseRunnable implements Runnable{

        @Override
        public void run() {
            // do my work;
            System.out.println("I am implements Runnable");
        }
    }


    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        UseThread useThread = new UseThread();
        useThread.start();
//        useThread.start();

        UseRunnable useRunnable = new UseRunnable();
        new Thread(useRunnable).start();


    }
}
