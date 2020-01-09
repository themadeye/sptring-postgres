package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Note {

    private int id;
    private String major;
    private String subcategory;
    private String title;
    private String details;

    public Note(@JsonProperty("id")int id, @JsonProperty("major") String major,  @JsonProperty("subcategory") String subcategory,  @JsonProperty("title") String title,  @JsonProperty("details") String details) {
        this.id = id;
        this.major = major;
        this.subcategory = subcategory;
        this.title = title;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
