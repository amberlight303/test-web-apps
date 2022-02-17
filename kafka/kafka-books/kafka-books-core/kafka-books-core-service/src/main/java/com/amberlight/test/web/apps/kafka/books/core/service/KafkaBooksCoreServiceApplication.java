package com.amberlight.test.web.apps.kafka.books.core.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = { "com.amberlight.test.web.apps.kafka.books.core" })
@EntityScan(basePackages = { "com.amberlight.test.web.apps.kafka.books.core" })
@EnableJpaRepositories(basePackages = { "com.amberlight.test.web.apps.kafka.books.core" })
@SpringBootApplication
public class KafkaBooksCoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaBooksCoreServiceApplication.class, args);
    }

}
