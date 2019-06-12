package com.example.getStartedExercise.getstartedexercise.repository.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * ArticleDmlProvider
 */
public class ArticleDmlProvider {

    String findByName() {
        return new SQL(){{
            SELECT("*");
            FROM("tb_article");
        }}.toString();
    }
}