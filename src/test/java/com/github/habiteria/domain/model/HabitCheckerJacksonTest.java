package com.github.habiteria.domain.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Ivchenko
 */
public class HabitCheckerJacksonTest {
    private ObjectMapper mapper;
    private String serialized;

    @Before
    public void setUp() throws Exception {
        HabitChecker daily = new DailyChecker();
        HabitChecker weekly = new WeeklyChecker();
        List<HabitChecker> checkers = Arrays.asList(daily, weekly);
        mapper = new ObjectMapper();
        serialized = mapper.writeValueAsString(checkers);
    }

    @Test
    public void givenTwoSubclasses_whenDeserialize_thenDeserializationCorrect() throws Exception {
        HabitChecker[] checkers = mapper.readValue(serialized, HabitChecker[].class);
        assertThat(checkers[0]).isInstanceOf(DailyChecker.class);
        assertThat(checkers[1]).isInstanceOf(WeeklyChecker.class);
    }
}