package org.philosophy.carwashing.dto.requestdto;

import lombok.*;
import org.philosophy.carwashing.dto.DiscountableDto;

/**
 * Dto - регистрация пользователя в системе
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserSignInRequestDto extends DiscountableDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
