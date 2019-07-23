package com.zyp.jdk9.jdk9learning.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangyp 01385499
 */
public class StreamTest {


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Student {
        private String no;
        private String name;
        private Integer age;
        private Integer grade;
        private String classId;
    }

    @AllArgsConstructor
    static class Teacher {
        private String name;
        private String major;
        private List<Student> students;

    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("1101", "zhangsan", 18, 60, "class1"));
        students.add(new Student("1102", "lisi", 19, 65, "class1"));
        students.add(new Student("1103", "lisisi", 18, 80, "class1"));
        students.add(new Student("1104", "wangyang", 19, 98, "class1"));
        students.add(new Student("1201", "liming", 19, 65, "class2"));
        students.add(new Student("1202", "liuhua", 30, 78, "class2"));
        students.add(new Student("1203", "zhaowu", 30, 83, "class2"));

        Map<String, List<Student>> classStudents = students.stream().collect(Collectors.groupingBy(Student::getClassId)); // 班级对应的学生列表
        Map<String, Student> studentNoMaps = students.stream().collect(Collectors.toMap(Student::getNo, Function.identity())); // 学号对应的学生

        Map<String, Optional<Student>> classMaxAgeStudents = students.stream().collect(
                Collectors.groupingBy(Student::getClassId,
                        Collectors.maxBy(Comparator.comparing(Student::getAge)))); // 每个班级年龄最大的学生

        Map<String, String> classMaxAgeStudentNames = students.stream().collect(
                Collectors.groupingBy(Student::getClassId,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Student::getAge)), st -> st.get().getName())
                ));// 每个班级年龄最大的学生姓名

        Map<String, Double> classAvgAges = classStudents.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Student::getClassId, Collectors.averagingDouble(Student::getAge))); // 统计班级平均年龄

        // 统计班级各年龄段成绩最优秀的学生姓名
        Map<String, String> classBestStudentName = students.stream().collect(Collectors.groupingBy(st -> String.join("-", st.getClassId(), String.valueOf(st.getAge())),
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Student::getGrade)), st -> st.get().getName())));

        test2();

        System.out.println();
    }

    private static void test2() {
        String poetry = "Where, before me, are the ages that have gone?\n" +
                "And where, behind me, are the coming generations?\n" +
                "I think of heaven and earth, without limit, without end,\n" +
                "And I am all alone and my tears fall down.";

        Stream<String> lines = Arrays.stream(poetry.split("\n"));
        Stream<String> words = lines.flatMap(line -> Arrays.stream(line.split(" ")));


//        System.out.println(words.reduce( (x, y) -> x + "_" + y));
//        System.out.println(words.reduce("hello", (x, y) -> x + "_" + y));
        List<String> strs = Arrays.asList("a", "b", "c", "d");
        System.out.println(strs.stream().reduce("hello", (a,b) -> a+"*"+b, (x, y) -> x + "_" + y));
        /*List l = words.map(w -> {
            if (w.endsWith(",") || w.endsWith(".") || w.endsWith("?"))
                return w.substring(0, w.length() - 1).trim().toLowerCase();
            else {
                return w.trim().toLowerCase();
            }
        }).distinct().sorted().collect(Collectors.toList());

        System.out.println(l);*/
    }
}
