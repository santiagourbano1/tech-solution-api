package com.epw.tech_solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.epw.tech_solution")
public class TechSolutionApplication {
    public static void main(String[] args) {
        SpringApplication.run(TechSolutionApplication.class, args);
    }
}