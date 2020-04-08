package com.jhao.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiangHao
 * @date 2020/3/12
 * @describe 不使用泛型
 */
public class NoGeneric {

    public int addInt(int x, int y) {
        return x + y;
    }

    public float addFloat(float x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("mark");
        list.add("hehe");
        list.add(123);
        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i);//转换出错
            System.out.println(name);
        }
    }
}
