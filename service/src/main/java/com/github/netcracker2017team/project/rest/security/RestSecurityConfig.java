package com.github.netcracker2017team.project.rest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

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
                    .antMatchers(HttpMethod.POST, "/api/signUp").permitAll()
                    .antMatchers("/api/user/{username}/**")
                    .access("@userAuthService.isAuthorized(authentication, #username)")
                    .anyRequest().fullyAuthenticated()
                    .and().httpBasic()
                    /* this doesn't work. */
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
