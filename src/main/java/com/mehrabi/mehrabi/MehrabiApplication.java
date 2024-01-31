package com.mehrabi.mehrabi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;



@SpringBootApplication
@EntityScan(basePackages = "com.mehrabi.mehrabi")
@ComponentScan
@OpenAPIDefinition
@EnableJpaRepositories
public class MehrabiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MehrabiApplication.class, args);
    }
}
