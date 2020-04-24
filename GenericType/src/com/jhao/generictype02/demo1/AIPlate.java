package com.jhao.generictype02.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class AIPlate<T> implements Plate<T> {

    private List<T> items = new ArrayList<T>(10);//1.7之前
//    private List<T> items1 = new ArrayList<>(10);//1.7之后


    public AIPlate() {
    }

    public AIPlate(T t) {
        items.add(t);
    }

    @Override
    public void set(T t) {
        items.add(t);
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
        return "AIPlate{" +
                "items=" + items +
                '}';
    }
}

class Plate1<T> implements Plate<T> {

    @Override
    public void set(T t) {

    }

    @Override
    public T get() {
        return null;
    }

}
