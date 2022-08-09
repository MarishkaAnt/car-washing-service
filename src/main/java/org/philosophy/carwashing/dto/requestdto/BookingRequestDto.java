package org.philosophy.carwashing.dto.requestdto;

import lombok.*;

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
    private Boolean isPaid;

}
