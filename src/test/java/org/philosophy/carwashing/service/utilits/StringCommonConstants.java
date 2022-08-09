package org.philosophy.carwashing.service.utilits;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class StringCommonConstants {
    public static final int CORRECT_ID_EQUALS_1 = 1;
    public static final double SPEEDCOEFFICIENT_EQUAL_1 = 1.0;
    public static final LocalTime CORRECT_OPEN_TIME_8_00 = LocalTime.parse("08:00");
    public static final LocalDateTime CORRECT_REQUEST_START_DATE_TIME = LocalDateTime.parse("2022-08-09T09:00");
    public static final LocalTime CORRECT_CLOSE_TIME_21_00 = LocalTime.parse("21:00");
    public static final LocalDateTime CORRECT_REQUEST_END_DATE_TIME = LocalDateTime.parse("2022-08-09T21:00");
    public static final int SAVED_ENTITY_ID_EQUAL_3 = 3;
    public static final Duration CORRECT_DURATION_PT_15_M = Duration.parse("PT15M");
    public static final double CORRECT_COST_300 = 300.0;
    public static final LocalDateTime DATETIME_FROM_10 = LocalDateTime.parse("2022-08-09T10:00:00");
    public static final LocalDateTime DATE_TIME_TO_10_15 = LocalDateTime.parse("2022-08-09T10:15:00");
    public static final Duration PT_15_M = Duration.parse("PT15M");
    public static final LocalDateTime RESPONSE_DATETIME_FROM = LocalDateTime.parse("2022-08-09T09:00:00");
    public static final LocalDateTime RESPONSE_DATETIME_TO = LocalDateTime.parse("2022-08-09T09:15:00");

}
