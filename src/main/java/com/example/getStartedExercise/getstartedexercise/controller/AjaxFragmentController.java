package com.example.getStartedExercise.getstartedexercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AjaxFragmentController
 */
@Controller
public class AjaxFragmentController {
    
    @RequestMapping("header") 
    public String navbar(){
        return "_nav";
    }
}