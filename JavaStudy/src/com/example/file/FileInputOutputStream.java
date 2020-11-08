package com.example.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author JiangHao
 * @date 2020/11/8
 * @describe
 */
public class FileInputOutputStream {

    @Test
    public void tset1() {

        //1.造文件
        File file = new File("hello.txt");
        //2.造流
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            //3.读取
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                System.out.print(new String(buffer, 0, buffer.length));
            }
            //4.关闭资源
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }

    @Test
    public void tset2() {

        //1.造文件
        File srcFile = new File("301387.jpg");
        File destFile = new File("3013871.jpg");
        //2.造流
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(srcFile);
            outputStream = new FileOutputStream(destFile);
            //3.读取
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
//                System.out.print(new String(buffer,0,buffer.length));
                outputStream.write(buffer, 0, buffer.length);
            }
            //4.关闭资源
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }
}
