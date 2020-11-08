package com.example.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sound.midi.Soundbank;

/**
 * @author JiangHao
 * @date 2020/11/8
 * @describe 一、Stream API 的操作步骤：
 * <p>
 * 1. 创建 Stream
 * <p>
 * 2. 中间操作
 * <p>
 * 3. 终止操作(终端操作)
 */
public class StreamTest {


    //创建Stream
    @Test
    public void test1() {
        //1.可以Collection系列集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        //2.t通过Arrays中的静态方法stream
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);
        //3.通过Stream类中的静态方法of
        Stream<String> stream2 = Stream.of("a", "b", "c");
        //4.创建无限流
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        //迭代
        stream3.limit(10).forEach(System.out::println);
        //生成
        Stream.generate(() -> Math.random())
                .limit(10)
                .forEach(System.out::println);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    //中间操作
    /*
	  筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */
    @Test
    public void test2() {
        //中间操作
        Stream<Employee> employeeStream = emps.stream()
                .filter((e) -> {
                    System.out.println("中间操作");
                    return e.getAge() > 20;
                });
        //终止操作:只有出发了终止操作才会开始执行中间操作返回结果
        //内部迭代、惰性求值
        employeeStream.forEach(System.out::println);
    }

    @Test
    public void test3() {
        emps.stream()
                .filter(e -> {
                    System.out.println("短路");
                    return e.getAge() > 10;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        emps.stream()
                .filter(e -> {
                    System.out.println("短路");
                    return e.getAge() > 10;
                })
                //跳过
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test5() {
        emps.stream()
                .filter(e -> {
                    System.out.println("短路");
                    return e.getAge() > 10;
                })
                //跳过
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test6() {
        List<String> list = new ArrayList<>();
        emps.stream().map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("----------------------");
        List<String> list1 = Arrays.asList("a", "cc", "bb");
        Stream<Stream<Character>> streamStream = list1.stream().map(StreamTest::filterCharacter);

        streamStream.forEach((s) -> {
            s.forEach(System.out::print);
        });
        //flatMap简化
        System.out.println();
        System.out.println("----------------------");
        Stream<Character> characterStream = list1.stream().flatMap(StreamTest::filterCharacter);
        characterStream.forEach(System.out::print);

    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> characters = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            characters.add(ch);
        }
        return characters.stream();
    }

    /**
     * 排序
     * 自然排序按照Comparable排序
     * 定制排序:(Comparator)
     */
    @Test
    public void test7() {
        List<Integer> list = Arrays.asList(6, 9, 1, 2, 5, 3, 8, 4, 5, 5, 6);
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("----------------------------------");
        emps.stream().sorted((e1, e2) -> {
            if (e1.getAge().equals(e2.getAge())) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }


    /**
     * 终止操作
     */
    @Test
    public void test8() {
        //检查是否所有元素都匹配
        boolean b = emps.stream().allMatch(e -> e.getAge() > 1);
        System.out.println(b);
        //检查元素中至少有一个元素满足
        boolean b1 = emps.stream().anyMatch(e -> e.getAge() > 60);
        System.out.println(b1);
        //是否没有匹配所有元素
        boolean b2 = emps.stream().noneMatch(e -> e.getAge() > 60);
        System.out.println(b2);
        //获取排序后的第一个元素
        Optional<Employee> first = emps.stream()
                .sorted((e1, e2) -> e1.getAge().compareTo(e2.getAge()))
                .findFirst();
        System.out.println(first.orElse(null));
        //返回流中任意元素
        Optional<Employee> any = emps.stream().filter(e -> e.getSalary() > 9000)
                .findAny();
        System.out.println(any.orElse(null));

        long count = emps.stream().filter(e -> e.getAge() > 20).count();
        System.out.println(count);

        Optional<Employee> max = emps.stream().max(Comparator.comparingInt(Employee::getAge));
        System.out.println(max);
        Optional<Employee> min = emps.stream().min(Comparator.comparingInt(Employee::getAge));
        System.out.println(min);
    }

    /**
     * 规约
     * reduce 可以反复将流中的元素结合起来的到一个新值
     */
    @Test
    public void test9() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sunm = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sunm);
    }

    /**
     * 收集
     */
    @Test
    public void test10() {
        List<String> collect = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        HashSet<String> collect2 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        Map<String, String> collect1 = emps.stream()
                .map(Employee::getName)
                .distinct()
                .collect(Collectors.toMap(s -> s + "-key", s -> s));
        for (Map.Entry<String, String> entry : collect1.entrySet()) {
            System.out.println(entry.getKey() + " -- " + entry.getValue());
        }

        for (String name : collect) {
            System.out.println(name);
        }
        //总数
        Long count = emps.stream().collect(Collectors.counting());
        System.out.println(count);
        //平均值
        Double average = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(average);
        //总和
        Double salarySum = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(salarySum);
        Optional<Employee> oldest = emps.stream().collect(Collectors.maxBy((e1, e2) -> {
            return e1.getAge() - e2.getAge();
        }));
        System.out.println(oldest.get().getAge());
    }

    /**
     * 分组
     */
    @Test
    public void test11() {
        Map<Integer, List<Employee>> collect = emps.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(collect);
        Map<Integer, Map<String, List<Employee>>> collect1 = emps.stream().collect(Collectors.groupingBy(Employee::getAge, Collectors.groupingBy(e -> {
            if (e.getSalary() > 3000) {
                return "low";
            } else {
                return "high";
            }
        })));
        System.out.println(collect1);
    }

    /**
     * 分区
     */
    @Test
    public void test12() {
        Map<Boolean,List<Employee>> employeeMap=emps.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 9000));
        System.out.println(employeeMap);
    }
    @Test
    public void test13() {
        DoubleSummaryStatistics collect = emps.stream().collect(Collectors.summarizingDouble(Employee::getAge));
        System.out.println(collect.getMax());
        System.out.println(collect.getAverage());
        System.out.println(collect.getCount());
        System.out.println(collect.getSum());

        String s = emps.stream().map(Employee::getName)
                .collect(Collectors.joining( ));
        System.out.println(s);
    }
}
