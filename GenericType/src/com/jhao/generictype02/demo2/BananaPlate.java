package com.jhao.generictype02.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class BananaPlate implements Plate<Banana> {

    private List<Banana> items = new ArrayList<>(10);

    @Override
    public void set(Banana banana) {

    }

    @Override
    public Banana get() {
        return items.get(0);
    }
}
