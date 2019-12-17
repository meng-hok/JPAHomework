package com.example.getStartedExercise.getstartedexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2WebMvc
@Import({SpringDataRestConfiguration.class})
public class GetStartedExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetStartedExerciseApplication.class, args);
	}

}
