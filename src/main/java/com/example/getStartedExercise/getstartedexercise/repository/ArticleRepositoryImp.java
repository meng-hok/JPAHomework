package com.example.getStartedExercise.getstartedexercise.repository;

import java.util.List;

import com.example.getStartedExercise.getstartedexercise.repository.model.Book;

/**
 * ArticleRepositoryImp
 */
public interface ArticleRepositoryImp {

    boolean add (Book article) ;
    List<Book> findAll(); 
    Book find(int id);
    boolean update(int index,Book article);
    boolean update (Book article);
    boolean delete(int id); 
    
    
}