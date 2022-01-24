package com.palovaki.dao;

import com.palovaki.models.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentDAO implements DAO<Student> {

    private JdbcTemplate jdbcTemplate;

    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Student> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Student stud = new Student();

            stud
                    .setId(rs.getLong("student_id"))
                    .setYearInCollege(rs.getInt("year_in_college"))
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"));


            return stud;
        });
    }

    @Override
    public void save(Student student) {
        String sql = """
                    INSERT INTO students (first_name, last_name, year_in_college)
                    VALUES(?, ?, ?);
                """;

        jdbcTemplate.update(sql, ps -> {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getYearInCollege());
        });
    }

    @Override
    public void delete(Long id) {

    }

    public List<Student> getStudentsAndDisciplines() {
        String sql = """
                        SELECT s.student_id,
                            s.first_name, s.last_name,
                            GROUP_CONCAT(sub.name SEPARATOR ', ') as enrolledSubjects
                        FROM students s
                        LEFT JOIN enrollments e
                        ON s.student_id = e.fk_student_id
                        LEFT JOIN subjects sub
                        ON sub.subject_id = fk_subject_id
                        GROUP BY s.student_id;
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Student student = new Student();

            student.setId(rs.getLong("student_id"))
                    .setEnrolledSubjects(rs.getString("enrolledSubjects"))
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"));

            System.out.println(student.getEnrolledSubjects());
            return student;
        });
    }
}
