package com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository;

import java.util.List;

import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepositoryImp;
import com.example.getStartedExercise.getstartedexercise.repository.model.Article;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * ArticleRepoDB
 */
@Repository
public interface ArticleRepoDB extends ArticleRepositoryImp {

     @Insert("INSERT INTO tb_articles (title, author, category_id ,description , thumbnail,status) VALUES (#{title},#{author},#{category.id} ,#{description} ,#{thumbnail} ,1 ) ")
     boolean add(Article article);
      
    //@Select("SELECT * FROM tb_articles where status = 1 ORDER BY id asc")
     @Select("SELECT  tba.* , tbc.name as name FROM TB_ARTICLES AS tba LEFT JOIN TB_CATEGORIES AS tbc ON tba.category_id = tbc.id where tba.status = 1")
     @Results({
       @Result( column = "category_id" ,property = "category.id",jdbcType=JdbcType.INTEGER),
       @Result( column = "name" ,property = "category.name",jdbcType=JdbcType.VARCHAR)
     }) 
     List<Article> findAll(); 

     //@Select("SELECT * FROM tb_articles where id = #{id} AND status = 1")
     @Select("SELECT  tba.* , tbc.name as name FROM TB_ARTICLES AS tba LEFT JOIN TB_CATEGORIES AS tbc ON tba.category_id = tbc.id  WHERE tba.id = #{id} AND tba.status = 1")
     @Results({
       @Result( column = "category_id" ,property = "category.id",jdbcType=JdbcType.INTEGER),
       @Result( column = "name" ,property = "category.name",jdbcType=JdbcType.VARCHAR)
     }) 
     Article find(int id);

     @Update("UPDATE tb_articles SET title= #{title},author=#{author},category_id=#{category.id},description =#{description},thumbnail = #{thumbnail}  WHERE id=#{id}")
     boolean update(Article article);

     @Update("Update tb_articles SET status=0 WHERE id=#{id} ")
      boolean delete(int id) ;

      @Update("Update tb_articles SET status=0 WHERE category_id=#{category_id} ")
      boolean deleteByCategoryId(int category_id);
    
    
    @Select("SELECT  tba.* , tbc.name as name FROM TB_ARTICLES AS tba LEFT JOIN TB_CATEGORIES AS tbc ON tba.category_id = tbc.id where tba.title like #{title} AND tba.status = 1")
     @Results({
       @Result( column = "category_id" ,property = "category.id",jdbcType=JdbcType.INTEGER),
       @Result( column = "name" ,property = "category.name",jdbcType=JdbcType.VARCHAR)
     }) 
      List<Article> findByTitle(String title);

      @Select("SELECT  tba.* , tbc.name as name FROM TB_ARTICLES AS tba LEFT JOIN TB_CATEGORIES AS tbc ON tba.category_id = tbc.id where tba.title like #{title} AND tba.category_id = #{category_id} AND tba.status = 1")
      @Results({
        @Result( column = "category_id" ,property = "category.id",jdbcType=JdbcType.INTEGER),
        @Result( column = "name" ,property = "category.name",jdbcType=JdbcType.VARCHAR)
      }) 
       List<Article> findByTitleAndType(String title,int category_id);
}