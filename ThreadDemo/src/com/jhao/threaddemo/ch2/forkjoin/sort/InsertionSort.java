package com.jhao.threaddemo.ch2.forkjoin.sort;

/**
 * @author JiangHao
 * @date 2021/4/6
 * @describe 简单插入排序（升序）
 */
public class InsertionSort {

    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        int currentValue;
        for (int i = 0; i < array.length - 1; i++) {
            int preIndex = i;//已被排序数据的索引
            //插入的数
            currentValue = array[preIndex + 1];
            //如果插入的数比被插入的数小
            while (preIndex >= 0 && currentValue < array[preIndex]) {
                //将array[preIndex]向后移
                array[preIndex + 1] = array[preIndex];
                //将 index 向前移
                preIndex--;
            }
            //将插入的数放入合适的位置
            array[preIndex + 1] = currentValue;
        }
        return array;
    }


    public static void main(String[] args) {
        System.out.println("============================================");
        InsertionSort.sort(MakeArray.makeArray());
    }
}
