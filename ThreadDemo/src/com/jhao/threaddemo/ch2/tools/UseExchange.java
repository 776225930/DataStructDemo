package com.jhao.threaddemo.ch2.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

import javax.sound.midi.Soundbank;

/**
 * @author JiangHao
 * @date 2021/4/7
 * @describe Exchange用法
 */
public class UseExchange {

    private static final Exchanger<Set<String>> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            Set<String> setA = new HashSet<String>();
            try {
                /**
                 * 添加数据
                 * set.add(...)
                 */
                setA.add("aaaaa");
                setA.add("bbbbb");
                setA.add("eeeee");
                setA.add("fffff");
                setA = EXCHANGER.exchange(setA);//交换set
                //处理交换后的数据
                for (String s:setA){
                    System.out.println("setA :"+s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            Set<String> setB = new HashSet<String>();//存放数据的容器
            try {
                /*添加数据
                 * set.add(.....)
                 * set.add(.....)
                 * */
                setB.add("ccccc");
                setB.add("ddddd");
                setB = EXCHANGER.exchange(setB);//交换set
                /*处理交换后的数据*/
                for (String s:setB){
                    System.out.println("setB :"+s);
                }
            } catch (InterruptedException e) {
            }
        }).start();
    }


}
