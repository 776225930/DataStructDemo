package com.jhao.threaddemo.ch2.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author JiangHao
 * @date 2021/4/6
 * @describe Future等的使用: 注意中断任务需要单独做处理，只调用cancel()无效
 */
public class UseFuture {

    private static class UseCallable implements Callable<Integer> {
        private int sum;

        @Override
        public Integer call() {
            System.out.println("Callable 子线程开始计算！");
            for (int i = 0; i < 500000; i++) {
                //
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Callable子线程计算任务中断！");
                    return null;
                }
                sum = sum + i;
                System.out.println("sum == " + sum);
            }
            System.out.println("Callable 子线程开始结束！结果为:" + sum);
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {

        UseCallable useCallable = new UseCallable();
        //包装
        FutureTask<Integer> futureTask = new FutureTask<Integer>(useCallable);
        Random r = new Random();
        new Thread(futureTask).start();
        Thread.sleep(1);
        if (r.nextInt(100)>50){
            System.out.println("Get UseCallable result = "+futureTask.get());
        }else{
            System.out.println("-------------> Cancel................. ");
            futureTask.cancel(true);
        }
    }
}
