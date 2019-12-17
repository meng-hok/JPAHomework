package com.example.getStartedExercise.getstartedexercise.repository.datarepository;

import java.util.List;
import java.util.Optional;

import com.example.getStartedExercise.getstartedexercise.repository.model.Book;
import com.example.getStartedExercise.getstartedexercise.repository.model.projection.BookProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * BookDataRepository
 */

@RepositoryRestResource(path = "/books" ,collectionResourceRel = "books_list",exported = true,excerptProjection = BookProjection.class)
public interface BookDataRepositoryCopy extends JpaRepository<Book, Integer> {
	
	@RestResource(path = "status", rel = "findByStatus" )
	Page<Book> findByStatus(@Param("status")Integer status,@Param("pageable")Pageable pageable);

	Page<Book> findByTitleContainingAndStatus(String string, int i,Pageable pageable);

	List<Book> findByTitleLikeAndCategoryAndStatus(String title, int category_id, int i);

	Page<Book> findByTitleContainingAndCategoryIdAndStatus(String title, int category_id, int i,Pageable pageable);

	Optional<Book> findById(Integer id);
    
}