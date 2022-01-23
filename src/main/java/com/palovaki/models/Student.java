package com.palovaki.models;

public class Student extends Person {
    private int yearInCollege;
    private Long id;

    public Student() {}

    public Student(String firstName, String lastName, int yearInCollege) {
        super(firstName, lastName);
        this.yearInCollege = yearInCollege;
    }

    public int getYearInCollege() {
        return yearInCollege;
    }

    public Student setYearInCollege(int yearInCollege) {
        this.yearInCollege = yearInCollege;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Student{" +
                "yearInCollege='" + yearInCollege + '\'' +
                ", id=" + id +
                '}';
    }
}
