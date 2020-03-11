package com.jhao.sort;

/**
 * @author JiangHao
 * @date 2020/3/10
 * @describe 快速排序
 */
public class QuickSort {


    public static int[] sort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end > array.length || start > end) {
            return null;
        }
        //分割指示器
        int zoneIndex = partition(array, start, end);

        if (zoneIndex > start) {
            sort(array, start, zoneIndex - 1);
        }
        if (zoneIndex < end) {
            sort(array, zoneIndex + 1, end);
        }
        return array;
    }

    private static int partition(int[] array, int start, int end) {
        //随机取一个基准点
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int zoneIndex = start - 1;
        //基准数和尾元素进行交换
        swap(array, pivot, end);
        for (int i = start; i <= end; i++) {
            if (array[i] <= array[end]) {
                //分割指示器向右移动一位
                zoneIndex++;
                if (i > zoneIndex) {
                    swap(array, i, zoneIndex);
                }
            }
        }
        return zoneIndex;
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {86, 11, 77, 23, 32, 45, 58, 63, 93, 4, 37, 22};
        System.out.println(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("=========================================");
        int[] sortedArray = sort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(sortedArray[i] + " ");
        }
    }
}
