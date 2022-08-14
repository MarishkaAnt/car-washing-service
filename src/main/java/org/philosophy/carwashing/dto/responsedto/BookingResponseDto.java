package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.model.User;
import org.philosophy.carwashing.model.WashType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookingResponseDto {

    private Integer id;
    private WashType washType;
    private Box box;
    private Integer userId;
    private BigDecimal totalCost;
    private LocalDateTime datetimeFrom;
    private LocalDateTime datetimeTo;
    private BookingStatuses status;
    private Boolean isPaid;
    private LocalDateTime paymentTime;

}
