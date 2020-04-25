package com.jhao.generictype02.demo03;


/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class Banana extends Fruit {
    private int id;
    public Banana() {
    }

    public Banana(int id) {
          this.id=id;
    }

    @Override
    public String toString() {
        return "Banana"+id;
    }
}
