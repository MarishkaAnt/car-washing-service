package org.philosophy.carwashing.dto.requestdto;

import lombok.*;
import org.philosophy.carwashing.enums.BookingStatuses;

import java.math.BigDecimal;

/**
 * Dto брони создается после того, как пользователь получит RequestResponseDto
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {

    private Integer requestId;
    private BigDecimal totalCost;
    private BookingStatuses status;
    private Boolean isPaid;

}
