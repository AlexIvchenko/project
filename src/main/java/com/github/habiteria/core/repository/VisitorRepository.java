package com.github.habiteria.core.repository;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.Visitor;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
public interface VisitorRepository extends CrudRepository<Visitor, Long> {
    boolean existsByUserAndDate(User user, LocalDate date);

    Visitor getByUserAndDate(User user, LocalDate date);
}
