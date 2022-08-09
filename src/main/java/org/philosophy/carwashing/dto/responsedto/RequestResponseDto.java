package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.model.User;
import org.philosophy.carwashing.model.WashType;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RequestResponseDto {

    private Integer id;
    private Integer washTypeId;
    private Integer userId;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;

}
