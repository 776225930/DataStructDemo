package com.jhao.threaddemo.ch1;

/**
 * @author JiangHao
 * @date 2021/4/5
 * @describe StartAndRun在执行上的区别
 */
public class StartAndRun {
    public static class ThreadRun extends Thread {

        @Override
        public void run() {
            int i = 90;
            while (i > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
                System.out.println("I am " + Thread.currentThread().getName()
                        + " and now the i=" + i--);
            }
        }
    }

    public static void main(String[] args) {
        ThreadRun threadRun = new ThreadRun();
        threadRun.setName("threadRun");
//        threadRun.run();
        threadRun.start();
    }
}
