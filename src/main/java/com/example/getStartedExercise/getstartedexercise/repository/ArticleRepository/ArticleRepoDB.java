package com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository;

import java.util.List;

import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepositoryImp;
import com.example.getStartedExercise.getstartedexercise.repository.model.Article;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * ArticleRepoDB
 */
@Repository
public interface ArticleRepoDB extends ArticleRepositoryImp {

     @Insert("INSERT INTO tb_articles (title, author, category_id ,description , thumbnail,status) VALUES (#{title},#{author},#{category.id} ,#{description} ,#{thumbnail} ,1 ) ")
     boolean add(Article article);
      
    @Select("SELECT * FROM tb_articles where status = 1 ORDER BY id asc")
     List<Article> findAll(); 

     @Select("SELECT * FROM tb_articles where id = #{id} AND status = 1")
     Article find(int id);

     @Update("UPDATE tb_articles SET title= #{title},author=#{author},description =#{description},thumbnail = #{thumbnail}  WHERE id=#{id}")
     boolean update(Article article);

     @Update("Update tb_articles SET status=0 WHERE id=#{id} ")
      boolean delete(int id) ;

      @Update("Update tb_articles SET status=0 WHERE category_id=#{category_id} ")
      boolean deleteByCategoryId(int category_id);


}