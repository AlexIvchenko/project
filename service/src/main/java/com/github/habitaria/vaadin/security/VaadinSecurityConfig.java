package com.github.habitaria.vaadin.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * @author Alex Ivchenko
 */
@Configuration
@Order(2)
public class VaadinSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")).accessDeniedPage("/accessDenied")
                .and().authorizeRequests()
                .antMatchers("/VAADIN/**", "/PUSH/**", "/UIDL/**", "/login", "/login/**", "/error/**", "/accessDenied/**", "/vaadinServlet/**").permitAll()
                .anyRequest()
                .authenticated()
                .and().logout()
                .logoutUrl("/logout");
    }
}
