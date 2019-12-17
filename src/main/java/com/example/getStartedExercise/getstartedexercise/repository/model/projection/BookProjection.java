package com.example.getStartedExercise.getstartedexercise.repository.model.projection;

import com.example.getStartedExercise.getstartedexercise.repository.model.Book;
import com.example.getStartedExercise.getstartedexercise.repository.model.Category;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * BookProjection
 */
@Projection(
  name = "book_category", 
  types = { Book.class }) 
public interface BookProjection {
    @Value("#{target.id}")
    Integer getId();
    String getTitle();
    String getAuthor();
    String getDescription();
    String getThumbnail();
    Integer getStatus();
    // @Value("#{target.category}")
    Category getCategory(); 

}