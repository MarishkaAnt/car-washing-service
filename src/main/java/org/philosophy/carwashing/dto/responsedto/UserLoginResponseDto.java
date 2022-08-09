package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.dto.DiscountableDto;
import org.philosophy.carwashing.enums.Roles;
import org.philosophy.carwashing.model.Booking;

import java.util.List;

/**
 *  Dto - ответ пользователю после регистрации
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserLoginResponseDto extends DiscountableDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Roles role;
    List<Booking> bookings;

}
