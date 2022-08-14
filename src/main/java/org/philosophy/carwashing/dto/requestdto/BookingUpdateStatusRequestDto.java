package org.philosophy.carwashing.dto.requestdto;

import lombok.*;

/**
 * Dto брони
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookingUpdateStatusRequestDto {

    private Integer id;
    private String status;
}
