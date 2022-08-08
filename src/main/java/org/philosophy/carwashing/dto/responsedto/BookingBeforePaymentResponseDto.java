package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.enums.BookingStatuses;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingBeforePaymentResponseDto {

    private Integer id;
    private Integer requestId;
    private BigDecimal totalCost;
    private BookingStatuses status;
    private Boolean isPaid;

}
