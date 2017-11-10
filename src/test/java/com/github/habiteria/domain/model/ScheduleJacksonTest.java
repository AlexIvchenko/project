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
public class ScheduleJacksonTest {
    private ObjectMapper mapper;
    private String serialized;

    @Before
    public void setUp() throws Exception {
        Schedule daily = new DailySchedule();
        Schedule weekly = new WeeklySchedule();
        List<Schedule> checkers = Arrays.asList(daily, weekly);
        mapper = new ObjectMapper();
        serialized = mapper.writeValueAsString(checkers);
    }

    @Test
    public void givenTwoSubclasses_whenDeserialize_thenDeserializationCorrect() throws Exception {
        Schedule[] checkers = mapper.readValue(serialized, Schedule[].class);
        assertThat(checkers[0]).isInstanceOf(DailySchedule.class);
        assertThat(checkers[1]).isInstanceOf(WeeklySchedule.class);
    }
}