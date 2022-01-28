package com.palovaki.controllers;

import com.palovaki.dao.SubjectDAO;
import com.palovaki.dao.TeacherDAO;
import com.palovaki.models.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubjectController {
    SubjectDAO subjectDAO;
    TeacherDAO teacherDAO;

    public SubjectController(SubjectDAO subjectDAO, TeacherDAO teacherDAO) {
        this.subjectDAO = subjectDAO;
        this.teacherDAO = teacherDAO;
    }

    @GetMapping("/subject/create")
    public String GetCreateSubject(Model model) {
        model.addAttribute("teachers", teacherDAO.getAll());
        return "createSubject";
    }

    @PostMapping("/subject/create")
    public String PostCreateSubject(@ModelAttribute Subject subject) {
        subjectDAO.save(subject);
        return "redirect:/";
    }

    @GetMapping("/most-enrolled-subjects")
    public String GetTopThreeMostEnrolledSubjects(Model model) {
        model.addAttribute("subjects", subjectDAO.getTopThreeMostEnrolledSubjects());
        return "mostEnrolledSubjects";
    }
}
