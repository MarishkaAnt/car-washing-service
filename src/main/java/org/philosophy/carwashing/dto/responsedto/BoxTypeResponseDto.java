package org.philosophy.carwashing.dto.responsedto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BoxTypeResponseDto {

    private Integer id;
    private Integer typeId;
    private Double speedcoefficient;

}
