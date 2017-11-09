package com.github.habiteria.domain.service.habit;

import com.github.habiteria.domain.model.CheckerType;

/**
 * @author Alex Ivchenko
 */
public interface Module {
    CheckerType supports();
}
