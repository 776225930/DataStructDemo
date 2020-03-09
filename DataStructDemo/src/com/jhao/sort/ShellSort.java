package com.jhao.sort;

/**
 * @author JiangHao
 * @date 2020/3/9
 * @describe 希尔排序
 */
public class ShellSort {

    public static int[] sort(int[] array) {
        if (array.length == 0 || array.length == 1) {
            return array;
        }
        int len = array.length;
        int gap = len / 2;//增量
        //组内待排序的数据
        int currentValue;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                currentValue = array[i];
                int preIndex = i - gap;
                //组内排序
                while (preIndex >= 0 && array[preIndex] > currentValue) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex = preIndex - gap;
                }
                array[preIndex + gap] = currentValue;
            }

            gap = gap / 2;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {86, 11, 77, 23, 32, 45, 58, 63, 93, 4, 37, 22};
        System.out.println(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("=========================================");
        int[] sortedArray = sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(sortedArray[i] + " ");
        }
    }
}
