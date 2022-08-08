package org.philosophy.carwashing.dto.requestdto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WashTypeRequestDto {

    private String name;
    private Duration duration;
    private BigDecimal cost;

}
