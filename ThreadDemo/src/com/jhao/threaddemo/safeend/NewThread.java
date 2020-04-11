package com.jhao.threaddemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author JiangHao
 * @date 2020/4/9
 * @describe 新建线程的方法
 */
public class NewThread {

    //1.扩展自Thread类
    private static class UseThread extends Thread {
        @Override
        public void run() {
            super.run();
            //do my work
            System.out.println("I am extends Thread。 my name is : " + Thread.currentThread().getName());
        }
    }


    /**
     * 2.1 实现Runnable接口,这种接口只是任务内容的描述,并不算线程
     */
    private static class UseRunnable implements Runnable {

        @Override
        public void run() {
            //do my work
            System.out.println("I am the Runnable。 my name is : " + Thread.currentThread().getName());
        }
    }

    /**
     * 2.2 实现Callable接口，允许有返回值
     * 需要使用FeatureTask接收结果
     */
    private static class UseCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("I am the Callable ,I can return a result , my name is : " + Thread.currentThread().getName());
            return "the result";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("the main Thread name is ：" + Thread.currentThread().getName());
        UseThread useThread = new UseThread();
        useThread.start();

        new Thread(new UseRunnable()).start();

        UseCallable useCallable = new UseCallable();
        FutureTask<String> futureTask = new FutureTask<String>(useCallable);
        new Thread(futureTask).start();
        //do some work ...

        //阻塞方法,会一直等待运行结果。一般是预估大概需要的时间，之后再去获取结果。
        System.out.println("get " + futureTask.get());
    }
}
