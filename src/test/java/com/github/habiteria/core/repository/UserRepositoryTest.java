package com.github.habiteria.core.repository;

import com.github.habiteria.core.entities.User;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
@RepositoryTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DatabaseSetup("UserRepositoryTest.givenUser_whenFindByUsername_thenReturnUser.given.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "UserRepositoryTest.givenUser_whenFindByUsername_thenReturnUser.then.xml")
    public void givenUserInDb_whenFind_whenReturnedRightUser() throws Exception {
        User user = userRepository.findByUsername("Alex");
        assertThat(user.getUsername()).isEqualTo("Alex");
    }
}