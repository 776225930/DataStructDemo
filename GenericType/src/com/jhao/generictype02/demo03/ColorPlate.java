package com.jhao.generictype02.demo03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class ColorPlate<K, T> extends BigPlate<T> {
    private List<T> items = new ArrayList<>(20);

    public ColorPlate() {

    }

    @Override
    public void set(T t) {
        items.add(t);
    }

    @Override
    public T get() {
        int index = items.size() -1;
        if(index>= 0){
            return items.get(index);
        }else{
            return null;
        }
    }

    @Override
    public String toString() {
        return "ColorPlate{" +
                "items=" + items +
                '}';
    }
}
