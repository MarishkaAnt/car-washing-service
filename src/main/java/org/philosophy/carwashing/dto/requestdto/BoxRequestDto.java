package org.philosophy.carwashing.dto.requestdto;

import lombok.*;
import org.philosophy.carwashing.model.BoxType;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoxRequestDto {

    private Integer boxTypeId;
    private LocalTime openTime;
    private LocalTime closeTime;

}
