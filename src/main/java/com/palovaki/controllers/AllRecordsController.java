package com.palovaki.controllers;

import com.palovaki.dao.StudentDAO;
import com.palovaki.dao.TeacherDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllRecordsController {

    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;

    public AllRecordsController(StudentDAO studentDAO, TeacherDAO teacherDAO) {
        this.studentDAO = studentDAO;
        this.teacherDAO = teacherDAO;
    }

    @GetMapping("/")
    public String getAllRecords(Model model) {
        model.addAttribute("students", studentDAO.getAll());
        model.addAttribute("teachers", teacherDAO.getAllWithNumOfTaughtSubjects());

        return "allRecords";
    }

}
