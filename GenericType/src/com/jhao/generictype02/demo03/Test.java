package com.jhao.generictype02.demo03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class Test {
    public static void main(String[] args) {
//        scene01();
//        scene03();
        scene05();
    }

    public static void scene01() {
        //先让几位角色上场
        XiaoMing xiaoMing = new XiaoMing();
        XiaoLi xiaoLi = new XiaoLi();
        XiaoLiMa xiaoLiMa = new XiaoLiMa();
        //小丽妈去拿苹果盘子装了些苹果
        Plate<Apple> applePlate = xiaoLiMa.getPlate();
        //小明吃苹果
        xiaoMing.eat(applePlate.get());
        Plate<? extends Fruit> fruitPlate = xiaoLiMa.getSnack(applePlate);
        //这时候小明再从盘子里面那苹果吃,发现不是苹果
//        xiaoMing.eat((Apple) fruitPlate.get());
        //实际上
        Fruit fruit = fruitPlate.get();
        Object o = fruitPlate.get();
//        Banana banana = fruitPlate.get();
    }

    public static void scene02() {
        //ColorPlate->BigPlate ->AIPlate->Plate
        //之间是子类关系
        Plate<Apple> applePlate = new AIPlate<Apple>();
        Plate<Apple> applePlate1 = new BigPlate<Apple>();
        Plate<Apple> applePlate2 = new ColorPlate<String, Apple>();
    }

    public static void scene03() {
        Plate<? super Fruit> lowerFruitPlate = new AIPlate<Food>();
        lowerFruitPlate.set(new Apple());
        lowerFruitPlate.set(new Banana());
//        lowerFruitPlate.set(new Food());

//        Fruit fruit =  lowerFruitPlate.get();
        Object object = lowerFruitPlate.get();

    }

    public static void scene04() {
        //<?> == <? extends Object>
        //不能存也不能取
        Plate<?> fruitPlate = new AIPlate<Apple>();
        Object o = fruitPlate.get();
//        fruitPlate.set(new Apple());
        fruitPlate.set(null);
    }

    public static void scene05() {

        List<Apple> src = new ArrayList<>();
        src.add(new Apple(1));
        List<Apple> dest = new ArrayList<>(10);
        dest.add(new Apple(2));
        System.out.println(dest);
        copy(dest, src);
        System.out.println(dest);

        List<Banana> src1 = new ArrayList<>();
        src1.add(new Banana(1));
        List<Banana> dest1 = new ArrayList<>();
        dest1.add(new Banana(2));
        System.out.println(dest1);
        copy1(dest1, src1);
//        copy2(dest1, src1);
        System.out.println(dest1);

        //注意是List<Fruit>
        List<Fruit> dest2 = new ArrayList<>(10);
        dest2.add(new Banana(2));
//        copy1(dest2, src1);//报错
        System.out.println(dest2);
        Test.<Banana>copy2(dest2, src1);//只可意会
        System.out.println(dest2);

        System.out.println(dest2);
//        Test.<Fruit>copy2(dest2, src1);//报错
        Test.<Fruit>copy3(dest2, src1);//ok
        System.out.println(dest2);


    }

    //一般的存、取
    public static void copy(List<Apple> dest, List<Apple> src) {
        Collections.copy(dest, src);
    }

    public static <T> void copy1(List<T> dest, List<T> src) {
        Collections.copy(dest, src);
    }

    public static <T> void copy2(List<? super T> dest, List<T> src) {
        Collections.copy(dest, src);
    }

    public static <T> void copy3(List<? super T> dest, List<? extends T> src) {
        Collections.copy(dest, src);
    }
}

