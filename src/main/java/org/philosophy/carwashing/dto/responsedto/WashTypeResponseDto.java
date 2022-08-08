package org.philosophy.carwashing.dto.responsedto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WashTypeResponseDto {

    private Integer id;
    private String name;
    private Duration duration;
    private BigDecimal cost;

}
