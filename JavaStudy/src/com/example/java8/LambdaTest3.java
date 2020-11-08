package com.example.java8;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToLongFunction;

/**
 * @author JiangHao
 * @date 2020/11/8
 * @describe Java8的四大核心函数式接口
 * <p>
 * Cousumer<T> :消费型接口
 * void accept(T t)
 * Supplier<T> :供给型接口
 * T get()
 * Function<T,R>:函数型接口
 * R apply(T t)
 * Predicate<T>:断言型接口
 * boolean test(T t)
 */
public class LambdaTest3 {
    /**
     * 消费型接口
     */
    @Test
    public void test1() {
        happy(10000, d -> {
            System.out.println("消费了：" + d);
        });
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    /**
     * 供给型接口
     */
    @Test
    public void test2() {
        List<Integer> supply = supply(10, () -> (int) (Math.random() * 100));
        supply.stream().forEach(System.out::println);
    }

    public List<Integer> supply(int count, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * 函数型接口
     */
    @Test
    public void test3() {
        handleString("hello loser", (s) -> {
            System.out.println(s.toUpperCase());
            System.out.println(s);
            return s.toUpperCase();
        });
    }

    public String handleString(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    /**
     * 断言型接口
     */
    @Test
    public void test4() {
        List<String> list = Arrays.asList("123", "23223432", "323", "sdsxfsdfs", "21312312", "aaaaaa");
        List<String> predicate = predicate(list, (s) -> {
            return s.length() > 3;
        });
        predicate.stream().forEach(System.out::println);
    }

    public List<String> predicate(List<String> list, Predicate<String> predicate) {
        List<String> list1 = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                list1.add(s);
            }
        }
        return list1;
    }
}
