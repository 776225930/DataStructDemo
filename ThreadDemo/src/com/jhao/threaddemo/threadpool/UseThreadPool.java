package com.jhao.threaddemo.threadpool;

import com.jhao.threaddemo.tools.SleepTools;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe 线程池的使用
 */
public class UseThreadPool {

    /**
     * 工作线程
     */
    static class Worker implements Runnable {
        private String taskName;
        private Random r = new Random();

        public Worker(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskName() {
            return taskName;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()
                    + " process this task :" + taskName);
            SleepTools.ms(r.nextInt(100) * 5);
        }
    }

    /**
     * 带返回结果
     */
    static class CallWorker implements Callable<String> {
        private String taskName;
        private Random r = new Random();

        public CallWorker(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName()
                    + " process this task :" + taskName);
//            SleepTools.ms(r.nextInt(100) * 5);
            return Thread.currentThread().getName()+":"+r.nextInt(100)*5;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = new ThreadPoolExecutor(3, 4, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());
//        for (int i = 0; i < 6; i++) {
//            Worker worker = new Worker("worker_" + i);
//            pool.execute(worker);
//        }
        for (int i = 0; i < 6; i++) {
            CallWorker callWorker = new CallWorker("callableWorker_" + i);
            Future<String> result = pool.submit(callWorker);
            System.out.println(result.get());
        }
        pool.shutdown();
    }

}
