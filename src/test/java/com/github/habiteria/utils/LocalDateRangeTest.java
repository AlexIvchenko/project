package com.github.habiteria.utils;

import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Ivchenko
 */
public class LocalDateRangeTest {
    @Test
    public void iteratorTest() throws Exception {
        LocalDateRange range = new LocalDateRange(LocalDate.now(), LocalDate.now().plusDays(3));
        assertThat(range).hasSize(4)
                .contains(LocalDate.now(),
                        LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(2),
                        LocalDate.now().plusDays(3));
    }

    @Test
    public void streamTest() throws Exception {
        LocalDateRange range = new LocalDateRange(LocalDate.now(), LocalDate.now().plusDays(3));
        assertThat(range.stream().collect(Collectors.toList()))
                .hasSize(4)
                .contains(LocalDate.now(),
                        LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(2),
                        LocalDate.now().plusDays(3));
    }
}