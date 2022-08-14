package org.philosophy.carwashing.dto.requestdto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Dto брони
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookingRequestDto {

    private Integer washTypeId;
    private Integer boxId;
    private Integer userId;
    private LocalDateTime datetimeFrom;
    private LocalDateTime datetimeTo;

}
