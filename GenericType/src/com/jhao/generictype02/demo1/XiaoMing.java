package com.jhao.generictype02.demo1;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class XiaoMing extends Person {


    public RawPlate getPlate() {
        return new RawPlate();
    }

    public <T> AIPlate<T> getAIPlate() {
        return new AIPlate<T>();
    }

}
