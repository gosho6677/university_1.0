package com.palovaki.dao;

import com.palovaki.models.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentDAO implements Dao<Student> {

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
}
