package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.Distributor;
import com.github.netcracker2017team.project.domain.model.ForeignGoal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface ForeignGoalRepository extends CrudRepository<ForeignGoal, String> {
    @Query("select goal from ForeignGoal goal where goal.template.distributor = ?1 and goal.status = 'WAIT_FOR_ACCEPT'")
    Set<ForeignGoal> findNotAccepted(Distributor distributor);
}
