package com.jhao.threaddemo.ch1.sync;

import com.jhao.threaddemo.tools.SleepTools;

/**
 * @author JiangHao
 * @date 2021/4/6
 * @describe 演示实例锁和类锁是不同的，两者可以并行
 */
public class InstanceAndClass {

    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println("类锁 TestClass is running...");
            synClass();
        }
    }

    private static class InstanceSyn implements Runnable {
        private InstanceAndClass SynClassAndInstance;

        public InstanceSyn(InstanceAndClass SynClassAndInstance) {
            this.SynClassAndInstance = SynClassAndInstance;
        }

        @Override
        public void run() {
            System.out.println("对象锁 TestInstance is running..." + SynClassAndInstance);
            SynClassAndInstance.instance();
        }
    }

    //等价于  synchronized(this)  锁当前实例
    private synchronized void instance() {
        SleepTools.second(1);
        System.out.println("对象锁 SynInstance is going..." + this.toString());
        SleepTools.second(1);
        System.out.println("对象锁 SynInstance ended " + this.toString());
    }

    //类锁 锁的是class
    private static synchronized void synClass() {
        SleepTools.second(1);
        System.out.println("类锁 synClass going...");
        SleepTools.second(1);
        System.out.println("类锁 synClass end");
    }

    public static void main(String[] args) {
        InstanceAndClass synClassAndInstance = new InstanceAndClass();
        Thread t1 = new SynClass();
        Thread t2 = new Thread(new InstanceSyn(synClassAndInstance));
        t2.start();
        SleepTools.second(1);
        t1.start();
    }
}
