package com.jhao.threaddemo;

/**
 * @author JiangHao
 * @date 2020/4/9
 * @describe 中断Runnable类型的线程
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
//        endThread.interrupt();
    }
}
