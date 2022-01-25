package com.palovaki.controllers;

import com.palovaki.dao.StudentDAO;
import com.palovaki.dao.SubjectDAO;
import com.palovaki.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private StudentDAO studentDAO;
    private SubjectDAO subjectDAO;

    public StudentController(StudentDAO studentDAO, SubjectDAO subjectDAO) {
        this.studentDAO = studentDAO;
        this.subjectDAO = subjectDAO;
    }

    @GetMapping("/student/create")
    public String GetCreateStudent() {
        return "createStudent";
    }

    @PostMapping("/student/create")
    public String PostCreateStudent(@ModelAttribute Student student) {
        studentDAO.save(student);
        return "redirect:/";
    }

    @GetMapping("/students-and-disciplines")
    public String GetStudentsAndDisciplines(Model model) {
        model.addAttribute("students", studentDAO.getStudentsAndDisciplines());
        return "studentsAndDisciplines";
    }

    @GetMapping("/enroll-to-discipline/{studentId}")
    public String GetEnrollToDiscipline(@PathVariable String studentId, Model model) {
        Long id = Long.parseLong(studentId);

        model.addAttribute("student", studentDAO.getById(id));
        model.addAttribute("disciplines", subjectDAO.getAvailableSubjectsForStudent(id));

        return "enrollStudent";
    }
}
