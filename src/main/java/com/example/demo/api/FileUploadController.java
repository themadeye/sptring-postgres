package com.example.demo.api;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.demo.dao.StorageService;
import com.example.demo.model.Person;
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

@RequestMapping("madeye/v1/file")
@RestController
public class FileUploadController {
    private final PersonService personService;

    @Autowired
    public FileUploadController(PersonService personService){
        this.personService = personService;
    }

    //postMapping: THIS METHOD HERE WILL SERVE AS POST
    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person){
        personService.addPerson(person);
    }

    // this method will serve as get request
    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    // PUT= the update in REST
    // @Valid = means the param or data passed in should be valid, @NotNull in this case means param shouldn't be null.
    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }




//    private final StorageService storageService;
//
//    @Autowired
//    public FileUploadController(StorageService storageService) {
//        this.storageService = storageService;
//    }
//
//    @GetMapping("/")
//    public String listUploadedFiles(Model model) throws IOException {
//
//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                        "serveFile", path.getFileName().toString()).build().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }
//
//    @PostMapping("/")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file,
//                                   RedirectAttributes redirectAttributes) {
//
//        storageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        return "redirect:/";
//    }

//    @ExceptionHandler(StorageFileNotFoundException.class)

}
