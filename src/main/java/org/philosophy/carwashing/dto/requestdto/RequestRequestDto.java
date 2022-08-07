package org.philosophy.carwashing.dto.requestdto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestRequestDto {

    private Integer boxId;
    private Integer washTypeId;
    private Integer userId;
    private LocalDateTime datetimeFrom;
    private LocalDateTime datetimeTo;

}
