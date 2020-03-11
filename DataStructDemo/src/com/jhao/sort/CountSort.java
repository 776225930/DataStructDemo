package com.jhao.sort;

import java.util.Arrays;

import javax.swing.event.DocumentEvent;

import sun.applet.Main;

/**
 * @author JiangHao
 * @date 2020/3/11
 * @describe
 */
public class CountSort {
    public static int[] sort(int[] array) {
        if (array.length < 1) {
            return null;
        }

        int min = array[0], max = array[0];
        //偏移量，用于定位原始数组每个元素在技术数组中的下标位置
        int bias;
        //寻找数组中最大值、最小值
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        bias = 0 - min;
        //计算计数数组的容量
        int[] counterArray = new int[max - min + 1];
        Arrays.fill(counterArray, 0);
        //遍历整个原始数组，将原始数组中的每一个元素值转化为计数数组下标
        //并将计数数组下标对应的元素值大小进行累加
        for (int i = 0; i < array.length; i++) {
            counterArray[array[i] + bias]++;
        }
        //
        System.out.println("计数数组为:");
        for (int i = 0; i < counterArray.length; i++) {
            System.out.print(counterArray[i] + "-");
        }
        System.out.println();
        System.out.println("***************************");
        System.out.println(" 偏移量 == " + bias + " ; min == " + min + " ; max == " + max);
        System.out.println();
        //访问原始数组是的下标计数器
        int index = 0;
        //访问计数数组的下标指示器
        int i = 0;
        //访问计数数组时，将计数数组中的元素转换后，重新写回原始数组
        while (index < array.length) {
            if (counterArray[i] > 0) {
                array[index] = i - bias;
                counterArray[i]--;
                index++;
            } else {
                i++;
            }
            for (int j = 0; j < counterArray.length; j++) {
                System.out.print(counterArray[j] + " ");
            }
            System.out.println();
            System.out.println("    -------------------");
            for (int j = 0; j < counterArray.length; j++) {
                System.out.print(array[j] + " ");
            }
            System.out.println();
            System.out.println("==============================");
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {5, 4, 5, 3, 3, 6, 9, 1, 4, 4, 3, 3};
        int[] sort = sort(array);
        for (int i = 0; i < sort.length; i++) {
            System.out.print(sort[i] + ", ");
        }
    }
}
