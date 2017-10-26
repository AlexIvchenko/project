package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.Distributor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface DistributorRepository extends CrudRepository<Distributor, UUID> {
}
