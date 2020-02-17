package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public int addPerson(Person person) {
        final String sql = "INSERT INTO person(id, name) VALUES (?, ?)";
        // define query arguments
        Object[] params = new Object[] { person.getId(), person.getName()};
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM person";
        // resultSet is a RowMapper
        List<Person> person = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
        return person;
    }

    @Override
    public Optional<Person> selectePersonById(UUID id) {

        final String sql = "SELECT id, name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id},(resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(personId, name);
        });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {

        final String sql = "DELETE FROM person WHERE id = ?";
        Object[] args = new Object[] {id};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int updatePersonById(UUID id, String name) {
        final String sql = "UPDATE person SET name = ? WHERE id = ?";
        // define query arguments
        // The Param order should be in order with sql string
        Object[] params = new Object[] { name, id};
        return jdbcTemplate.update(sql, params);
    }

    // Test save image
    @Override
    public void addImage(ArrayList<File> file) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO person(id, name, img) VALUES (?, ?, ?)");
                // Notes: FileInputStream are always gonna throw Exception, just put a try block to handle it.
                for(File f : file){
                    UUID id = UUID.randomUUID();
                    try (FileInputStream fis = new FileInputStream(f)) {
                        ps.setObject(1, id);
                        ps.setString(2, f.getName());
                        ps.setBinaryStream(3, fis, f.length());
                        ps.executeUpdate();
                        if(fis != null){
                            fis.close();
                        }
                    } // fileIn is closed
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return ps;
            }
        });
    }

    @Override
    public void getImage(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList(
                "SELECT * FROM person");
        for (Map<String, Object> row : list) {
            System.out.println(row.get("name"));
        }
    }
}
