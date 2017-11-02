package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.Distributor;
import com.github.netcracker2017team.project.domain.model.foreign.ForeignGoal;
import com.github.netcracker2017team.project.domain.model.Goal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface ForeignGoalRepository extends CrudRepository<ForeignGoal, String> {
    @Query("select goal from ForeignGoal goal where goal.template.distributor = :distributor and goal.status = :status")
    Set<ForeignGoal> findByDistributorWithStatus(@Param("distributor") Distributor distributor, @Param("status") Goal.Status status);
}
