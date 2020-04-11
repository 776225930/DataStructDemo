package com.jhao.threaddemo.waitandnotify;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe 快递实体类
 */
public class Express {
    public final static String CITY = "ShangHai";
    //快递运输里程数
    private int km;
    //快递到达地点
    private String site;

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /**
     * 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理
     */
    public synchronized void changeKm() {
        this.km = 101;
        notify();
    }

    /**
     * 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理
     */
    public synchronized void changeSite() {
        this.site = "BeiJing";
        notify();
    }

    public synchronized void waitKm() {
        while (this.km < 100) {
            try {
                System.out.println(" I will wait ");
                //wait()被notify()后，接着执行wait()后面的语句
                wait();
                System.out.println(" check km thread[" + Thread.currentThread().getName() + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("this km is " + km + ", I will change db. ");
    }

    public synchronized void waitSite() {
        while (CITY.equals(this.site)) {
            try {
                wait();
                System.out.println("check site thread[" + Thread.currentThread().getName() + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("this site is " + site + ", I will change db. ");
    }

}
