package com.palovaki.controllers;

import com.palovaki.dao.TeacherDAO;
import com.palovaki.models.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/teachers-and-subjects")
    public String GetTeachersAndTheirSubjects(Model model) {
        Map<Long, Teacher> teachers = teacherDAO.getAllWithTheirSubjects();
        model.addAttribute("teachers", teachers);

        return "teachersAndSubjects";
    }
}
