package com.jhao.generic;

/**
 * @author JiangHao
 * @date 2020/3/12
 * @describe
 */
public class GenericMethod {

    public <T> T genericMethod(T... a) {
        return a[1];
    }
}
