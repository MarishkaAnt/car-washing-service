package org.philosophy.carwashing.dto.responsedto;

import org.philosophy.carwashing.enums.BookingStatuses;

import java.math.BigDecimal;

public class BookingBeforePaymentResponseDto {

    private Integer id;
    private Integer request_id;
    private BigDecimal totalCost;
    private BookingStatuses status;
    private Boolean isPaid;

}
