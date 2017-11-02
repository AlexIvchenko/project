package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.Distributor;
import com.github.netcracker2017team.project.domain.model.foreign.ForeignGoal;
import com.github.netcracker2017team.project.domain.model.Goal;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
@RepositoryTest
@DatabaseSetup("ForeignGoalRepoTest.xml")
@Slf4j
@PropertySource("classpath:application.yml")
public class ForeignGoalRepositoryTest {
    @Autowired
    private ForeignGoalRepository repository;

    private Distributor distributor;

    @Before
    public void setUp() throws Exception {
        distributor = new Distributor();
        distributor.setId("76dd6349-d2f8-4b44-ba2a-2b861508e7b4");
    }

    @Value("${spring.datasource.url}") String url;

    @Test
    @ExpectedDatabase(value = "ForeignGoalRepoTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void givenGoals_whenFindNotAcceptedGoal_thenFoundNotAccepted() throws Exception {
        Set<ForeignGoal> goals = repository.findByDistributorWithStatus(distributor, Goal.Status.PUBLISHED);
        assertThat(goals).hasSize(1).are(notAccepted());
    }

    private static Condition<ForeignGoal> notAccepted() {
        return new Condition<ForeignGoal>() {
            @Override
            public boolean matches(ForeignGoal foreignGoal) {
                return foreignGoal.getStatus() == Goal.Status.PUBLISHED;
            }
        };
    }
}