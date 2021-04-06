package com.jhao.threaddemo.ch2.forkjoin.sort;

import java.util.Arrays;

/**
 * @author JiangHao
 * @date 2021/4/6
 * @describe 归并排序
 */
public class MergeSort {

    public static int[] sort(int[] array) {
        if (array.length <= MakeArray.THRESHOLD) {
            //使用插入后排序
            return InsertionSort.sort(array);
        } else {
            //切分数组。然后递归调用
            int mid = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);
            return merge(sort(left), sort(right));
        }
    }

    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     *
     * @param left  经过排序后的左子数组
     * @param right 经过排序后的右子数组
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                //左边数组已经取完，完全取右边数组的值即可(已经排好序，直接取值就行)
                result[index] = right[j++];
            } else if (j >= right.length) {
                //右边数组已经取完，完全取左边的数组的值即可(已经排好序，直接取值就行)
                result[index] = left[i++];
            }
            //排序刚开始
            else if (left[i] > right[j]) {
                //左边数组的元素值大于右边数组的值,取右边数组的值(已经排好序，直接取值就行)
                result[index] = right[j++];
            } else {
                //右边数组元素的值大于左边数组，取左边元素的值(已经排好序，直接取值就行)
                result[index] = left[i++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("============================================");
        long start = System.currentTimeMillis();
        MergeSort.sort(MakeArray.makeArray());
        System.out.println(" spend time:" + (System.currentTimeMillis() - start) + "ms");
    }
}
