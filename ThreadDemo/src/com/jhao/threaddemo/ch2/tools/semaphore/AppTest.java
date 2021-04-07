package com.jhao.threaddemo.ch2.tools.semaphore;

import com.jhao.threaddemo.tools.SleepTools;

import java.sql.Connection;
import java.util.Random;

/**
 * @author JiangHao
 * @date 2021/4/7
 * @describe
 */
public class AppTest {
    private static DBPoolSemaphore dbPool = new DBPoolSemaphore();

    private static class BusinessThread extends Thread {
        @Override
        public void run() {

            Random r = new Random();//让每个线程持有连接的时间不一样
            long start = System.currentTimeMillis();
            try {
                Connection connection = dbPool.takeConnect();
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + "_获取数据库连接共耗时【" + (System.currentTimeMillis() - start) + "】ms.");
                SleepTools.ms(100 + r.nextInt(100));//模拟业务操作，线程持有连接查询数据
                System.out.println("查询数据完成，归还连接！");
                dbPool.returnConnection(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread thread = new BusinessThread();
            thread.start();
        }
    }
}
