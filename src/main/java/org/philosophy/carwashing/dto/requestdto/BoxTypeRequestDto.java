package org.philosophy.carwashing.dto.requestdto;

import lombok.*;
import org.philosophy.carwashing.enums.BoxTypes;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BoxTypeRequestDto {

    private Integer typeId;
    private Double speedcoefficient;

}
