package com.jhao.generictype02.demo2;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public interface Plate<T> {

    public void set(T t);

    public T get();
}
