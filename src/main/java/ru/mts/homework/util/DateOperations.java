package ru.mts.homework.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@UtilityClass
public class DateOperations {
    public static LocalDate minLocal(LocalDate date1, LocalDate date2) {
        return Collections.min(Arrays.asList(date1, date2));
    }

    public static LocalDate maxLocal(LocalDate date1, LocalDate date2) {
        return Collections.max(Arrays.asList(date1, date2));
    }
}
