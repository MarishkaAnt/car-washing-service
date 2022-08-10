package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.model.Box;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookingResponseDto {

    private Integer id;
    private Integer requestId;
    private Box box;
    private Integer userId;
    private Double totalCost;
    private LocalDateTime datetimeFrom;
    private LocalDateTime datetimeTo;
    private Duration duration;
    private BookingStatuses status;
    private Boolean isPaid;
    private LocalDateTime paymentTime;

}
