package com.jhao.generictype02.demo1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
     *
     * @param aiPlate
     */
    public void addFruitToAI(AIPlate<Banana> aiPlate) {
//        aiPlate.set(new Apple());
        aiPlate.set(new Banana());
    }

    public void add1() {

        try {
            Plate<? extends Fruit> fruitPlate = new AIPlate<Apple>(new Apple());
//        fruitPlate.set(new Apple());//不能存
            fruitPlate.set(null);//只能存null
            Method setMethod = fruitPlate.getClass().getMethod("set", Object.class);
            setMethod.setAccessible(true);
//            setMethod.invoke(fruitPlate, new Banana());
            setMethod.invoke(fruitPlate, new Apple());
//            setMethod.invoke(fruitPlate, new Object());//一旦无法转换就会抛异常
            Fruit fruit = fruitPlate.get();
            System.out.println("fruit :" + fruit);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void add2(Plate<? extends Fruit> fruitPlate) {

        try {
            Method set = fruitPlate.getClass().getMethod("set", Object.class);
            set.setAccessible(true);
//            set.invoke(fruitPlate, new Apple());//
            set.invoke(fruitPlate, new Banana());//
            Fruit fruit = fruitPlate.get();
            System.out.println(fruit);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
