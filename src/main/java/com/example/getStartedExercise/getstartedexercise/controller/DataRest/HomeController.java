package com.example.getStartedExercise.getstartedexercise.controller.DataRest;

import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController
 */
@Controller
public class HomeController {
    @Autowired
    CategoryRepository categoryRepository;
    
    @GetMapping("/api/index")
    public String index(ModelMap modelMap ){
    modelMap.addAttribute("CATEGORIES", categoryRepository.findAll());
       return "api-index";
   }
    
}