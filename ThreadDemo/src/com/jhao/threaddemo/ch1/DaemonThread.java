package com.jhao.threaddemo.ch1;

import com.jhao.threaddemo.tools.SleepTools;

import java.util.concurrent.ExecutionException;

/**
 * @author JiangHao
 * @date 2021/4/5
 * @describe 守护线程:等其他线程结束后，自己结束，finally可能不会走。
 */
public class DaemonThread {

    private static boolean isRun = true;

    private static class UseThread extends Thread {

        @Override
        public void run() {
            super.run();
            try {
                while (isRun) {
                    System.out.println(Thread.currentThread().getName() + " I am extends Thread.");
                }
                System.out.println(Thread.currentThread().getName() + " interrupt flag is " + isInterrupted());
            } finally {
                //守护线程中finally不一定起作用
                System.out.println(" .............finally");
            }
        }
    }

//    static {
//        UseThread useThread = new UseThread();
//        useThread.setDaemon(true);
//        useThread.start();
//    }

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        UseThread useThread = new UseThread();
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(2000);
    }

}
