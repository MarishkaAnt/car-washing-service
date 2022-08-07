package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.enums.BookingStatuses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingAfterPaymentResponseDto {

    private Integer id;
    private Integer request_id;
    private BigDecimal totalCost;
    private BookingStatuses status;
    private Boolean isPaid;
    private LocalDateTime payment_time;

}
