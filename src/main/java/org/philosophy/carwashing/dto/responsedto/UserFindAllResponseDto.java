package org.philosophy.carwashing.dto.responsedto;

import lombok.*;
import org.philosophy.carwashing.dto.DiscountableDto;

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
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Integer roleId;

}
