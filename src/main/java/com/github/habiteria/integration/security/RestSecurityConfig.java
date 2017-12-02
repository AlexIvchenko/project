package com.github.habiteria.integration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Alex Ivchenko
 */
@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers("/users/{userId}/**")
                .access("@userAuthService.isAuthorized(authentication, #userId)")
                .antMatchers("/users/{userId}/habits/{habitId}")
                .access("@userAuthService.isAuthorized(#userId, #habitId)")
                .anyRequest().authenticated()
                .and().httpBasic().and().cors();

    }
}
