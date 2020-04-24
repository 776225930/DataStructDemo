package com.jhao.generictype02.demo1;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class Test {


    public static void main(String[] args) {
//        scene01();
//        scene02();
//        scene03();
//        scene04();
        XiaoMingMa xiaoMingMa = new XiaoMingMa();
//        List<Double> doubleList = Arrays.asList(1.2, 2.1, 3.2);//坑爹的是Arrays有一个内部类也叫ArrayList
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.1);
        System.out.println(xiaoMingMa.sumOfList(doubleList));
    }


    public static void scene01() {
        XiaoLi xiaoLi = new XiaoLi();
        XiaoMing xiaoMing = new XiaoMing();
        XiaoMingMa xiaoMingMa = new XiaoMingMa();

        //小明给了他妈一个盘子RawPlate,什么水果都能放
        RawPlate rawPlate = xiaoMing.getPlate();
        //小明妈去装水果
        xiaoMingMa.addFruit(rawPlate);
        //小丽吃香蕉
        xiaoLi.eat((Banana) rawPlate.get());//报错Apple cannot be cast to Banana
    }

    public static void scene02() {
        XiaoLi xiaoLi = new XiaoLi();
        XiaoMing xiaoMing = new XiaoMing();
        XiaoMingMa xiaoMingMa = new XiaoMingMa();

        //假如这个盘子是智能的，能主动检测你往里面装什么
        //小明给的这个盘子只能装香蕉,方法泛型类型推断
        AIPlate<Banana> aiPlate = xiaoMing.<Banana>getAIPlate();
        //小明妈就不会装错了
        xiaoMingMa.addFruitToAI(aiPlate);
        //小丽吃香蕉
        xiaoLi.eat(aiPlate.get());//bingo
    }

    public static void scene04() {
        XiaoLi xiaoLi = new XiaoLi();
        XiaoMing xiaoMing = new XiaoMing();
        XiaoMingMa xiaoMingMa = new XiaoMingMa();

        //假如这个盘子是智能的，能主动检测你往里面装什么
        //小明给的这个盘子只能装香蕉,方法泛型类型推断
        AIPlate<Banana> aiPlate = xiaoMing.<Banana>getAIPlate();
        //小明妈就不会装错了
        xiaoMingMa.add1();//使用反射破坏泛型
        xiaoMingMa.add2(aiPlate);//使用反射破坏泛型
        //小丽吃香蕉
        xiaoLi.eat(aiPlate.get());
    }

    public static void scene03() {
        int[] ints = new int[10];
        sort(ints);
        double[] doubles = new double[10];
        sort(doubles);
        Object[] objects = new Object[10];
        sort(objects);
    }


    public static void sort(int[] array) {
    }//只能对int类型使用

    public static void sort(double[] array) {
    }//只能对double使用

    //泛型不能用于定义数组，没有范型数组，但下面使用可以
    public static <T> void sort(T[] array) {
    }

}

class Test1 {
    static class A {
    }

    static class A1 {
    }

    static interface B {
    }

    static interface C {
    }

    //        static class D<T extends B & A &C>{}//编译报错
    //具有多个限定的类型变量是范围中列出的所有类型的子类型。如果范围之一是类，则必须首先指定它
    static class D1<T extends A & B & C> {
    }//OK

    //单继承
//    static class D2<T extends  A & A1 & B & C>{}


}
