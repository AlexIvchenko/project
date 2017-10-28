package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.Distributor;
import com.github.netcracker2017team.project.domain.model.DistributorGoal;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface DistributorGoalRepository extends CrudRepository<DistributorGoal, String> {
    Set<DistributorGoal> findByDistributor(Distributor distributor);
}
