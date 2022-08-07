package org.philosophy.carwashing.dto.responsedto;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponseDto {

    private Integer id;
    private Integer boxId;
    private Integer washTypeId;
    private Integer userId;
    private LocalDateTime responseDatetimeFrom;
    private LocalDateTime responseDatetimeTo;
    private Duration duration;

}
