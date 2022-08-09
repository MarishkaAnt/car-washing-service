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
    private Double totalCost;
    private BookingStatuses status;
    private Boolean isPaid;
    private LocalDateTime paymentTime;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
    private Duration duration;
    private Integer userId;
    private Box box;

}
