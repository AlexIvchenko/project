package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.Notification;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Alex Ivchenko
 */
public interface NotificationRepository extends CrudRepository<Notification, String> {

}
