package org.philosophy.carwashing.dto.requestdto;

import org.philosophy.carwashing.enums.BookingStatuses;

import java.math.BigDecimal;

/**
 * Dto брони создается после того, как пользователь получит RequestResponseDto
 */

public class BookingRequestDto {

    private Integer request_id;
    private BigDecimal totalCost;
    private BookingStatuses status;
    private Boolean isPaid;

}
