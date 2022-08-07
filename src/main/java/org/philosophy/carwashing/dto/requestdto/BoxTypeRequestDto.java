package org.philosophy.carwashing.dto.requestdto;

import lombok.*;
import org.philosophy.carwashing.enums.BoxTypes;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoxTypeRequestDto {

    private Integer type_id;
    private Double speedcoefficient;

}
