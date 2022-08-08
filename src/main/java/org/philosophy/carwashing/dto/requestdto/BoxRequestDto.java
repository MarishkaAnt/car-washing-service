package org.philosophy.carwashing.dto.requestdto;

import lombok.*;
import org.philosophy.carwashing.dto.DiscountableDto;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoxRequestDto extends DiscountableDto {

    private Integer boxTypeId;
    private LocalTime openTime;
    private LocalTime closeTime;

}
