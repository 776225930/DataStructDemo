package com.jhao.threaddemo.ch1.vola;


/**
 * @author JiangHao
 * @date 2021/4/6
 * @describe
 */
public class Nosafe {

    public volatile long count = 0;//只能保证可见性，无法确保线程安全

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    //count进行累加
    public void incCount() {
        count++;
    }

    //线程
    private static class Count extends Thread {

        private Nosafe simplOper;

        public Count(Nosafe simplOper) {
            this.simplOper = simplOper;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                simplOper.incCount();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Nosafe simplOper = new Nosafe();
        //启动两个线程
        Count count1 = new Count(simplOper);
        Count count2 = new Count(simplOper);
        count1.start();
        count2.start();
        Thread.sleep(50);
        System.out.println(simplOper.count);//20000?
    }
}
