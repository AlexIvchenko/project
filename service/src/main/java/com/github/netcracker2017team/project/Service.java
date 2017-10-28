package com.github.netcracker2017team.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
        basePackageClasses = {Service.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class Service {
    public static void main(String[] args) {
        SpringApplication.run(Service.class, args);
    }
}
