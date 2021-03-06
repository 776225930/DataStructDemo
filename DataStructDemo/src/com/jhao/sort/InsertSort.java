package com.jhao.sort;

/**
 * @author JiangHao
 * @date 2020/3/8
 * @describe 插入排序
 */
public class InsertSort {
    public static int[] sort(int[] array) {
        if (array.length == 0 || array.length == 1) {
            return array;
        }
        int currentValue;//待排序的元素
        for (int i = 0; i < array.length - 1; i++) {
            int preIndex = i;//已排序元素(其中的最后一个元素)的位置
            currentValue = array[preIndex + 1];
            while (preIndex >= 0 && array[preIndex] > currentValue) {
                //一直循环(在已排序的元素中)找到合适的插入位置,(找到的话)刷新preIndex+1的值
                array[preIndex + 1] = array[preIndex];
                preIndex--;//
            }
            //
            array[preIndex + 1] = currentValue;

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
