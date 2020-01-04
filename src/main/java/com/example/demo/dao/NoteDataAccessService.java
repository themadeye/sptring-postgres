package com.example.demo.dao;


import com.example.demo.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("postgres2")
public class NoteDataAccessService implements NoteDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NoteDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addNote(Note note) {
        final String sql = "INSERT INTO note(id, major, subcategory, title, details) VALUES (?, ?, ?, ?, ?)";
        // define query arguments
        Object[] params = new Object[] { note.getId(), note.getMajor(), note.getSubcategory(), note.getTitle(), note.getDetails()};
        return jdbcTemplate.update(sql, params);
    }
}
