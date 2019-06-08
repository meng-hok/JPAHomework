package com.example.getStartedExercise.getstartedexercise.repository;

import java.util.List;

import com.example.getStartedExercise.getstartedexercise.repository.model.Article;

/**
 * ArticleRepositoryImp
 */
public interface ArticleRepositoryImp {

    boolean add (Article article) ;
    List<Article> findAll(); 
    Article find(int id);
    boolean update(int index,Article article);
    boolean update (Article article);
    boolean delete(int id); 
    
    
}