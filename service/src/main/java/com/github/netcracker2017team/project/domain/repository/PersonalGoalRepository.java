package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.PersonalGoal;
import com.github.netcracker2017team.project.domain.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface PersonalGoalRepository extends CrudRepository<PersonalGoal, String> {
    @Query("select g from PersonalGoal g where g.doer = :doer and g.status = 'PUBLISHED'")
    Set<PersonalGoal> findPublished(@Param("doer") User doer);

    @Query("select g from PersonalGoal g where g.doer = :doer and g.status = 'NEW'")
    Set<PersonalGoal> findNew(@Param("doer") User doer);
}
