package com.example.getStartedExercise.getstartedexercise.repository.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * MyProvider
 */
public class MyProvider {

    public String findAll () {
        // System.out.println("hehehehe");
        return new SQL(){{
            SELECT("tba.* , tbc.name as name ");
            FROM("TB_ARTICLES AS tba LEFT JOIN TB_CATEGORIES AS tbc ON tba.category_id = tbc.id where tba.status = 1");
        }}.toString();
    }

    public String findById() {
        return new SQL (){{
            INSERT_INTO("tb_article   ");
            VALUES("id,name,testing", "1,23,values");
        }}.toString();
    }

    
}