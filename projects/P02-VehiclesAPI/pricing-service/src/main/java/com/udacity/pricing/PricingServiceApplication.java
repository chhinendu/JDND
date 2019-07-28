package com.udacity.pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Creates a Spring Boot Application to run the Pricing Service.
 * TODO: Convert the application from a REST API to a microservice.
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.udacity")
public class PricingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricingServiceApplication.class, args);
    }

}
