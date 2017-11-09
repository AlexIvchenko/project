package com.github.habiteria.domain.service.habit;

import com.github.habiteria.domain.model.CheckerType;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alex Ivchenko
 */
public class Modules<T extends Module> {
    private final Map<CheckerType, T> modules;

    public Modules(List<T> modules) {
        this.modules = modules.stream()
                .collect(Collectors.toMap(T::supports, module -> module));
    }


    public T get(CheckerType type) {
        return modules.get(type);
    }

    public Stream<T> all() {
        return Stream.of(CheckerType.values())
                .map(this::get)
                .filter(Objects::nonNull);
    }
}
