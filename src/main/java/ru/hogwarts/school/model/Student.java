package ru.hogwarts.school.model;

import java.util.Objects;

public class Student {
    private String name;
    private Long id;
    private int age;

    public Student(String name, Long id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public Student(long currentId, String name, int age) {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name) && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, age);
    }

    public void put(Long id, Student student) {
    }

}
