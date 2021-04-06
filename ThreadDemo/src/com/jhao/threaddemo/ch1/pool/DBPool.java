package com.jhao.threaddemo.ch1.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author JiangHao
 * @date 2021/4/6
 * @describe 连接池的实现
 */
public class DBPool {

    /**
     * 容器，存放连接
     */
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    /**
     * 限制了池的大小=20
     *
     * @param initialSize
     */
    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    /**
     * 释放连接,通知其他的等待连接的线程
     *
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            //锁 连接池
            synchronized (pool) {
                pool.addLast(connection);
                //通知其他等待线程 可以拿链接了
                pool.notify();
            }
        }
    }

    /**
     * 获取
     *
     * @param waitMills 在 waitMills 内无法获取到连接，将会返回null 1S
     */
    public Connection fetchConnection(long waitMills) throws InterruptedException {
        synchronized (pool) {
            //永不超时
            if (waitMills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                //等待超时时刻
                long future = System.currentTimeMillis() + waitMills;
                //等待时长
                long remaining = waitMills;
                //todo 这里换 if 会?
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    //唤醒一次，重新计算等待时长
                    remaining = future - System.currentTimeMillis();
                }
                Connection connection = null;
                if (!pool.isEmpty()) {
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }

}
