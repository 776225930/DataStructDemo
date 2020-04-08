package com.jhao.generic;

import java.util.ArrayList;

/**
 * @author JiangHao
 * @date 2020/3/12
 * @describe 类型变量的限定-方法上
 */
public class ArrayAlg {

    public static <T extends ArrayList & Comparable> T min(T a, T b) {
        if (a.compareTo(b) > 0) {
            return a;
        } else {
            return b;
        }

    }
}
