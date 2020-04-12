package com.jhao.threaddemo.syn;

import com.jhao.threaddemo.tools.SleepTools;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe 演示对象锁和类锁
 * synchronized 加到 static 方法前面是给class加锁，即类锁；
 * 而synchronized 加到非静态方法前面是给对象上锁。
 * 对象锁和类锁是不同的锁，所以多个线程同时执行这2个不同锁的方法时，是异步的。
 */
public class SyncClzAndInst {

    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println("TestClass is running...");
            synClass();
        }
    }

    /**
     * 类锁:实际锁的是类的class对象
     */
    private static synchronized void synClass() {
        SleepTools.second(1);
        System.out.println("syncClass going...");
        SleepTools.second(1);
        System.out.println("synClass end");
    }


    //对象锁
    private static Object object = new Object();

    /**
     * 使用对象锁
     */
    private void synStaticObject() {
        synchronized (object) {
            SleepTools.second(1);
            System.out.println("使用对象锁 syncClass going...");
            SleepTools.second(1);
            System.out.println("使用对象锁 synClass end");
        }
    }

    /**
     * 使用对象锁
     */
    private static class SyncObject implements Runnable {
        private SyncClzAndInst syncClzAndInst;

        public SyncObject(SyncClzAndInst syncClzAndInst) {
            this.syncClzAndInst = syncClzAndInst;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running... " + syncClzAndInst);
            syncClzAndInst.instance();
        }
    }

    private static class SyncObject2 implements Runnable {
        private SyncClzAndInst syncClzAndInst;

        public SyncObject2(SyncClzAndInst syncClzAndInst) {
            this.syncClzAndInst = syncClzAndInst;
        }

        @Override
        public void run() {
            System.out.println("TestInstance2 is running... " + syncClzAndInst);
            syncClzAndInst.instance2();
        }
    }

    /**
     * 锁对象
     */
    private synchronized void instance() {
        SleepTools.second(3);
        System.out.println("synInstance is going...     " + this.toString());
        SleepTools.second(3);
        System.out.println("synInstance ended       " + this.toString());
    }

    //锁对象
    private synchronized void instance2() {
        SleepTools.second(3);
        System.out.println("synInstance2 is going...      " + this.toString());
        SleepTools.second(3);
        System.out.println("synInstance2 ended      " + this.toString());
    }

    public static void main(String[] args) {
        //使用对象锁,不同的对象调用一个非静态同步方法不会起同步作用
        //同一个对象调用才会起作用
        SyncClzAndInst syncClzAndInst = new SyncClzAndInst();
        Thread t1 = new Thread(new SyncObject(syncClzAndInst));
        SyncClzAndInst syncClzAndInst1 = new SyncClzAndInst();
        Thread t2 = new Thread(new SyncObject2(syncClzAndInst1));
        t1.start();
        t2.start();

        //使用类锁
//        SynClass synClass = new SynClass();
//        synClass.start();
//        SynClass synClass1 = new SynClass();
//        synClass1.start();
//        SleepTools.second(1);
    }
}
