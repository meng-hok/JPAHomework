package com.example.getStartedExercise.getstartedexercise.controller.CategoryController;

import java.util.ArrayList;

import javax.validation.Valid;

import com.example.getStartedExercise.getstartedexercise.repository.model.Category;
import com.example.getStartedExercise.getstartedexercise.service.CategoryService.CategoryServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    public String add(@Valid @ModelAttribute("CATEGORY") Category category,BindingResult bindResult,ModelMap modelMap){
         if(bindResult.hasErrors()){
            modelMap.addAttribute("CATEGORY",category);
            return "category/form";
         }
        cateSer.add(category);
        return "redirect:/category/show";
    }

    @GetMapping(value = "edit")
    public String update(@RequestParam("id") int id,ModelMap modelMap){
        Category category = cateSer.find(id);
        modelMap.addAttribute("CATEGORY",category);
        return "category/form";
    }

    @PostMapping(value = "edit")
    public String update(@Valid @ModelAttribute("CATEGORY") Category category,BindingResult bindResult,ModelMap modelMap){
        if(bindResult.hasErrors()){
            modelMap.addAttribute("CATEGORY",category);
            return "category/form";
         }
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