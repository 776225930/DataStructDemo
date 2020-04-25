package com.jhao.generictype02.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class ConditionalPlate<T> implements Plate<T> {

    private List<T> items = new ArrayList<T>(10);

    public ConditionalPlate() {

    }

    @Override
    public void set(T t) {

    }

    @Override
    public T get() {
        int index = items.size() - 1;
        if (index >= 0) {
            return items.get(index);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "ConditionalPlate{" +
                "items=" + items +
                '}';
    }
//    @Override
//    public boolean equals(T t) {
//        return super.equals(t);
//    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
