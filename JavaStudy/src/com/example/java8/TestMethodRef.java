package com.example.java8;


import org.junit.Test;

import java.io.PrintStream;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author JiangHao
 * @date 2020/11/8
 * @describe
 * 一.方法引用：若Lambda体重的内容有方法已经实现了我们可以使用“方法引用”
 * <p>
 * 主要有三种语法格式:
 * 对象::实例方法名
 * <p>
 * 类::静态方法名
 * <p>
 * 类::实例方法名：若Lambda 参数列表第一个参数是实例方法的调用者，第二个参数是实例方法的参数时
 * <p>
 *     注意：
 *   	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 *   	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 * 二.构造器引用:注意需要调用的构造器参数列表要和函数式接口中抽象方法的参数列表一致
 * <p>
 * 三.数组引用
 * Type::new
 */
public class TestMethodRef {


    @Test
    public void test1() {
        PrintStream printStream = System.out;
        Consumer<String> consumer = (s) -> printStream.println(s);
        consumer.accept("hello !");
        Consumer<String> consumer1 = printStream::println;
        consumer1.accept("say hello !");
    }

    //对象的引用 :: 实例方法名
    @Test
    public void test2() {
        Employee employee = new Employee();
        Supplier<String> supplier = () -> employee.getName();
        System.out.println(supplier.get());

        Supplier<String> supplier1 = employee::getName;
        System.out.println(supplier1.get());
    }

    //类名 :: 静态方法名
    @Test
    public void test3() {

        BiFunction<Double, Double, Double> fun = (x, y) -> Math.max(x, y);
        System.out.println(fun.apply(1D, 3D));
        System.out.println("----------------------------------------------");

        BiFunction<Double, Double, Double> function = Math::max;
        System.out.println(function.apply(1D, 3D));

    }

    /**
     * 类名::实例方法
     */
    @Test
    public void test4() {
        BiPredicate<String, String> biPredicate = (s1, s2) -> {
            return s1.equals(s2);
        };
        System.out.println(biPredicate.test("a", "b"));
        System.out.println("----------------------------------");
        //第二个参数是第一个参数调用的方法的参数时才可以
        BiPredicate<String, String> biPredicate1 = String::equals;
        System.out.println(biPredicate1.test("a", "a"));

    }

    /**
     * 构造器引用
     */
    @Test
    public void test5() {
        Supplier<Employee> supplier = () -> new Employee();
        Supplier<Employee> supplier1 = Employee::new;
    }

    /**
     * 构造器引用
     */
    @Test
    public void test6() {
        Function<Integer, Employee> fun = (x) -> new Employee(x);
        BiFunction<Integer, String, Employee> biFunction = (age, name) -> new Employee();

    }

    /**
     * 数组引用
     */
    @Test
    public void test7() {
        Function<Integer, String[]> function = (x) -> {
            return new String[x];
        };
        Function<Integer, String[]> function1 = (x) -> new String[x];

        Function<Integer, String[]> function2 = String[]::new;

    }

}
