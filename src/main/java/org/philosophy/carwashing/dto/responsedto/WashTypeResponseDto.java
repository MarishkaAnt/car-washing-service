package org.philosophy.carwashing.dto.responsedto;

import lombok.*;

import java.time.Duration;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WashTypeResponseDto {

    private Integer id;
    private String name;
    private Duration duration;
    private Double cost;

}
