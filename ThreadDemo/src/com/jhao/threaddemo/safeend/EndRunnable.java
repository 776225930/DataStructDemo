package com.jhao.threaddemo.safeend;

/**
 * @author JiangHao
 * @date 2020/4/9
 * @describe 中断Runnable类型的线程
 * 和Thread类似，通过interrupt()和isInterrupt()方法,
 * 不同的是通过Thread.currentThread()获取当前线程对象
 */
public class EndRunnable {

    private static class UseRunable implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " interrupt flas is " + Thread.currentThread().isInterrupted());
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(threadName + " is running ...");
            }
            System.out.println(threadName + " interrupt flas is " + Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunable useRunable = new UseRunable();
        Thread endThread = new Thread(useRunable);
        endThread.start();
        Thread.sleep(10);
        endThread.interrupt();
    }
}
