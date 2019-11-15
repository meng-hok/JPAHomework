package com.example.getStartedExercise.getstartedexercise.repository.datarepository;

import java.util.List;

import com.example.getStartedExercise.getstartedexercise.repository.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * BookDataRepository
 */
@Repository
public interface BookDataRepository extends JpaRepository<Book,Integer> {

	List<Book> findByStatus(Integer status);
	

	List<Book> findByTitleLikeAndStatus(String string, int i);


	List<Book> findByTitleLikeAndCategoryAndStatus(String title, int category_id, int i);


	List<Book> findByTitleLikeAndCategoryIdAndStatus(String title, int category_id, int i);
    
}