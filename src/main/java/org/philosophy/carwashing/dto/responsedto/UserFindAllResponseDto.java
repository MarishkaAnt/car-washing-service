package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.dto.DiscountableDto;
import org.philosophy.carwashing.model.Role;

/**
 * Dto для вывода в списки
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserFindAllResponseDto extends DiscountableDto {

    private Integer id;
    private String first_name;
    private String last_name;
    private Boolean isActive;
    private Role role;

}
