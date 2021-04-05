package com.jhao.threaddemo.ch1.safeend;

import javafx.scene.paint.Stop;

/**
 * @author JiangHao
 * @date 2021/4/5
 * @describe 安全结束线程；
 * 相关stop()、resume()、destory()、suspend()已经不建议再使用,过于粗暴
 *  * 1.使用 interrupt()方法修改Thread的状态标志位。
 *  *   在线程中使用isInterrupted()查询相关状态来控制线程
 *  *   interrupt()并不是马上终止线程，而只是发出信号，具体还要看相关线程的处理
 *  * 2.使用static Thread.interrupted():方法和isInterrupted()相似,只是在最后会再次修改线程状态为false,
 *  *  而使用isInterrupted()则最终状态为true.
 *  *  也就是说调用 Thread.interrupted()判断当前线程状态后，马上又会清除状态标志位为false。下一次再判断线程状态会得到true
 */
public class EndThread {

    private static class UseThread extends Thread {

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": interrupt flag == " + isInterrupted());
//            while (!isInterrupted()) {
            while (!Thread.interrupted()) {
                System.out.println(threadName + " is running!");
                System.out.println(threadName + "inner interrrupt flag ="
                        + isInterrupted());
            }
            System.out.println("使用Thread.interrupted() 判断线程状态后: " + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread("test end t");
        useThread.start();
        Thread.sleep(3000);
        useThread.interrupt();
    }
}
