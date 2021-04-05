package com.jhao.threaddemo.ch1.safeend;

/**
 * @author JiangHao
 * @date 2021/4/5
 * @describe 阻塞方法中抛出InterruptedException异常后，如果需要继续中断，需要手动再中断一次
 */
public class HasInterrputException {
    private static class UseThread extends Thread {

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Thread.sleep(100);
                    System.out.println(" running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName()
                            + " in InterruptedException interrupt flag is "
                            + isInterrupted());
                    //
                    //1.return;
                    interrupt();
                }
                System.out.println(Thread.currentThread().getName()
                        + " I am extends Thread.");
            }
            System.out.println(Thread.currentThread().getName()
                    + " interrupt flag is " + isInterrupted());
        }

        public static void main(String[] args) throws InterruptedException {
            Thread endThread = new UseThread("HasInterrputEx");
            endThread.start();
            Thread.sleep(500);
            endThread.interrupt();


        }
    }
}
