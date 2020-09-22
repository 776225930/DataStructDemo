package com.jhao.threaddemo.threadpool;

import com.jhao.threaddemo.tools.SleepTools;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author JiangHao
 * @date 2020/9/22
 * @describe
 */
public class SingleThreadExecutorDemo {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "myThread");
            }
        });
        //使用Runnable 没有返回结果
//        for (int i = 0; i < 100; i++) {
//            Task task = new Task(i);
//            singleThreadPool.submit(task);
//            singleThreadPool.execute(task);
//        }

        for (int i = 0; i < 100; i++) {
            TaskWithResult task = new TaskWithResult(i);
            Future<String> submit = singleThreadPool.submit(task);
//            try {
//                System.out.println(submit.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }
    }

    static class Task implements Runnable {
        private int index = 0;


        public Task(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            SleepTools.second(1);
            System.out.println("==>" + Thread.currentThread().getName() + ":" + index);
        }
    }

    static class TaskWithResult implements Callable<String> {
        private int index = 0;

        public TaskWithResult(int index) {
            this.index = index;
        }

        @Override
        public String call() throws Exception {
            SleepTools.second(1);
            System.out.println("=====> " + index);
            return index + " is success!";
        }
    }
}
