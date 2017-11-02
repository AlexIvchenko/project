package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.Distributor;
import com.github.netcracker2017team.project.domain.model.template.distributor.DistributorGoalTemplate;
import com.github.netcracker2017team.project.domain.model.template.distributor.DistributorStepTemplate;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
@RepositoryTest
public class DistributorGoalTemplateRepositoryTest {
    @Autowired
    private DistributorRepository distributorRepository;

    @Autowired
    private DistributorGoalTemplateRepository goalRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DatabaseSetup("DistributorGoalTemplateRepositoryTest.givenGoalTemplate_whenAddOneStep_whenStepAdded.given.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "DistributorGoalTemplateRepositoryTest.givenGoalTemplate_whenAddOneStep_whenStepAdded.then.xml")
    public void givenGoalTemplate_whenAddOneStep_whenStepAdded() throws Exception {
        Distributor distributor = distributorRepository.findOne("1113f14d-5a6f-4b4f-b580-69e16bf14415");
        Set<DistributorGoalTemplate> goals = goalRepository.findByDistributor(distributor);
        assertThat(goals.size()).isEqualTo(1);
        DistributorGoalTemplate goal = toOne(goals);
        DistributorStepTemplate step = new DistributorStepTemplate();
        step.setId("9256285f-e7a9-473a-9ec6-a3a6e607671c");
        step.setName("step3");
        goal.addStep(step);
        goalRepository.save(goal);
        entityManager.flush();
    }

    private static <T> T toOne(Collection<? extends T> collection) {
        return new ArrayList<T>(collection).get(0);
    }
}