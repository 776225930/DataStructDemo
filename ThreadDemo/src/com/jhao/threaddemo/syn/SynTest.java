package com.jhao.threaddemo.syn;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe
 */
public class SynTest {


    private long count = 0;
    //作为一个锁
    private Object obj = new Object();

    public void setCount(long count) {
        this.count = count;
    }

    /**
     * count进行累加
     */
    public void incCount() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName());
            count++;
        }
    }

    public synchronized void incCount2() {
        System.out.println(Thread.currentThread().getName());
        count++;
    }

    /**
     * count进行累加
     */
    public void incCount3() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName());
            count++;
        }
    }

    private static class Count extends Thread {
        private SynTest simplOper;

        public Count(SynTest simplOper) {
            this.simplOper = simplOper;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                simplOper.incCount();//count = count+10000
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynTest simplOper = new SynTest();
        SynTest simplOper1 = new SynTest();

        //启动两个线程
        Count count1 = new Count(simplOper);
        Count count2 = new Count(simplOper);
        count1.start();
        count2.start();
        Thread.sleep(50);
        System.out.println(simplOper.count);//20000

    }
}
