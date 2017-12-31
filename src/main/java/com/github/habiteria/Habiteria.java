package com.github.habiteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
        basePackageClasses = {Habiteria.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class Habiteria {
    public static void main(String[] args) {
        SpringApplication.run(Habiteria.class, args);
    }
}
