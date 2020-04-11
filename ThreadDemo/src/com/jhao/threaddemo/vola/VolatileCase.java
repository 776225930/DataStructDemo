package com.jhao.threaddemo.vola;

import com.jhao.threaddemo.tools.SleepTools;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe Volatile的提供的可见性, 并不能保证原子性
 */
public class VolatileCase {
    public volatile static boolean ready;
    private static int number;

    private static class PrintThread extends Thread {
        @Override
        public void run() {
            System.out.println("PrintThread is running.......");
            while (!ready) ;//阻塞
            System.out.println("number = " + number);
        }
    }

    public static void main(String[] args) {
        new PrintThread().start();
        SleepTools.second(1);
        number = 51;
        ready = true;
        SleepTools.second(3);
        System.out.println("main is ended!");
    }
}
