package com.example.getStartedExercise.getstartedexercise.service.ArticleService;

import java.util.List;

import javax.validation.Valid;

import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepositoryImp;
import com.example.getStartedExercise.getstartedexercise.repository.model.Book;
import com.example.getStartedExercise.getstartedexercise.service.ArticleServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 * ArticleService
 */

 @Service
public class ArticleService implements ArticleServiceImp {

    ArticleRepositoryImp articleRepositoryImp;

    @Autowired
    ArticleService(ArticleRepositoryImp articleRepositoryImp){
        this.articleRepositoryImp = articleRepositoryImp;
    }

    @Override
    public boolean add( Book article) {
        if(articleRepositoryImp.add(article)){
            System.out.println("addd aii");
            return true;
        }
     
        return false;
    }

    @Override
    public List<Book> findAll() {
        return articleRepositoryImp.findAll();
    }

    @Override
    public Book find(int id) {
        return articleRepositoryImp.find(id);
    }

    @Override
    public boolean update(Book article) {
        // System.out.println("service here"+article.getId());
        articleRepositoryImp.update(article);
        return false;
    }

    @Override
    public boolean update(int index,Book article) {
        articleRepositoryImp.update(index, article);
        return false;
    }
    @Override
    public boolean delete(int id) {
        articleRepositoryImp.delete(id);
        return false;
    }

    
}