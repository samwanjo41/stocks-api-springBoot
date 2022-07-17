package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


public class SwaggerConfig {

    @Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // we want to display the swagger for all classes that are annotated with @RestController
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                // display all paths not specifically to "/api/v1"...
                .paths(PathSelectors.any())
                .build()
                // we could have added "/api/v1" here and have removed it from our rest controller
                .pathMapping("/")
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                // include the metadata details from the method below
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                // name of your API project
                .title("Stock API")
                .description("Sample Java Spring Boot API using MongoDB Atlas")
                // this version follows semantic versioning
                // at the time this codelab was created, the project had been updated to 2.0.0 from 1.0.0
                // there are breaking changes from the old code base, therefore we increment the major integer from 1 -> 2
                .version("1.0.0")
                .contact(new Contact("@samwanjo41", "https://github.com/samwanjo41/stocks-api-springBoot", "test@gmail.com"))
                .build();
    }
}
