package com.example.getStartedExercise.getstartedexercise.service.CategoryService;

import java.util.List;

import com.example.getStartedExercise.getstartedexercise.repository.model.Category;

/**
 * CategoryService
 */
public interface CategoryService {

    
   boolean add (Category category) ;
   List<Category> findAll(); 
   Category find(int id);
   boolean update(int index,Category category);
   boolean update (Category category);
   boolean delete(int id); 
}