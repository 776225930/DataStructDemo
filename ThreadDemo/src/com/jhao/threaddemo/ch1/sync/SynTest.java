package com.jhao.threaddemo.ch1.sync;

/**
 * @author JiangHao
 * @date 2021/4/5
 * @describe synchronized关键字的使用方法
 */
public class SynTest {

    private long count = 0;

    private Object obj = new Object();//作为一个锁

    public long getCount() {
        return count;
    }

    /*用在同步块上*/
    public  void incCount() {
        count++;
    }

    public void incCount1() {
        synchronized (obj) {
            count++;
        }
    }

    /**
     * 无效加锁，锁的不是同一个对象
     * 锁的是当前类的对象实例
     */
    public void incCount2() {
        synchronized (this) {
            count++;
        }
    }
    public synchronized void incCount3() {
            count++;
    }

    public void setCount(long count) {
        this.count = count;
    }


    public static class Count extends Thread {

        private SynTest simpleOper;

        public Count(SynTest simpleOper) {
            this.simpleOper = simpleOper;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                simpleOper.incCount3();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynTest simplOper = new SynTest();
        Count count = new Count(simplOper);
        Count count1 = new Count(simplOper);
        count.start();
        count1.start();
        Thread.sleep(500);
        System.out.println(simplOper.count);
    }
}
