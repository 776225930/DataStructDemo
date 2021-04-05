package com.jhao.threaddemo.ch1.threadLocal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author JiangHao
 * @date 2021/4/6
 * @describe ThreadLocal造成的内存泄漏演示
 * 线程持有的ThreadLocalMap强引用->持有Entry<key,value>,导致无法释放
 *         解决:使用完后 localVariable.remove();//只是释放了key;
 *              然后在每次set get 方法时(概率性)会（expungestelEntry()）清除 key为空的entry,
 *              虽然不一定会完全清除完，但是有效降低了内存泄漏(大概降低了75%)
 *
 */
public class ThreadLocalOOM {

    private static final int TASK_LOOP_SIZE = 100;
    final static ThreadPoolExecutor POOL_EXECUTOR
            = new ThreadPoolExecutor(5, 5,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());

    /*5M大小的数组*/
    static class LocalVariable {
        private byte[] a = new byte[1024 * 1024 * 5];
    }

    ThreadLocal<LocalVariable> localVariable;

    //= new ThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {
        /*5*5=25*/
        for (int i = 0; i < TASK_LOOP_SIZE; ++i) {
            POOL_EXECUTOR.execute(new Runnable() {
                @Override
                public void run() {
                    ThreadLocalOOM oom = new ThreadLocalOOM();
                    oom.localVariable = new ThreadLocal<>();
                    oom.localVariable.set(new LocalVariable());
                    //new LocalVariable();
                    System.out.println("use local varaible");
                    //使用完后需要主动调用，避免内存泄漏
                    oom.localVariable.remove();
                }
            });

            Thread.sleep(100);
        }
        System.out.println("pool execute over");
    }

}
