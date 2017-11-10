package com.github.habiteria.core.domain.habit;

import com.github.habiteria.core.model.ScheduleType;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alex Ivchenko
 */
public class Modules<T extends Module> {
    private final Map<ScheduleType, T> modules;

    public Modules(List<T> modules) {
        this.modules = modules.stream()
                .collect(Collectors.toMap(T::supports, module -> module));
    }


    public T get(ScheduleType type) {
        return modules.get(type);
    }

    public Stream<T> all() {
        return Stream.of(ScheduleType.values())
                .map(this::get)
                .filter(Objects::nonNull);
    }
}