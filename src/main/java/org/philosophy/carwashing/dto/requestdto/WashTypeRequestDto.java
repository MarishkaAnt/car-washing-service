package org.philosophy.carwashing.dto.requestdto;

import lombok.*;

import java.time.Duration;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WashTypeRequestDto {

    private String name;
    private Duration duration;
    private Double cost;

}
