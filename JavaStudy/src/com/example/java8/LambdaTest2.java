package com.example.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author JiangHao
 * @date 2020/11/8
 * @describe lambda需要函数式接口支持
 */
public class LambdaTest2 {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    @Test
    public void test1() {
        Collections.sort(emps, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });
//        Collections.sort(emps, (e1, e2) -> {
//            return e1.getName().compareTo(e2.getName());
//        });
        Collections.sort(emps, Comparator.comparing(Employee::getId));
        for (Employee e : emps) {
            System.out.println(e);
        }
    }

    @Test
    public void test2() {
    }

    @Test
    public void test3() {
    }

}
