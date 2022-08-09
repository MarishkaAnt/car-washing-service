package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.dto.DiscountableDto;

/**
 * Dto для вывода пользователя на фронтенд
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserViewResponseDto extends DiscountableDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String role;

}
