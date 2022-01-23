package com.palovaki.models;

public class Teacher extends Person {
    private String title;
    private Long id;
    private int taughtSubjects;


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

    public int getTaughtSubjects() {
        return taughtSubjects;
    }

    public Teacher setTaughtSubjects(int taughtSubjects) {
        this.taughtSubjects = taughtSubjects;
        return this;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "title='" + title + '\'' +
                '}';
    }

}
