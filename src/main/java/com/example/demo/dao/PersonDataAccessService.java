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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public void addImage(File file) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                UUID id = UUID.randomUUID();
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                PreparedStatement ps = connection.prepareStatement("INSERT INTO person(id, name, img) VALUES (?, ?, ?)");
                ps.setObject(1, id);
                ps.setString(2, file.getName());
                ps.setBinaryStream(3, fis, file.length());
                ps.executeUpdate();
//                ps.close();
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return ps;
            }
        });
    }
}
