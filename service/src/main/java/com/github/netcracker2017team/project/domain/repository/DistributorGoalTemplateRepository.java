package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.Distributor;
import com.github.netcracker2017team.project.domain.model.template.distributor.DistributorGoalTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@RepositoryRestResource
public interface DistributorGoalTemplateRepository extends CrudRepository<DistributorGoalTemplate, String> {
    Set<DistributorGoalTemplate> findByDistributor(Distributor distributor);
}
