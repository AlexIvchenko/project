package com.github.habiteria;

import com.github.habiteria.core.domain.service.visitor.VisitorService;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.builders.Users;
import com.github.habiteria.core.repository.UserRepository;
import com.github.habiteria.security.DatabaseUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
public class DatabaseUserDetailsServiceTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private VisitorService visitorService;


    private UserDetailsService userDetailsService;

    @Before
    public void setUp() throws Exception {
        userDetailsService = new DatabaseUserDetailsService(userRepository, visitorService);
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