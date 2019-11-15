package com.example.getStartedExercise.getstartedexercise.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * GlobalConfiguration
 */

 @Configuration
 @EnableTransactionManagement
public class GlobalConfiguration {

    // @Bean
    // @Profile("psql")
    // public DataSource dataSource () {
    //     DriverManagerDataSource db = new DriverManagerDataSource ();
    //     db.setDriverClassName("org.postgresql.Driver");
    //     db.setUrl("jdbc:postgresql://localhost:5432/Article");
    //     db.setUsername("sokhok");
    //     db.setPassword("123!@#");
    //     return db;
    // }

    @Bean
    @Profile("memory")
    public DataSource h2dataSource () {
        String pather = "classpath:/static/table/";
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2);
       // embeddedDatabaseBuilder.addScript(pather+"data.sql");
       // embeddedDatabaseBuilder.addScripts(pather+"table.sql",pather+"data.sql");
        return embeddedDatabaseBuilder.build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(h2dataSource());
        return transactionManager;
    }
}