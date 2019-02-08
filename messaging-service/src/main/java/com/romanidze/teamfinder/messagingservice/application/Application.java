package com.romanidze.teamfinder.messagingservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 05.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan({"com.romanidze.teamfinder.messagingservice.config",
                "com.romanidze.teamfinder.messagingservice.controllers",
                "com.romanidze.teamfinder.messagingservice.receivers",
                "com.romanidze.teamfinder.messagingservice.services"})
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
