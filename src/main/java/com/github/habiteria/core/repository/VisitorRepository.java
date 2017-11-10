package com.github.habiteria.core.repository;

import com.github.habiteria.core.model.User;
import com.github.habiteria.core.model.Visitor;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
public interface VisitorRepository extends CrudRepository<Visitor, String> {
    boolean existsByUserAndDate(User user, LocalDate date);

    Visitor getByUserAndDate(User user, LocalDate date);
}
