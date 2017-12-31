package com.github.habiteria.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * @author Alex Ivchenko
 */
public class LocalDateRange implements Iterable<LocalDate> {
    private final LocalDate start;
    private final LocalDate end;

    public LocalDateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public Stream<LocalDate> stream() {
        return Stream.iterate(start, d -> d.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end) + 1);
    }

    @Override
    public Iterator<LocalDate> iterator() {
        return new LocalDateRangeIterator(start, end);
    }

    private static class LocalDateRangeIterator implements Iterator<LocalDate> {
        private final LocalDate start;
        private final LocalDate end;
        private LocalDate cur;

        public LocalDateRangeIterator(LocalDate start, LocalDate end) {
            this.start = start;
            this.end = end;
            cur = start;
        }

        @Override
        public boolean hasNext() {
            return !cur.isAfter(end);
        }

        @Override
        public LocalDate next() {
            LocalDate ret = cur;
            cur = cur.plusDays(1);
            return ret;
        }
    }
}
