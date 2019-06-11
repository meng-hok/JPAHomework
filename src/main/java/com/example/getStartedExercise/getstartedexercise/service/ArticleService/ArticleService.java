package com.example.getStartedExercise.getstartedexercise.service.ArticleService;

import java.util.List;

import javax.validation.Valid;

import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepositoryImp;
import com.example.getStartedExercise.getstartedexercise.repository.model.Article;
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
    public boolean add( Article article) {
        if(articleRepositoryImp.add(article)){
            System.out.println("add ban aiaiii=");
            return true;
        }
        System.out.println("add ort kert te");
        return false;
    }

    @Override
    public List<Article> findAll() {
        return articleRepositoryImp.findAll();
    }

    @Override
    public Article find(int id) {
        return articleRepositoryImp.find(id);
    }

    @Override
    public boolean update(Article article) {
        // System.out.println("service here"+article.getId());
        articleRepositoryImp.update(article);
        return false;
    }

    @Override
    public boolean update(int index,Article article) {
        articleRepositoryImp.update(index, article);
        return false;
    }
    @Override
    public boolean delete(int id) {
        articleRepositoryImp.delete(id);
        return false;
    }

    
}