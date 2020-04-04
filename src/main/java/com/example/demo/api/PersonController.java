package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.NoteService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// The default port for this server was 8080, if the frontend side wish to access from their very own origin port.
// We need to add the CrossOrigin header and list out the frontend origin port.
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:8081", "http://localhost:8082" })
//restcontroller means this controller serve as a restful service : HTTP, GET, DELETE, POST, PUT
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService, NoteService noteService){
        this.personService = personService;
    }

    //postMapping: THIS METHOD HERE WILL SERVE AS POST
    @PostMapping(path = "madeye/v1/person/{name}")
    public void addPerson(@Valid @NotNull @RequestBody Person person){
        personService.addPerson(person);
    }

    // this method will serve as get request
    @GetMapping("madeye/v1/person")
//    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "madeye/v1/person/{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElse(null);
    }

//    @DeleteMapping(path = "{id}")
    @DeleteMapping(path = "madeye/v1/person/{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    // PUT= the update in REST
    // @Valid = means the param or data passed in should be valid, @NotNull in this case means param shouldn't be null.
    @PutMapping(path = "madeye/v1/{name}/person/{id}")
//    public void updatePerson(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person personToUpdate){
    public void updatePerson(@PathVariable("id") UUID id, @PathVariable("name") String name, @RequestBody Person personToUpdate){
        personService.updatePerson(id, name);
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

    @GetMapping("madeye/v1/file")
    public void addIamge(){
        ArrayList<File> arrF = new ArrayList<>();
        if (dir.isDirectory()) {
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                arrF.add(f);
            }

            personService.addImage(arrF);
        }
    }

    @GetMapping("madeye/v1/getfile")
    public void getIamge(){
        personService.getImage();
    }

    class SuperClass {
        SuperClass(int x) {
            System.out.println("Super");
        }
    }

    @GetMapping("madeye/v1/lambda")
    public void lambdaExample(){

        int array[] = {0, 1, 2, 3, 4};
        int key = 3;

        //with type declaration
        MathOperation addition = (int a, int b) -> a + b;

        //with out type declaration
        MathOperation subtraction = (a, b) -> a - b;

        //with return statement along with curly braces
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        //without return statement and without curly braces
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + operate(10, 5, addition));
        System.out.println("10 - 5 = " + operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + operate(10, 5, division));

        //without parenthesis
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        //with parenthesis
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Mahesh");
        greetService2.sayMessage("Suresh");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation operation) {
        return operation.operation(a, b);
    }
}
