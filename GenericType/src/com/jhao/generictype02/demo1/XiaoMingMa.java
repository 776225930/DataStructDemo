package com.jhao.generictype02.demo1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

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
//            set.invoke(fruitPlate, new Apple());//在拿出来时（xiaoLi.eat(aiPlate.get())）一旦类型不能转换则报异常
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

    public double sumOfList(List<? extends Number> list) {
        System.out.println("sumOfList " + list.getClass());
        //java.lang.UnsupportedOperationException
//        list.add(null);
        System.out.println("list: " + list.getClass());
        //副作用
//        list.add(1.1); //上限 in  只读，但这不是严格限制
        //反射调用 最新的不能调用
        // Caused by: java.lang.UnsupportedOperationException
        Class<?> clazz = list.getClass();
        System.out.println(clazz);

        try {
            Method addMethod = clazz.getMethod("add", Object.class);
            addMethod.setAccessible(true);
            addMethod.invoke(list, 1000);
            System.out.println("list: " + list.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        double s = 0.0;
        for (Number n : list) {
            s += n.doubleValue();
        }
        return s;

    }

}
