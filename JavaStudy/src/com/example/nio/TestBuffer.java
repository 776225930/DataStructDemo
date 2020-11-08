package com.example.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author JiangHao
 * @date 2020/11/9
 * @describe 一、缓冲区(Buffer):在Java NIO中负责数据的存取。缓存就是数组。用于存储不同类型的数据
 * 根据不同数据类型(boolean 除外)，提供了相应类型的缓冲区:
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntegerBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 二、缓冲区存取数据的核心方法
 * put():存入缓冲区中
 * get():获取缓冲区中的数据
 * 三、缓冲区四个核心属性
 * capacity:容量，表述缓冲区中最大存数数据的容量。一旦申明不能改变
 * limit:界限，缓冲区中可以操作的数据大小。后边不能读写
 * position：位置，缓冲区中正在操作数据的位置。
 * 0<=mark<=position<=limit<=capacity
 */
public class TestBuffer {

    @Test
    public void test1() {
        //1.分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //2.存入数据
        buf.put("hello".getBytes());
        //3.切换到读模式 limit 变为上一次的position
        buf.flip();
        //4.读取缓冲区数据
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        System.out.println(bytes.toString());
        System.out.println("-----------allocate----------");

        //可重复读
        buf.rewind();
        //清空缓冲区,但是缓冲区中数据还存在，只是出于遗忘状态
        buf.clear();


        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());


    }

    @Test
    public void test2() {

        String str = "abcde";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();
        //读取缓冲区数据
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes, 0, 2);
        System.out.println(new String(bytes));

        //标记
        buf.mark();
        //
        buf.get(bytes, 2, 2);
        System.out.println(new String(bytes));
        //position 恢复到mark的位置
        buf.reset();

        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        //缓冲区中是否还有可以操作的数据
        if (buf.hasRemaining()){
            System.out.println(buf.remaining());
        }


    }
}
