package com.example.getStartedExercise.getstartedexercise.service.ArticleService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepositoryImp;
import com.example.getStartedExercise.getstartedexercise.repository.datarepository.BookDataRepository;
import com.example.getStartedExercise.getstartedexercise.repository.model.Book;
import com.example.getStartedExercise.getstartedexercise.service.ArticleServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

/**
 * ArticleService
 */

 @Service
public class ArticleService implements ArticleServiceImp {

    ArticleRepositoryImp articleRepositoryImp;

    @Autowired
    ArticleService(ArticleRepositoryImp articleRepositoryImp,BookDataRepository bookDataRepository){
        this.articleRepositoryImp = articleRepositoryImp;
        this.bookDataRepository = bookDataRepository;
    }
    
    BookDataRepository bookDataRepository;

    @Override
    public boolean add( Book article) {
       
        Book book = bookDataRepository.save(article);
        return book == null ? false : true;
     
    }

    @Override
    public List<Book> findAll() {
        return bookDataRepository.findByStatus(1);
    }

    @Override
    public Book find(int id) {
       try {
        Book book = bookDataRepository.findById(id).get();
        return book;
       } catch (Exception e) {
           return null;
       }
        
        
       // return articleRepositoryImp.find(id);
    }

    @Override
    public boolean update(Book article) {
       
      Book _book=  bookDataRepository.getOne(article.getId());
      _book = article;
      Book book= bookDataRepository.save(_book);
      return book == null ? false : true;
    }

    @Override
    public boolean update(int index,Book article) {
       // articleRepositoryImp.update(index, article);
        Book book=  bookDataRepository.getOne(index);
        book = article;
        Book _book = bookDataRepository.save(book);
        return _book == null ? false : true;
    }
    @Override
    public boolean delete(int id) {
        Book book=  bookDataRepository.getOne(id);
        book.setStatus(0);
        Book _book = bookDataRepository.save(book);
        return _book == null ? false : true;
    }

    
}