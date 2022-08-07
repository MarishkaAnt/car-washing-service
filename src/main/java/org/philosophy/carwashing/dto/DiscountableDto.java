package org.philosophy.carwashing.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 *  Dto - абстрактный класс для добавления в дто полей скидки
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class DiscountableDto {

    protected Boolean hasDiscount;
    protected BigDecimal discountAmount;

}
