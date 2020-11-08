package com.example.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author JiangHao
 * @date 2020/11/8
 * @describe
 */
public class FileTest {

    @Test
    public void test1() {
        //相对于当前module
        File file = new File("hello.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getParent());
        System.out.println(file.getParentFile());
        System.out.println(file.getName());
        System.out.println(file.lastModified());
        System.out.println(file.length());
        //D:\IdeaProjects\MyDemo\JavaStudy\hello.txt
        //此时仅存在内存中
        System.out.println(file.getAbsolutePath());

        File file1 = new File("D:\\IdeaProjects\\MyDemo\\JavaStudy\\", "fileDir");

        File file2 = new File(file1, "hello2.txt");
    }

    /**
     * File.list()
     * File.listFiles()
     */
    @Test
    public void test2() {
        File file = new File("D:\\IdeaProjects\\MyDemo\\JavaStudy\\");
        for (File f : file.listFiles()) {
            System.out.println(f);
        }
        System.out.println("------------------------------------------------");
        for (String f : file.list()) {
            System.out.println(f);
        }
    }

    @Test
    public void test3() {
        File file = new File("hello1.txt");
        File file1 = new File("D:\\IdeaProjects\\MyDemo\\JavaStudy\\hello.txt");
        //file必须存在,file1不能存在
        //PS 可以通过renameTo实现快速移动文件
        boolean b = file.renameTo(file1);
        System.out.println(b);

    }

    @Test
    public void test4() {
        File file = new File("hello.txt");
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.canRead());
        System.out.println(file.exists());
        System.out.println(file.canWrite());
        System.out.println(file.isHidden());

    }


    @Test
    public void test5() {
        File file = new File("hello1.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
            System.out.println("删除成功");
        }

    }
}
