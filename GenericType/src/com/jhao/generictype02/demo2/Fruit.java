package com.jhao.generictype02.demo2;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class Fruit implements Comparable<Fruit> {

    int price = 0;

    @Override
    public String toString() {
        return "Fruit{" +
                "price=" + price +
                '}';
    }

    @Override
    public int compareTo(Fruit fruit) {
        return 0;
    }
}
