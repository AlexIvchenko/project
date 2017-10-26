package com.github.netcracker2017team.project.domain.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@ContextConfiguration(classes = Config.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class UserRepositoryTest {
//    @Autowired
//    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DatabaseSetup("UserRepositoryTest.givenUser_whenFindByUsername_thenReturnUser.given.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "UserRepositoryTest.givenUser_whenFindByUsername_thenReturnUser.then.xml")
    public void name() throws Exception {
        userRepository.findByUsername("Alex");
    }

//    @Test
//    public void givenUser_whenFindByUsername_thenReturnUser() throws Exception {
//        User user = User.builder()
//                .username("test")
//                .email("test")
//                .password("pass")
//                .build();
//
//        testEntityManager.persist(user);
//        testEntityManager.flush();
//
//        User found = userRepository.findByUsername(user.getUsername());
//        assertThat(found).isEqualToComparingFieldByField(user);
//    }
}