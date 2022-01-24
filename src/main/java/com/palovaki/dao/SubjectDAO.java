package com.palovaki.dao;

import com.palovaki.models.Subject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SubjectDAO implements Dao<Subject> {

    JdbcTemplate jdbcTemplate;

    public SubjectDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional getById(Long id) {
        return Optional.empty();
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

    @Override
    public void delete(Long id) {

    }
}
