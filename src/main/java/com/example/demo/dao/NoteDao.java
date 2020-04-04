package com.example.demo.dao;

import com.example.demo.model.Note;

import java.util.List;

public interface NoteDao {
    default int addNote(Note note){
        return addNote(note);
    }

    int deleteNote(Note note);

    List<Note> getAllNote();
}
