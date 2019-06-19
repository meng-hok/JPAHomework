package com.example.getStartedExercise.getstartedexercise.repository.model;

import javax.validation.constraints.NotEmpty;

/**
 * Category
 */
public class Category {

    
    private int id;
    @NotEmpty
    private String name = "DEFAULT";
    

    public Category() {
    }
    public Category(String name) {
    
        this.name = name;
    }
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category id(int id) {
        this.id = id;
        return this;
    }

    public Category name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
    
}