package com.example.getStartedExercise.getstartedexercise.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Category
 */
@Data
@Table(name ="tb_categories")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String name = "DEFAULT";
    private Integer status = 1;


    public Category() {
    }
    public Category(String name) {
    
        this.name = name;
    }
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    
    
}