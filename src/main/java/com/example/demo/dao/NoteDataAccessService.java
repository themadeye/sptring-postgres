package com.example.demo.dao;


import com.example.demo.model.Note;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postgres2")
public class NoteDataAccessService implements NoteDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NoteDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addNote(Note note) {
        final String sql = "INSERT INTO note(major, subcategory, title, details) VALUES (?, ?, ?, ?)";
        // define query arguments
        Object[] params = new Object[] {note.getMajor(), note.getSubcategory(), note.getTitle(), note.getDetails()};
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Note> getAllNote(){
        final String sql = "SELECT * FROM note";
        // resultSet is a RowMapper
        List<Note> note = jdbcTemplate.query(sql, (resultSet, i) -> {
            int id = resultSet.getInt("id");
            String major = resultSet.getString("major");
            String subcategory = resultSet.getString("subcategory");
            String title = resultSet.getString("title");
            String details = resultSet.getString("details");
            return new Note(id, major, subcategory, title, details);
        });
        return note;
    }
}
