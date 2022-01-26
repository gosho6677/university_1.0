package com.palovaki.controllers;

import com.palovaki.dao.StudentDAO;
import com.palovaki.dao.SubjectDAO;
import com.palovaki.models.Student;
import com.palovaki.models.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/enroll-to-discipline/{studId}")
    public String PostEnrollToDiscipline(@PathVariable String studId,
                                         @RequestParam("disciplineId") String disciplineId) {
        Long studentId = Long.parseLong(studId);
        Long subjectId = Long.parseLong(disciplineId);

        studentDAO.enrollToDiscipline(studentId, subjectId);
        return "redirect:/students-and-disciplines";
    }

    @GetMapping("/remove-from-discipline/{studentId}")
    public String GetRemoveStudentFromDiscipline(@PathVariable String studentId, Model model) {
        Long id = Long.parseLong(studentId);

        model.addAttribute("student", studentDAO.getById(id));
        model.addAttribute("disciplines", subjectDAO.getEnrolledFromStudentSubjects(id));

        return "removeFromDiscipline";
    }

    @PostMapping("/remove-from-discipline/{studId}")
    public String PostRemoveStudentFromDiscipline(@PathVariable String studId,
                                                  @RequestParam("disciplineId") String disciplineId) {
        Long studentId = Long.parseLong(studId);
        Long subjectId = Long.parseLong(disciplineId);

        studentDAO.removeFromDiscipline(studentId, subjectId);

        return "redirect:/students-and-disciplines";
    }
}
