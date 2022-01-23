package com.palovaki.controllers;

import com.palovaki.dao.TeacherDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {

    TeacherDAO teacherDAO;

    public TeacherController(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @GetMapping("/teacher/create")
    public String GetCreateTeacher() {
        return "createTeacher";
    }
}
