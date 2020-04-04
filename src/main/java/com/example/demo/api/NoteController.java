package com.example.demo.api;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Note;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.regex.Pattern;

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

    @GetMapping("madeye/v1/note")
    public List<Note> getAllNote(){
        return noteService.getAllNote();
    }

    @PostMapping("madeye/v1/note/delete")
    public void deleteNote(@Valid @NotNull @RequestBody Note note){
        noteService.deleteNote(note);
    }

    static final File dir = new File("C:/Users/LeonKong/VueProject/vue-material-dashboard-master/src/hole2");
    static final String[] EXTENSIONS = new String[]{"gif", "png", "jpg"};
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

//    @GetMapping("madeye/v1/file")
//    public List<String> getFile(){
//        ArrayList<String> arrF = new ArrayList<>();
//        if (dir.isDirectory()) {
//            for (final File f : dir.listFiles(IMAGE_FILTER)) {
//                String separator = "\\";
//                String[] fileName = f.toString().replaceAll(Pattern.quote(separator), "\\\\").split("\\\\");
//                StringBuilder sb = new StringBuilder();
//                sb.append("@").append("/").append(fileName[6]).append("/").append(fileName[7]);
//                arrF.add(sb.toString());
//            }
//        }
//        return arrF;
//    }
}
