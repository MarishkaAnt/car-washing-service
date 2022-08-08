package org.philosophy.carwashing.dto.requestdto;

import lombok.*;

/**
 * Dto - регистрация пользователя в системе
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
