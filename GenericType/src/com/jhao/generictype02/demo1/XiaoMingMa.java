package com.jhao.generictype02.demo1;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class XiaoMingMa extends Person {
    public void addFruit(RawPlate rawPlate) {
        rawPlate.set(new Apple());
    }
    /**
     * 这里不是泛型方法，这只是一个普通方法，只是使用了AIPlate<Banana>这个泛型类做形参而已
     * @param aiPlate
     */
    public void addFruitToAI(AIPlate<Banana> aiPlate) {
//        aiPlate.set(new Apple());
        aiPlate.set(new Banana());
    }


}
