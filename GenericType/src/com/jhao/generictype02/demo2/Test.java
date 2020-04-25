package com.jhao.generictype02.demo2;


import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class Test {
    public static void main(String[] args) {

//        scene01();
        scene04();
    }

    public static void scene01() {
        ArrayList<Apple> apples = new ArrayList<>();
        ArrayList<Banana> bananas = new ArrayList<>();
        ArrayList<Fruit> fruits = new ArrayList<>();
//        fruits = bananas;//使用泛型后马上检查出来
        System.out.println(apples.getClass() == bananas.getClass());//true
    }

    /**
     * 泛型不能使用基本类型
     */
    public static void scene02() {
//        ArrayList<int> ints = new ArrayList<int>();
        ArrayList<Integer> integers = new ArrayList<>();
    }

    public static void scene03() {
        ArrayList<String> strings = new ArrayList<>();
        if (strings instanceof ArrayList<?>) {
        }
//        if (strings instanceof ArrayList<String>){}
    }

    public static void scene04() {
//        Plate<Apple>[] applePlates = new Plate<Apple>[10];//不允许
//          T[] arr=new T[];//不允许
        Apple[] apples = new Apple[10];
        Fruit[] fruits = new Fruit[10];
        System.out.println("==> " + apples.getClass());//class [Lcom.jhao.generictype02.demo2.Apple;
        System.out.println("==> " + fruits.getClass());//class [Lcom.jhao.generictype02.demo2.Fruit;
        fruits = apples;//赋值后fruits在运行时实际要放的是Apple
        //fruits里面原本放的是Fruit类型
//        fruits[0] = new Banana();//编译通过，运行报ArrayStoreException
        //Fruit是Apple的父类，Fruit[]是Apple[]的父类，这就是数组的协变
        //如果加入泛型后，由于擦除机制，运行时将无法知道数组的类型
        Plate<?>[] plates = new Plate<?>[10];//这是可以的

    }

}

class Test2 {

    public static <E> void appendList(List<E> list) {
        //无法创建一个类型参数的实例。例如，下面代码就会引起编译时错误：
//        E elem = new E();
//        list.add(elem);
    }

    //通过反射创建一个参数化类型的实例
    public static <E> void append(List<E> list, Class<E> cls) throws Exception {
        E elem = cls.newInstance();   // OK
        list.add(elem);
    }

}

class Test3<T> {
//    public static T one;
//    public static T test(T t);

    public static <T> T test(T t) {
        return t;
    }
}

class ViewGroup {
}

class LinearLayout extends ViewGroup {
}

class Test01 {

    public <T extends ViewGroup> T get() {//CAP#1
        return (T) new LinearLayout();
    }
}