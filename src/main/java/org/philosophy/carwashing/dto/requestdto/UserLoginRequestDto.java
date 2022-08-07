package org.philosophy.carwashing.dto.requestdto;

import lombok.*;

/**
 * Dto - вход пользователя в систему
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDto {

    private String email;
    private String password;

}
