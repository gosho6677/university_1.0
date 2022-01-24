package com.palovaki.controllers;

import com.palovaki.dao.TeacherDAO;
import com.palovaki.models.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/teacher/create")
    public String PostCreateTeacher(@ModelAttribute Teacher teacher) {
        teacherDAO.save(teacher);
        return "redirect:/";
    }
}
