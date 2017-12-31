package com.github.habiteria.integration.controller.annotations;

import org.springframework.format.annotation.DateTimeFormat;

import java.lang.annotation.*;

/**
 * @author Alex Ivchenko
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
public @interface Date {
}
