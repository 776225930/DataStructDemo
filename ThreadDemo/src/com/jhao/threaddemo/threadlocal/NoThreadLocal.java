package com.jhao.threaddemo.threadlocal;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe
 */
public class NoThreadLocal {
    static Integer count = new Integer(1);

    public void startThreadyArray() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(new TestTask(i));
        }
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();
        }
    }

    private static class TestTask implements Runnable {
        private int id;

        public TestTask(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            count = count + id;
            System.out.println(Thread.currentThread().getName() + ":"
                    + count);
        }
    }

    public static void main(String[] args) {
        NoThreadLocal noThreadLocal = new NoThreadLocal();
        noThreadLocal.startThreadyArray();
    }
}
