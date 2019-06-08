package com.example.getStartedExercise.getstartedexercise.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

/**
 * FileConguration
 */

public class FileConguration {

    
      public static String uploaded(MultipartFile files) {
        
        String fileName = UUID.randomUUID() + "." + files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf(".") + 1);
        try {
            Files.copy(files.getInputStream(),Paths.get("src/main/resources/static/img/",fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return fileName;
    }
}