package com.palovaki.models;

public class Subject {
    private Long id;
    private String name;
    private int credits;
    private Long teacherId;
    private int totalStudents;

    public Subject() {
    }

    public Subject(Long id, String name, int credits, Long teacherId) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.teacherId = teacherId;
    }

    public Long getId() {
        return id;
    }

    public Subject setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    public int getCredits() {
        return credits;
    }

    public Subject setCredits(int credits) {
        this.credits = credits;
        return this;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public Subject setTeacherId(String teacherId) {
        this.teacherId = Long.parseLong(teacherId);
        return this;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public Subject setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
        return this;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", teacherId=" + teacherId +
                '}';
    }
}
