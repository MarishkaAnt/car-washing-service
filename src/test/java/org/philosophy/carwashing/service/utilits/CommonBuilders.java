package org.philosophy.carwashing.service.utilits;

import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.enums.BoxTypes;
import org.philosophy.carwashing.enums.WashTypes;
import org.philosophy.carwashing.model.*;

import static org.philosophy.carwashing.service.utilits.StringCommonConstants.*;

public class CommonBuilders {

    public static Box getCorrectBoxSpeedStandard() {
        BoxType correctBoxType = BoxType.builder()
                .id(CORRECT_ID_EQUALS_1)
                .type(BoxTypes.STANDARD)
                .speedCoefficient(SPEEDCOEFFICIENT_EQUAL_1)
                .build();

        return Box.builder()
                .id(CORRECT_ID_EQUALS_1)
                .boxType(correctBoxType)
                .openTime(CORRECT_OPEN_TIME_8_00)
                .closeTime(CORRECT_CLOSE_TIME_21_00)
                .build();
    }

    public static WashType getCorrectWashTypeLight() {
        return WashType.builder()
                .id(CORRECT_ID_EQUALS_1)
                .name(WashTypes.LIGHT)
                .duration(CORRECT_DURATION_PT_15_M)
                .cost(CORRECT_COST_300)
                .build();
    }
    public static Booking getCorrectBooking(){
        return Booking.builder()
                .id(CORRECT_ID_EQUALS_1)
//                .user(CORRECT_ID_EQUALS_1)
                .totalCost(CORRECT_COST_300)
                .datetimeFrom(DATETIME_FROM_10)
                .datetimeTo(DATE_TIME_TO_10_15)
                .status(BookingStatuses.NEW)
                .isPaid(false)
                .build();
    }

}
