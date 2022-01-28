package com.palovaki.models;

import java.util.Objects;

public class Teacher extends Person {
    private String title;
    private Long id;
    private int numberOfTaughtSubjects;

    // refactor later
    private String taughtSubject;
    private int totalStudentsForSubject;


    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String title) {
        super(firstName, lastName);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Teacher setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Teacher setId(Long id) {
        this.id = id;
        return this;
    }

    public int getNumberOfTaughtSubjects() {
        return numberOfTaughtSubjects;
    }

    public Teacher setNumberOfTaughtSubjects(int numberOfTaughtSubjects) {
        this.numberOfTaughtSubjects = numberOfTaughtSubjects;
        return this;
    }

    public int getTotalStudentsForSubject() {
        return totalStudentsForSubject;
    }

    public Teacher setTotalStudentsForSubject(int totalStudentsForSubject) {
        this.totalStudentsForSubject = totalStudentsForSubject;
        return this;
    }

    public String getTaughtSubject() {
        return taughtSubject;
    }

    public Teacher setTaughtSubject(String taughtSubject) {
        this.taughtSubject = taughtSubject;
        return this;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", numberOfTaughtSubjects=" + numberOfTaughtSubjects +
                ", taughtSubject='" + taughtSubject + '\'' +
                ", totalStudentsForSubject=" + totalStudentsForSubject +
                '}';
    }

}
