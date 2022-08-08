package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.dto.DiscountableDto;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoxResponseDto extends DiscountableDto {

    private Integer id;
    private Integer boxTypeId;
    private LocalTime openTime;
    private LocalTime closeTime;

}
