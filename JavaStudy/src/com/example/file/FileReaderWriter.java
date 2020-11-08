package com.example.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author JiangHao
 * @date 2020/11/8
 * @describe
 */
public class FileReaderWriter {

    @Test
    public void test1() {
        try {
            //1.实例化File文件对象
            File file = new File("hello.txt");
            //2.提供具体的流
            FileReader fileReader = new FileReader(file);
            int read = -1;
            //read()返回一个字符
            //3.数据读取
            while ((read = fileReader.read()) != -1) {
                System.out.print((char) read);
            }
            //4.关闭流
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            //1.实例化File文件对象
            File file = new File("hello.txt");
            //2.提供具体的流
            FileReader fileReader = new FileReader(file);
            int len = -1;
            //read()返回一个字符
            char[] chars = new char[5];
            //3.数据读取
            while ((len = fileReader.read(chars)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.print(chars[i]);
                }
            }
            //4.关闭流
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            //1.实例化File文件对象
            File file = new File("hello.txt");
            //2.提供具体的流:如果文件存在会覆盖
            FileWriter fileWriter = new FileWriter(file, false);
            //3.写出：如果不存在则创建
            fileWriter.write("I am a loser");
            //关闭流
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        try {
            //1.实例化输入、输出File文件对象
            File srcFile = new File("hello.txt");
            File destFile = new File("hello1.txt");
            //2.提供具体的流:如果文件存在会覆盖
            FileReader fileReader = new FileReader(srcFile);
            FileWriter fileWriter = new FileWriter(destFile, true);
            //3.写出：如果不存在则创建
            char[] charBuffer = new char[8];
            int len = -1;
            while ((len = fileReader.read(charBuffer)) != -1) {
                fileWriter.write(charBuffer, 0, len);
            }
            //关闭流
            fileReader.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
