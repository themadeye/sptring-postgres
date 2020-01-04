package com.example.demo.dao;

import com.example.demo.model.Note;

public interface NoteDao {
    default int addNote(Note note){
        return addNote(note);
    }
}
