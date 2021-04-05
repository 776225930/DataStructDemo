package com.jhao.threaddemo.ch1.threadLocal;

import com.jhao.threaddemo.tools.SleepTools;

/**
 * @author JiangHao
 * @date 2021/4/6
 * @describe ThreadLocal的线程不安全演示 , 原因: 使用了共享变量 static ...
 */
public class ThreadLocalUnsafe  implements Runnable{
//    public static Number number = new Number(0);
    public  Number number = new Number(0);

    public ThreadLocalUnsafe() {
    }

    @Override
    public void run() {
        //每个线程计数加一
        number.setNum(number.getNum()+1);
        //将其存储到ThreadLocal中
        value.set(number);
        SleepTools.ms(2);
        //输出num值
        System.out.println(Thread.currentThread().getName()+"="+value.get().getNum());
    }

    public static ThreadLocal<Number> value = new ThreadLocal<Number>() {
    };

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadLocalUnsafe()).start();
        }
    }

    private static class Number {
        public Number(int num) {
            this.num = num;
        }

        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Number [num=" + num + "]";
        }
    }
}
