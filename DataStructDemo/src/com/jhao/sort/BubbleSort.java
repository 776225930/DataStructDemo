package com.jhao.sort;

/**
 * @author JiangHao
 * @date 2020/3/8
 * @describe
 */
public class BubbleSort {

    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        //每完成一次循环都会有一个元素到达该到的位置，下一次就不必再参加循环
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
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
