package com.romanidze.teamfinder.teamservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * 04.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan({"com.romanidze.teamfinder.teamservice.config",
                "com.romanidze.teamfinder.teamservice.controllers",
                "com.romanidze.teamfinder.teamservice.receivers", "com.romanidze.teamfinder.teamservice.services",
                "com.romanidze.teamfinder.teamservice.mappers", "com.romanidze.teamfinder.teamservice.runners"})
@EnableMongoRepositories(basePackages = {"com.romanidze.teamfinder.teamservice.repositories"})
@EnableMongoAuditing
@EntityScan(basePackages = {"com.romanidze.teamfinder.teamservice.domain"})
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
