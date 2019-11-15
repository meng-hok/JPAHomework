package com.example.getStartedExercise.getstartedexercise.service.CategoryService;

import java.util.List;

import javax.management.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository.ArticleRepoDB;
import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository.CategoryRepository;
import com.example.getStartedExercise.getstartedexercise.repository.datarepository.CategoryDataRepository;
import com.example.getStartedExercise.getstartedexercise.repository.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CategoryReposityImp
 */

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryDataRepository cateRepo ;
    @Autowired
    private ArticleRepoDB articleRepo;


    public CategoryServiceImp(CategoryDataRepository cateRepo, ArticleRepoDB articleRepo) {
        this.cateRepo = cateRepo;
        this.articleRepo = articleRepo;
    }


    @Override
    public boolean add(Category category) {
       
        Category _category = cateRepo.save(category);
        return _category == null ? false : true;
    }

    @Override
    public List<Category> findAll() {
        return cateRepo.getByStatus(1);
    }

    @Override
    public Category find(int id) {
        return cateRepo.getOne(id);
    }

    @Override
    public boolean update(int index, Category category) {
        return false;
    }

    @Override
    public boolean update(Category category) {
        // if(cateRepo.update(category)){
        //     return true;
        // }
        // return false;
        Category _category = cateRepo.getOne(category.getId());

        _category = category;
        Category category2 = cateRepo.save(_category);

        return category2 == null ? false : true;

    }

  
    @Override
    @Transactional
    public boolean delete(int id) {

        
       Category _category = cateRepo.getOne(id);

        _category.setStatus(0);
        Category category2 = cateRepo.save(_category);
        if( category2 == null  ){
            return false;
        }else{
            cateRepo.deleteAllBooksAfterCategoryGettingDeleted(id);

            return true;
        }
        
    }

    
}