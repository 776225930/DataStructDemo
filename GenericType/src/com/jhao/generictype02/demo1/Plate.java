package com.jhao.generictype02.demo1;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public interface Plate<T> {

    void set(T t);

    T get();
}
