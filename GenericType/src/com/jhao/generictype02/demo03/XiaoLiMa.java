package com.jhao.generictype02.demo03;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XiaoLiMa extends Person {

    public Plate<Apple> getPlate() {
        Plate<Apple> fruitPlate = new AIPlate<>();
        fruitPlate.set(new Apple());
        return fruitPlate;
    }

    public Plate<? extends Fruit> getSnack(Plate<Apple> applePlate) {
        Plate<? extends Fruit> fruitPlate = applePlate;
        //不能存放任何元素
        try {
            //使用反射破坏
            Method set = fruitPlate.getClass().getMethod("set", Object.class);
            set.setAccessible(true);
            set.invoke(fruitPlate, new Banana());
//            set.invoke(fruitPlate, new Beef());//什么都能放了 安全没法保证
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
//            fruitPlate.set(new Apple());
//            fruitPlate.set(new Banana());
            //除了null什么也不能放
//            fruitPlate.set(null);
        }
        return fruitPlate;
    }

}
