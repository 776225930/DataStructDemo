package com.jhao.sort;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author JiangHao
 * @date 2020/3/12
 * @describe 桶排序
 */
public class BucketSort {
    /**
     * @param array
     * @param bucketSize BucketSize，作为每个桶所能放置多少个不同数值
     *                 （例如当BucketSize==5时，该桶可以存放｛1,2,3,4,5｝这几种数字，
     *                 但是容量不限，即可以存放100个3）；
     * @return
     */
    public static ArrayList<Integer> sort(ArrayList<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2) {
            return array;
        }
        int max = array.get(0), min = array.get(0);
        //找到最大值和最小值
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
            }
            if (array.get(i) < min) {
                min = array.get(i);
            }
        }
        //获得桶的数量
        int bucketCount = (max - min) / bucketSize + 1;
        //构建桶
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArr = new ArrayList<>();
        //初始化
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<>());
        }
        //将原始数组的数据分配到桶中
        for (int i = 0; i < array.size(); i++) {
            bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
        }
        //查看桶中的数据分布
        for (int i = 0; i < bucketArr.size(); i++) {
            System.out.print("第" + i + "个桶包含的数据 :");
            for (int j = 0; j < bucketArr.get(i).size(); j++) {
                System.out.println(" " + bucketArr.get(i).get(j));
            }
        }
        //取出数据
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSize == 1) {
                for (int j = 0; j < bucketArr.get(i).size(); j++) {
                    resultArr.add(bucketArr.get(i).get(j));
                }
            } else {
                if (bucketCount == 1) {
                    bucketSize--;
                }
                //对桶中的数据再次用通进行排序
                ArrayList<Integer> temp = sort(bucketArr.get(i), bucketSize);
                for (int j = 0; j < temp.size(); j++) {
                    resultArr.add(temp.get(j));
                }
            }
        }
        return resultArr;
    }

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(86);
        array.add(11);
        array.add(77);
        array.add(23);
        array.add(32);
        array.add(45);
        array.add(58);
        array.add(63);
        array.add(93);
        array.add(4);
        array.add(37);
        array.add(22);
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + ", ");
        }
        System.out.println("============================================");
        ArrayList<Integer> dest = BucketSort.sort(array, 2);
        for (int i = 0; i < dest.size(); i++) {
            System.out.print(dest.get(i) + ", ");
        }
    }

}
