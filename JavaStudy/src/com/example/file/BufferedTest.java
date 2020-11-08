package com.example.file;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author JiangHao
 * @date 2020/11/8
 * @describe
 */
public class BufferedTest {

    /**
     * 实现非文本文件复制
     */
    @Test
    public void test1() {

        try {
            File srcFile = new File("301387.jpg");
            File destFile = new File("dest.jpg");
            InputStream inputStream = new FileInputStream(srcFile);
            OutputStream outputStream = new FileOutputStream(destFile);

            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            byte[] buffer = new byte[1024];
            int len = -1;
            long start = System.currentTimeMillis();
            while ((len = bufferedInputStream.read(buffer, 0, buffer.length)) != -1) {
                bufferedOutputStream.write(buffer, 0, buffer.length);
            }
            System.out.println(System.currentTimeMillis() - start);
            bufferedInputStream.close();
            inputStream.close();
            bufferedOutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
