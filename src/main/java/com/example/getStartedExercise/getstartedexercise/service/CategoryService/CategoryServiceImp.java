package com.example.getStartedExercise.getstartedexercise.service.CategoryService;

import java.util.List;

import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository.ArticleRepoDB;
import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository.CategoryRepository;
import com.example.getStartedExercise.getstartedexercise.repository.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CategoryReposityImp
 */

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository cateRepo ;

    @Override
    public boolean add(Category category) {
       
        cateRepo.add(category);
        return false;
    }

    @Override
    public List<Category> findAll() {
        return cateRepo.findAll();
    }

    @Override
    public Category find(int id) {
        return cateRepo.find(id);
    }

    @Override
    public boolean update(int index, Category category) {
        return false;
    }

    @Override
    public boolean update(Category category) {
        if(cateRepo.update(category)){
            return true;
        }
        return false;
    }

    @Autowired
    private ArticleRepoDB articleRepo;

    @Override
    public boolean delete(int id) {
        if(cateRepo.delete(id)){
            articleRepo.deleteByCategoryId(id);
            return true;
        }
        return false;
    }

    
}