package com.github.habiteria.core.domain.service.progress;

import com.github.habiteria.core.entities.Habit;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Service
public class ProgressComputerImpl implements ProgressComputer {
    @Override
    public int compute(Habit habit) {
        LocalDate startDate = habit.getSchedule().getStart().toLocalDate();
        LocalDate endDate = habit.getSchedule().getEnd().toLocalDate();
        Integer period = startDate.until(endDate).getDays();
        int progress;
        if (period > 0) {
            progress = startDate.until(LocalDate.now()).getDays() * 100 / period;
        } else {
            progress = 100;
        }
        return progress;
    }
}
