package com.amberlight.test.web.apps.book.book.core.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = { "com.amberlight.test.web.apps.book.book.core" })
@EntityScan(basePackages = { "com.amberlight.test.web.apps.book.book.core" })
@EnableJpaRepositories(basePackages = { "com.amberlight.test.web.apps.book.book.core" })
@SpringBootApplication
public class BookCoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookCoreServiceApplication.class, args);
    }

}
