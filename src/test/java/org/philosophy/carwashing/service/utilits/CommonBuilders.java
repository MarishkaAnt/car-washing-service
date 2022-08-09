package org.philosophy.carwashing.service.utilits;

import org.philosophy.carwashing.dto.requestdto.RequestRequestDto;
import org.philosophy.carwashing.dto.responsedto.RequestResponseDto;
import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.enums.BoxTypes;
import org.philosophy.carwashing.enums.WashTypes;
import org.philosophy.carwashing.model.*;

import java.time.LocalDateTime;

import static org.philosophy.carwashing.service.utilits.StringCommonConstants.*;

public class CommonBuilders {
    public static RequestRequestDto getCorrectInputDto(){
        RequestRequestDto dto = new RequestRequestDto();
        dto.setWashTypeId(CORRECT_ID_EQUALS_1);
        dto.setUserId(CORRECT_ID_EQUALS_1);
        dto.setDatetimeFrom(LocalDateTime.parse("2022-08-09T09:00:00"));
        dto.setDatetimeTo(LocalDateTime.parse("2022-08-09T18:00:00"));
        return dto;
    }

    public static RequestResponseDto getCorrectOutputDto(){
        RequestResponseDto dto = new RequestResponseDto();
        dto.setId(CORRECT_ID_EQUALS_1);
        dto.setWashTypeId(CORRECT_ID_EQUALS_1);
        dto.setUserId(CORRECT_ID_EQUALS_1);
        return dto;
    }

    public static Box getCorrectBoxSpeedStandard() {
        BoxType correctBoxType = BoxType.builder()
                .id(CORRECT_ID_EQUALS_1)
                .type(BoxTypes.STANDARD)
                .speedcoefficient(SPEEDCOEFFICIENT_EQUAL_1)
                .build();

        return Box.builder()
                .id(CORRECT_ID_EQUALS_1)
                .boxType(correctBoxType)
                .openTime(CORRECT_OPEN_TIME_8_00)
                .closeTime(CORRECT_CLOSE_TIME_21_00)
                .build();
    }

    public static Request getCorrectRequestAfterSaving(){
        return Request.builder()
                .id(CORRECT_ID_EQUALS_1)
                .washType(getCorrectWashTypeLight())
                .userId(CORRECT_ID_EQUALS_1)
                .datetimeFrom(CORRECT_REQUEST_START_DATE_TIME)
                .datetimeTo(CORRECT_REQUEST_END_DATE_TIME)
                .responseDatetimeFrom(RESPONSE_DATETIME_FROM)
                .responseDatetimeTo(RESPONSE_DATETIME_TO)
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
                .request(getCorrectRequestAfterSaving())
                .userId(CORRECT_ID_EQUALS_1)
                .totalCost(CORRECT_COST_300)
                .datetimeFrom(DATETIME_FROM_10)
                .datetimeTo(DATE_TIME_TO_10_15)
                .duration(PT_15_M)
                .status(BookingStatuses.NEW)
                .isPaid(false)
                .build();
    }

}
