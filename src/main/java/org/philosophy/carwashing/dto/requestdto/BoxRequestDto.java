package org.philosophy.carwashing.dto.requestdto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closeTime;

}
