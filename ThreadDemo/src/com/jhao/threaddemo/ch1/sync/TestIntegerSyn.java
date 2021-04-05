package com.jhao.threaddemo.ch1.sync;

/**
 * @author JiangHao
 * @date 2021/4/6
 * @describe 错误的加锁和原因分析
 *
 */
public class TestIntegerSyn {
    public static void main(String[] args) {
        Worker worker=new Worker(1);
        //Thread.sleep(50);
        for(int i=0;i<5;i++) {
            new Thread(worker).start();
        }
    }
    private static class Worker implements Runnable{

        private Integer i;
        private Object o = new Object();

        public Worker(Integer i) {
            this.i=i;
        }

        @Override
        public void run() {
            synchronized (o) {
//            synchronized (i) {//这里使用 Integer 作为锁 存在问题，Integer 每次自增后返回新Integer对象
                Thread thread=Thread.currentThread();
//                System.out.println(thread.getName()+"--@"
//                        +System.identityHashCode(i));
                i++;
//                System.out.println(thread.getName()+"-------"+i+"-@"
//                        +System.identityHashCode(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName()+"-------"+i+"--@"
                        +System.identityHashCode(i));
            }

        }

    }
}
