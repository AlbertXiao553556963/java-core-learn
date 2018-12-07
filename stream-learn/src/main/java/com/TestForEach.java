package com;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestForEach{

    @Data
    static class Student {
        int no;
        String name;
        String sex;
        float height;

        public Student(int no, String name, String sex, float height) {
            this.no = no;
            this.name = name;
            this.sex = sex;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        Student stuA = new Student(1, "A", "M", 184);
        Student stuB = new Student(2, "B", "G", 163);
        Student stuC = new Student(3, "C", "M", 175);
        Student stuD = new Student(4, "D", "G", 158);
        Student stuE = new Student(5, "E", "M", 170);
        List<Student> list = new ArrayList<>();
        list.add(stuA);
        list.add(stuB);
        list.add(stuC);
        list.add(stuD);
        list.add(stuE);
        list.stream()
                .skip(3l)
                .filter(student -> "G".equals(student.getSex()))
                .forEach(student -> student.setName("xmx"));
        list.stream().collect(Collectors.toList());
        System.out.println(list);
    }
}