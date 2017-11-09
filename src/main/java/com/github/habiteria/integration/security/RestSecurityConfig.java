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
                .antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers("/api/users/{userId}/**")
                .access("@userAuthService.isAuthorized(authentication, #userId)")
                .antMatchers("/api/users/{userId}/habits/{habitId}")
                .access("@userAuthService.isAuthorized(#userId, #habitId)")
                .anyRequest().authenticated()
                .and().httpBasic();

    }
}
