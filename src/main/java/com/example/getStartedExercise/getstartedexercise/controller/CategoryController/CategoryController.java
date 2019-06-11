package com.example.getStartedExercise.getstartedexercise.controller.CategoryController;

import java.util.ArrayList;

import com.example.getStartedExercise.getstartedexercise.repository.model.Category;
import com.example.getStartedExercise.getstartedexercise.service.CategoryService.CategoryServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * CategoryController
 */

 @Controller
 @RequestMapping("category")
public class CategoryController {
    // 
    @Autowired
    public CategoryServiceImp cateSer;

    @GetMapping(value = "add")
    public String add(ModelMap modelMap){
        modelMap.addAttribute("CATEGORY",new Category());
        // 
        return "category/form";
    }

    @PostMapping(value = "add")
    public String add(@ModelAttribute Category category){
        cateSer.add(category);
        return "redirect:/category/show";
    }

    @GetMapping(value = "edit")
    public String update(@RequestParam("id") int id,ModelMap modelMap){
        Category category = cateSer.find(id);
        System.out.println(category);
        modelMap.addAttribute("CATEGORY",category);
        return "category/form";
    }

    @PostMapping(value = "edit")
    public String update(@ModelAttribute Category category){
        cateSer.update(category);
        return "redirect:/category/show";
    }

    
    @GetMapping(value = "show")
    public String display(ModelMap modelMap){
        modelMap.addAttribute("CATEGORIES", cateSer.findAll());
        // modelMap.addAttribute("CATEGORY", new ArrayList<>());
        return "category/table";
    }

    @GetMapping(value = "delete")
    public String delete(@RequestParam("id") int id) {
        cateSer.delete(id);
        return "redirect:/category/show";
    }
}