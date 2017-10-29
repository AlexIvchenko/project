package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.model.template.UserGoalTemplate;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
@RepositoryTest
public class UserGoalTemplateRepositoryTest {
    @Autowired
    private UserGoalTemplateRepository goalRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DatabaseSetup("UserGoalTemplateRepositoryTest.givenGoalWithSteps_whenRemoveGoal_whenStepsRemovedAsWell.given.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "UserGoalTemplateRepositoryTest.givenGoalWithSteps_whenRemoveGoal_whenStepsRemovedAsWell.then.xml")
    public void givenGoalWithSteps_whenRemoveGoal_whenStepsRemovedAsWell() throws Exception {
        User user = new User();
        user.setId("9e3c448d-c48b-4b16-b49f-dca17ded6de9");
        Set<UserGoalTemplate> goals = goalRepository.findByOwner(user);
        for (UserGoalTemplate goal: goals) {
            assertThat(goal.getOwner()).isEqualTo(user);
        }
        goalRepository.delete(goals);
        entityManager.flush();
    }
}