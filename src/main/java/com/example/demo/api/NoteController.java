package com.example.demo.api;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.demo.dao.StorageService;
import com.example.demo.model.Note;
import com.example.demo.model.Person;
import com.example.demo.service.NoteService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:8081", "http://localhost:8082" })
@RestController
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping(path = "madeye/v1/note/{major}")
    public void addNote(@Valid @NotNull @RequestBody Note note){
        noteService.addNote(note);
    }

}
