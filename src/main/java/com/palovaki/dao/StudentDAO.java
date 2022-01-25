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
    public Student getById(Long id) {
        String sql = """
                    SELECT student_id, first_name, last_name
                    FROM students
                    WHERE student_id = ?
                """;
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Student student = new Student();

            student
                    .setId(rs.getLong("student_id"))
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"));

            return student;
        }, id);
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

            return student;
        });
    }

    public void enrollToDiscipline(Long studentId, Long subjectId) {
        String sql = """
                    INSERT INTO enrollments(fk_student_id, fk_subject_id)
                    VALUES(
                        (SELECT student_id FROM students WHERE student_id = ?),
                        (SELECT subject_id FROM subjects WHERE subject_id = ?)
                    );
                """;

        jdbcTemplate.update(sql, ps -> {
            ps.setLong(1, studentId);
            ps.setLong(2, subjectId);
        });
    }

    @Override
    public void delete(Long id) {

    }
}
