package com.example.demo.service;

import com.example.demo.dao.NoteDao;
import com.example.demo.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteDao noteDao;

    @Autowired
    public NoteService(@Qualifier("postgres2") NoteDao noteDao){
        this.noteDao = noteDao;
    }
    public int addNote(Note note){
        return noteDao.addNote(note);
    }
    public List<Note> getAllNote(){
        return noteDao.getAllNote();
    }
}
