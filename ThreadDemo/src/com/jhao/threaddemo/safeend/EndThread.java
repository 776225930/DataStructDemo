package com.jhao.threaddemo.safeend;

/**
 * @author JiangHao
 * @date 2020/4/9
 * @describe 如何安全的中断线程
 * 相关stop()、resume()、destory()、suspend()已经不建议再使用,过于粗暴
 * 1.使用interrupt()方法修改Thread的状态标志位。
 *  在线程中使用isInterrupted()查询相关状态来控制线程
 *  interrupt()并不是马上终止线程，而只是发出信号，具体还要看相关线程的处理
 * 2.使用static Thread.interrupted():方法和isInterrupted()相似,只是在最后会再次修改线程状态为false,
 *  而使用isInterrupted()则最终状态为true
 */
public class EndThread {

    private static class UseThread extends Thread {

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " interrrupt flag ==== " + isInterrupted());
            //2.
//            while (!Thread.interrupted()) {
            while (!Thread.currentThread().isInterrupted()) {
                //1.
//                while (!isInterrupted()) {
                System.out.println(threadName + " is running");
                System.out.println(threadName + " inner interrupt flag == " + isInterrupted());
            }
            System.out.println(threadName + " interrrupt flag ====" + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();

        Thread.sleep(20);
        //1.使用interrupt()方法修改Thread的状态标志位。
        //在线程中使用isInterrupted()查询相关状态来控制线程
        //interrupt()并不是马上终止线程，而只是发出信号，具体还要看相关线程的处理
        endThread.interrupt();
    }
}
