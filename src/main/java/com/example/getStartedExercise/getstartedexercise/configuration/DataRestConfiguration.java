package com.example.getStartedExercise.getstartedexercise.configuration;

import com.example.getStartedExercise.getstartedexercise.repository.model.Book;
import com.example.getStartedExercise.getstartedexercise.repository.model.Category;
import com.example.getStartedExercise.getstartedexercise.repository.model.projection.BookProjection;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * DataRestConfiguration
 */
@Configuration
public class DataRestConfiguration implements RepositoryRestConfigurer{

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Book.class,Category.class);
        config.getProjectionConfiguration()
          .addProjection(BookProjection.class);
          config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.DEFAULT);
    }

}