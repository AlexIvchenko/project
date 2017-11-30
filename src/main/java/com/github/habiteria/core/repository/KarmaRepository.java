package com.github.habiteria.core.repository;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.imps.KarmaImpl;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Alex Ivchenko
 */
public interface KarmaRepository extends CrudRepository<KarmaImpl, Long> {
    KarmaImpl getByOwner(User owner);
}
