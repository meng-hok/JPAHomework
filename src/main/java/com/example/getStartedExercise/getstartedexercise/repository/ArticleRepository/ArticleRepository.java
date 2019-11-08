package com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepositoryImp;
import com.example.getStartedExercise.getstartedexercise.repository.model.Book;

import org.springframework.stereotype.Repository;

/**
 * ArticleRepository
 */
//@Repository
public class ArticleRepository implements ArticleRepositoryImp {

    List<Book> list = new ArrayList<>(); 
    static int id =1;
    @Override
    public boolean add(Book article) {
        try{
            article.setId(id);
            list.add(article);
            id++;
        }catch(Exception ex){
            return false;   
        }
        return true;
    }   

    @Override
    public List<Book> findAll() {
        return list;
    }

    @Override
    public Book find(int id) {
       
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id){
                 return list.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean update(Book article) {
        System.out.println(article.getId());
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == article.getId()){
                System.out.println(i + "work");
                list.set(i, article);
                
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean update(int index,Book article) {
        list.set(index, article);
        return false;
    }
    @Override
    public boolean delete(int id) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id){
               list.remove(i);
                return true;
            }
        }
        return false;
    }

    
}