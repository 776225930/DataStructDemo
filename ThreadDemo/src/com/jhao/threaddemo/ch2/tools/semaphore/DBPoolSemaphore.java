package com.jhao.threaddemo.ch2.tools.semaphore;

import com.jhao.threaddemo.ch1.pool.SqlConnectImpl;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @author JiangHao
 * @date 2021/4/7
 * @describe 演示Semaphore用法，一个数据库连接池的实现. 限制线程数
 */
public class DBPoolSemaphore {
    private final static int POOL_SIZE = 10;
    //两个指示器，分别表示池子还有可用链接和已用链接
    private final Semaphore useful, useless;//不可用链接位置也视为一种资源

    private static LinkedList<Connection> pool = new LinkedList<>();

    //初始化连接池
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }

    public DBPoolSemaphore() {
        this.useful = new Semaphore(10);
        this.useless = new Semaphore(0);
    }

    /**
     * 归还链接
     *
     * @param connection
     */
    public void returnConnection(Connection connection) throws InterruptedException {
        if (connection != null) {

            useless.acquire();//获取许可
            synchronized (pool) {
                pool.addLast(connection);
            }
            useful.release();//归还(可用链接)许可
            System.out.println("当前有" + useful.getQueueLength() + "个线程等待数据库连接!!"
                    + "可用连接数：" + useful.availablePermits());
        }
    }

    public Connection takeConnect() throws InterruptedException {
        useful.acquire();//获取(可用链接)许可
        Connection connection;
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        useless.release();//归还许可
        return connection;
    }

}
