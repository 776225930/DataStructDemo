package com.jhao.sort;

import java.util.Arrays;


/**
 * @author JiangHao
 * @date 2020/3/10
 * @describe
 */
public class MergeSort {
    public static int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        //切分的位置
        int mid = array.length / 2;
        //左闭右开
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(sort(left), sort(right));

    }

    /**
     * 合并左右排好序的子数组
     *
     * @param left
     * @param right
     */
    private static int[] merge(int[] left, int[] right) {
        //存放结果
        int[] result = new int[left.length + right.length];
        //
        for (int index = 0, leftIndex = 0, rightIndex = 0; index < result.length; index++) {
            if (leftIndex >= left.length) {//左边数组已经取完
                result[index] = right[rightIndex++];
            } else if (rightIndex >= right.length) {
                result[index] = left[leftIndex++];
            } else if (left[leftIndex] > right[rightIndex]) {
                result[index] = right[rightIndex++];
            } else {
                result[index] = left[leftIndex++];
            }
        }
        return result;
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
