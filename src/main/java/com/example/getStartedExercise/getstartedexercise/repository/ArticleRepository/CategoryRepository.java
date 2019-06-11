package com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository;

import java.util.List;

import com.example.getStartedExercise.getstartedexercise.repository.model.Category;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepository
 */
@Repository
public interface CategoryRepository {

   @Insert("INSERT INTO tb_categories (name, status) VALUES (#{name} ,1 ) ")
   boolean add (Category category) ;
   
   @Select("SELECT * FROM tb_categories where status =1 ")
   List<Category> findAll(); 
   
   @Select("SELECT * FROM tb_categories where id = #{id} AND status =1 ")
   Category find(int id);

   boolean update(int index,Category category);

   @Update("UPDATE tb_categories SET name = #{name} WHERE id = #{id} ")
   boolean update (Category category);

   @Update("UPDATE tb_categories SET status = 0 WHERE id = #{id} ")
   boolean delete(int id); 
}