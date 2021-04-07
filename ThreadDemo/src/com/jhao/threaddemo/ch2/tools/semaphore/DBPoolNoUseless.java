package com.jhao.threaddemo.ch2.tools.semaphore;

import com.jhao.threaddemo.ch1.pool.SqlConnectImpl;
import com.jhao.threaddemo.tools.SleepTools;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author JiangHao
 * @date 2021/4/7
 * @describe Semaphore 用来控制同时访问特定资源的线程数量
 * 用法，一个数据库连接池的实现
 */
public class DBPoolNoUseless {

    private final static int POOL_SIZE = 10;
    //这么做存在隐患，无法限制归还的次数
    private final Semaphore useful;
    //存放数据库连接的容器
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    //初始化池
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }

    public DBPoolNoUseless() {
        this.useful = new Semaphore(10);
    }

    /**
     * 归还链接
     *
     * @param connection
     */
    public void returnConnect(Connection connection) throws InterruptedException {
        if (connection != null) {
            System.out.println("当前有"+useful.getQueueLength()+"个线程等待数据库连接!!"
                    +"可用连接数："+useful.availablePermits());
            synchronized (pool) {
                pool.addLast(connection);
            }
            useful.release();

        }
    }

    /*从池子拿连接*/
    public Connection takeConnect() throws InterruptedException {
        useful.acquire();
        Connection connection;
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        useful.release();
        return connection;
    }

    private static DBPoolNoUseless dbPoolNoUseless = new DBPoolNoUseless();

    private static class BusiThread extends Thread{
        @Override
        public void run() {
            Random r = new Random();//让每个线程持有连接的时间不一样
            long start = System.currentTimeMillis();
            try {
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +"_获取数据库连接共耗时【"+(System.currentTimeMillis()-start)+"】ms.");
                SleepTools.ms(100+r.nextInt(100));//模拟业务操作，线程持有连接查询数据
                System.out.println("查询数据完成，归还连接！");
                dbPoolNoUseless.returnConnect(new SqlConnectImpl());
            } catch (InterruptedException e) {
            }
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 120; i++) {
            Thread thread = new BusiThread();
            thread.start();
        }
    }
}
