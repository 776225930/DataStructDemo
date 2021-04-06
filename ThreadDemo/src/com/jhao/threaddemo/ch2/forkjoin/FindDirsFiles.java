package com.jhao.threaddemo.ch2.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author JiangHao
 * @date 2021/4/6
 * @describe 遍历指定目录（含子目录）找寻指定类型文件
 */
public class FindDirsFiles extends RecursiveAction {
    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        List<FindDirsFiles> subTasks = new ArrayList<>();
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //对每个子目录都新建一个任务
                    subTasks.add(new FindDirsFiles(file));
                } else {
                    //遇到文件，检查
                    if (file.getAbsolutePath().endsWith("txt")) {
                        System.out.println("文件:" + file.getAbsolutePath());
                    }
                }
            }
            if (!subTasks.isEmpty()) {
                //在当前的ForkJoinPool 上调度所有的子任务
                for (FindDirsFiles subTask : invokeAll(subTasks)) {
                    subTask.join();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 用一个 ForkJoinPool 实例调度总任务
        ForkJoinPool pool = new ForkJoinPool();
        FindDirsFiles task = new FindDirsFiles(new File("F:/"));
        /*异步提交任务*/
        pool.execute(task);
        /*主线程做自己的业务工作*/
        System.out.println("Task is Running......");

        Thread.sleep(1);

        int otherWork = 0;
        for (int i = 0; i < 100; i++) {
            otherWork = otherWork + i;
        }
        System.out.println("Main Thread done sth......,otherWork="
                + otherWork);
        task.join();//阻塞方法
        System.out.println("Task end");
    }
}
