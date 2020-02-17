package com.example.demo.dao;

import com.example.demo.model.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
// this is the interface we going to find the operation allowed or the contract that anyone wishes to implement this interface
// cool thing about it, is we can then use dependency injection to switch between implementation with just one line of code
public interface PersonDao {
// Now here, we have two different method which is
    // insert person with given UUID and person object
    int insertPerson(UUID id, Person person);
    // insert person without UUID
    default int addPerson(Person person){
        // then we generate one for them and insert it
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    // this will return a list of person
    List<Person> selectAllPeople();

    Optional<Person> selectePersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, String name);

    void addImage(ArrayList<File> f);
    void getImage();
}
