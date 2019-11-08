package com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.example.getStartedExercise.getstartedexercise.repository.model.Category;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepository
 */

@Repository
@Transactional
public class CategoryRepository {

  @PersistenceContext
  EntityManager entityManager;

  public Boolean add(Category category) {
    try {
      entityManager.persist(category);

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public List<Category> findAll() {
    TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.status = 1",
        Category.class);
    List<Category> categories = query.getResultList();
    return categories;
  }

  public Category find(int id) {
    Category category = entityManager.find(Category.class, id);
    return category;
  }

  public boolean update(Category category) {
    try {
    Category _category = entityManager.find(Category.class, category.getId());
    _category = category;
    _category.setStatus(1);
   
      entityManager.merge(_category);
    } catch (Exception e) {
      return false;
    }
   
    return true;
  }

  public boolean delete(int id) {
    Category category = entityManager.find(Category.class, id);
    category.setStatus(0);
    try {
      entityManager.merge(category);
    } catch (Exception e) {
      return false;
    }

    return true;

  }

}