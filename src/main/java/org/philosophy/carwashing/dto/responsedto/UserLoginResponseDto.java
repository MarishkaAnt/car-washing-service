package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.dto.DiscountableDto;
import org.philosophy.carwashing.model.Role;

/**
 *  Dto - ответ пользователю после регистрации
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDto extends DiscountableDto {

    private Integer id;
    private String first_name;
    private String last_name;
    private Boolean isActive;
    private Role role;

}
