package com.github.habitaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
        basePackageClasses = {Habitaria.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class Habitaria {
    public static void main(String[] args) {
        SpringApplication.run(Habitaria.class, args);
    }
}
