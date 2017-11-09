package com.github.habiteria.domain.service.module;

import com.github.habiteria.domain.model.CheckerType;
import com.github.habiteria.domain.service.HabitSnapshotService;

/**
 * @author Alex Ivchenko
 */
public interface HabitCheckerTypeModule extends HabitSnapshotService {
    CheckerType supports();
}
