package com.palovaki.dao;

import com.palovaki.models.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TeacherDAO implements DAO<Teacher> {

    private final JdbcTemplate jdbcTemplate;

    public TeacherDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Teacher getById(Long id) {
        return null;
    }

    @Override
    public List<Teacher> getAll() {
        String sql = "SELECT * FROM teachers;";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Teacher> getAllWithNumOfTaughtSubjects() {
        String sql = """
                        SELECT t.teacher_id, t.title, t.first_name, t.last_name, COUNT(s.subject_id) as taughtSubjects
                        FROM teachers t
                        LEFT JOIN subjects s
                        ON t.teacher_id = s.fk_teacher_id
                        GROUP BY t.teacher_id
                        ORDER BY t.first_name, t.last_name;
                """;

        return jdbcTemplate.query(sql, rowMapper);
    }

    RowMapper<Teacher> rowMapper = (rs, rowNum) -> {
        Teacher teach = new Teacher();

        teach
                .setId(rs.getLong("teacher_id"))
                .setTitle(rs.getString("title"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"));

        int taughtSubjects = 0;
        try {
            taughtSubjects = rs.getInt("taughtSubjects");
        } catch (Exception ignored) {
        }

        teach.setTaughtSubjects(taughtSubjects);

        return teach;
    };

    @Override
    public void save(Teacher teacher) {
        String sql = """
                    INSERT INTO teachers(title, first_name, last_name)
                    VALUES(?, ?, ?);
                """;

        jdbcTemplate.update(sql, ps -> {
            ps.setString(1, teacher.getTitle());
            ps.setString(2, teacher.getFirstName());
            ps.setString(3, teacher.getLastName());
        });
    }

    @Override
    public void delete(Long id) {

    }
}
