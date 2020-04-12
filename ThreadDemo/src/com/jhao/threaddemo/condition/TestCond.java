package com.jhao.threaddemo.condition;

import com.jhao.threaddemo.waitandnotify.Express;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe 测试Lock和Condition实现等待通知
 */
public class TestCond {
    private static ExpressCond expressCond = new ExpressCond(0, ExpressCond.CITY);

    /**
     * 检查里程数变化的线程,不满足条件，线程一直等待
     */
    private static class CheckKm extends Thread {
        @Override
        public void run() {
            expressCond.waitKm();
        }
    }


    /**
     * 检查地点变化的线程,不满足条件，线程一直等待
     */
    private static class CheckSite extends Thread {
        @Override
        public void run() {
            expressCond.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }
        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }
        Thread.sleep(1000);
        expressCond.changeKm();
    }
}
