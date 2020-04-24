package com.jhao.generictype02.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe 普通盘子，没有使用泛型
 */
public class RawPlate implements Plate {

    private List items = new ArrayList(10);

    public RawPlate() {

    }

    @Override
    public void set(Object fruit) {
        //没有泛型约束,随便添加任意类型
        items.add(fruit);
    }

    @Override
    public Fruit get() {
        int index = items.size() - 1;
        if (index >= 0) {
            //没有使用泛型,必须强制转换返回结果
            return (Fruit) items.get(index);
        } else {

            return null;
        }
    }

    @Override
    public String toString() {
        return "RawPlate{" +
                "items=" + items +
                '}';
    }
}
