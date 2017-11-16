package com.github.habiteria;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.builders.Users;
import com.github.habiteria.core.repository.UserRepository;
import com.github.habiteria.core.domain.service.visitor.VisitorService;
import com.github.habiteria.security.DatabaseUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
public class DatabaseUserDetailsServiceTest {
    @TestConfiguration
    public static class DatabaseUserDetailsServiceTestConfiguration {
        @Bean
        public UserDetailsService userService() {
            return new DatabaseUserDetailsService();
        }
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private VisitorService visitorService;

    @Before
    public void setUp() throws Exception {
        User user = Users.withUsername("test")
                .withPassword("test")
                .withEmail("test")
                .withName("test", "test");
        Mockito.when(userRepository.findByUsername("test")).thenReturn(user);
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() throws Exception {
        UserDetails found = userDetailsService.loadUserByUsername("test");
        assertThat(found.getUsername()).isEqualTo("test");
    }
}