package com.palovaki.dao;

import com.palovaki.models.Subject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectDAO implements DAO<Subject> {

    JdbcTemplate jdbcTemplate;

    public SubjectDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Subject getById(Long id) {
        return null;
    }

    @Override
    public List<Subject> getAll() {
        return null;
    }

    @Override
    public void save(Subject subject) {
        String sql = """
                    INSERT INTO subjects(name, credits, fk_teacher_id)
                    VALUES(?, ?, ?);
                """;

        jdbcTemplate.update(sql, ps -> {
            ps.setString(1, subject.getName());
            ps.setInt(2, subject.getCredits());
            ps.setLong(3, subject.getTeacherId());
        });
    }

    public List<Subject> getAvailableSubjectsForStudent(Long studentId) {
        String sql = """
                    SELECT subject_id, name, credits
                    FROM subjects
                    WHERE subject_id NOT IN (
                        SELECT sub.subject_id
                        FROM students st, enrollments e, subjects sub
                        WHERE st.student_id = e.fk_student_id AND sub.subject_id = e.fk_subject_id AND st.student_id = ?
                    );
                """;

        return jdbcTemplate.query(sql, rowMapper, studentId);
    }

    public List<Subject> getEnrolledFromStudentSubjects(Long studentId) {
        String sql = """
                    SELECT sub.subject_id, sub.name, sub.credits
                    FROM students st, enrollments e, subjects sub
                    WHERE st.student_id = e.fk_student_id AND sub.subject_id = e.fk_subject_id AND st.student_id = ?;
                """;

        return jdbcTemplate.query(sql, rowMapper, studentId);
    }


    public List<Subject> getTopThreeMostEnrolledSubjects() {
        String sql = """
                    SELECT sub.subject_id, sub.name, sub.credits,
                        COUNT(st.student_id) as total_students
                    FROM enrollments e, students st, subjects sub
                    WHERE st.student_id = e.fk_student_id AND sub.subject_id = e.fk_subject_id
                    GROUP BY sub.subject_id
                    ORDER BY total_students DESC, sub.name
                    LIMIT 3;
                """;

        return jdbcTemplate.query(sql, rowMapper);
    }

    RowMapper<Subject> rowMapper = (rs, rowNum) -> {
        Subject subject = new Subject();

        subject
                .setId(rs.getLong("subject_id"))
                .setName(rs.getString("name"))
                .setCredits(rs.getInt("credits"));

        int totalStudents = 0;

        try {
            totalStudents = rs.getInt("total_students");
        } catch (Exception ignored) {
        }
        subject.setTotalStudents(totalStudents);

        return subject;
    };


    @Override
    public void delete(Long id) {

    }
}
