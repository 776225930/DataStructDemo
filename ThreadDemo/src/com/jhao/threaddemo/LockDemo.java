package com.jhao.threaddemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe 使用显示锁的范式
 */
public class LockDemo {
    private int count = 0;
    private Lock lock = new ReentrantLock();//默认非公平锁

    public void incr() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public synchronized void incr2() {
        count++;
        incr2();
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo lockDemo = new LockDemo();
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @Override
                public void run() {
                    lockDemo.incr();
                }
            }.start();
        }
        Thread.sleep(2000);
        System.out.println(lockDemo.count);
    }
}
