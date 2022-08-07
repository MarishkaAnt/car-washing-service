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

    private String first_name;
    private String last_name;
    private String email;
    private String password;

}
