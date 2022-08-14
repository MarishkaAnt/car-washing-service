package org.philosophy.carwashing.service.utilits;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class StringCommonConstants {
    public static final int CORRECT_ID_EQUALS_1 = 1;
    public static final double SPEEDCOEFFICIENT_EQUAL_1 = 1.0;
    public static final LocalTime CORRECT_OPEN_TIME_8_00 = LocalTime.parse("08:00");
    public static final LocalTime CORRECT_CLOSE_TIME_21_00 = LocalTime.parse("21:00");
    public static final Duration CORRECT_DURATION_PT_15_M = Duration.parse("PT15M");
    public static final BigDecimal CORRECT_COST_300 = BigDecimal.valueOf(300.0);
    public static final LocalDateTime DATETIME_FROM_10 = LocalDateTime.parse("2022-08-09T10:00:00");
    public static final LocalDateTime DATE_TIME_TO_10_15 = LocalDateTime.parse("2022-08-09T10:15:00");
    public static final Duration PT_15_M = Duration.parse("PT15M");

}
