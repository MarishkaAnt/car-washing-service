package org.philosophy.carwashing.dto;

import lombok.*;

/**
 *  Dto - абстрактный класс для добавления в дто полей скидки
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class DiscountableDto {

    protected Boolean hasDiscount;
    protected Integer discountAmount;

}
