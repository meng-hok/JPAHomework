package com.example.getStartedExercise.getstartedexercise.controller.ArticleController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * FileUploadController
 */

// @Configuration
// @PropertySource("classpath:/fileupload.properties")

@Controller
public class FileUploadController implements WebMvcConfigurer {

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // registry.addResourceHandler("image/**").addResourceLocations("file");
    // }

    @GetMapping(value = "upload")
    public String photoUpload() {

        return "upload";
    }

    @PostMapping("/upload")
    public String uploaded(@RequestParam("files") List<MultipartFile> files) {
        if (!files.isEmpty()) {
            files.forEach(file -> {
                try {
                    Files.copy(file.getInputStream(),Paths.get("/home/menghok/Desktop/get-started-exercise/src/main/resources/filestorer",file.getOriginalFilename()));
                } catch (IOException e) {
                   e.printStackTrace();
                }
            });
        } 
        return "redirect:/home";
    }

    


    // @RequestMapping("/upload/test")
    // public String test() {

    //     return
    // }


    
}