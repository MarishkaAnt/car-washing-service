package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.model.BoxType;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoxResponseDto {

    private Integer id;
    private BoxType boxType;
    private LocalTime openTime;
    private LocalTime closeTime;

}
