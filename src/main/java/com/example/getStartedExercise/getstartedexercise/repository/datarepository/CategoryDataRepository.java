package com.example.getStartedExercise.getstartedexercise.repository.datarepository;

import java.util.List;

import com.example.getStartedExercise.getstartedexercise.repository.model.Book;
import com.example.getStartedExercise.getstartedexercise.repository.model.Category;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepository
 */
@Repository
@RepositoryRestResource(exported = true)
public interface CategoryDataRepository extends JpaRepository<Category,Integer> {

	List<Category> findByStatus(int status);
    
    @Modifying
    @Query(
        nativeQuery = true,
        value = "UPDATE tb_books SET status = 0 WHERE category_id = :id"
        )
	Integer deleteAllBooksAfterCategoryGettingDeleted(@Param("id") Integer id);

	List<Category> getByStatus(int i);

    
}