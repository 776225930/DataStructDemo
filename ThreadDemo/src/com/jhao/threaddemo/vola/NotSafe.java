package com.jhao.threaddemo.vola;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe
 */
public class NotSafe {
    private volatile long count = 0;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    /**
     * count进行累加
     */
    public void incCount() {
        count++;
    }

    private static class Count extends Thread {
        private NotSafe notSafe;

        public Count(NotSafe notSafe) {
            this.notSafe = notSafe;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                notSafe.incCount();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NotSafe notSafe = new NotSafe();
        //启动两个线程
        Count count = new Count(notSafe);
        Count count1 = new Count(notSafe);
        count.start();
        count1.start();
        Thread.sleep(6000);
        System.out.println(notSafe.count);
    }

}
