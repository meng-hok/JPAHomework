package com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepositoryImp;
import com.example.getStartedExercise.getstartedexercise.repository.model.Book;
import com.example.getStartedExercise.getstartedexercise.repository.provider.MyProvider;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * ArticleRepoDB
 */
@Repository
@Transactional
public class ArticleRepoDB implements ArticleRepositoryImp {

  @PersistenceContext
  EntityManager entityManager;

  public boolean add(Book article) {
    try {
      entityManager.persist(article);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public List<Book> findAll() {
    TypedQuery<Book> query = entityManager.createQuery("SELECT a FROM Book a WHERE a.status = 1", Book.class);
    List<Book> articles = query.getResultList();
    return articles;
  }

  public Book find(int id) {
    Book article = entityManager.find(Book.class, id);
    return article;
  }

  public boolean update(Book article) {
    Book _article = entityManager.find(Book.class, article.getId());
    _article = article;
    _article.setStatus(1);
    try {
      entityManager.merge(_article);
    } catch (Exception e) {
      return false;
    }
    
    return true;

  }

  // @Update("Update tb_articles SET status=0 WHERE id=#{id} ")
  public boolean delete(int id) {
    Book article = entityManager.find(Book.class, id);
    article.setStatus(0);
    try {
      entityManager.merge(article);
    } catch (Exception e) {
      return false;
    }
   
    return true;
  };

  public boolean deleteByCategoryId(int category_id) {

    TypedQuery<Book> query = entityManager.createQuery("SELECT a FROM Book a WHERE a.category.id = ?1",
        Book.class);
    query.setParameter(1, category_id);
    List<Book> articles = query.getResultList();
    //entityManager.getTransaction().begin();
    articles.forEach((article) -> {
      article.setStatus(0);
      try {
        entityManager.merge(article);
      } catch (Exception e) {
       // entityManager.getTransaction().rollback();
        return;
      }
    
    });
    //entityManager.getTransaction().commit();
    return true;
  }

  @Override
  public boolean update(int index, Book article) {
    return false;
  }

  public List<Book> findByTitle(String title) {
    TypedQuery<Book> query = entityManager
        .createQuery("SELECT a FROM Book a WHERE a.title LIKE ?1 AND a.status =1", Book.class);
    query.setParameter(1, "%" + title + "%");
    List<Book> articles = query.getResultList();

    return articles;
  }

  public List<Book> findByTitleAndType(String title, int category_id) {
    TypedQuery<Book> query = entityManager.createQuery(
        "SELECT a FROM Book a WHERE a.title LIKE ?1 AND a.status = 1 AND  a.category.id = ?2", Book.class);
    query.setParameter(1, "%" + title + "%");
    query.setParameter(2, category_id);
    List<Book> articles = query.getResultList();

    return articles;
  }
}