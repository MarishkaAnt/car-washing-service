package org.philosophy.carwashing.dto.requestdto;

import lombok.*;

/**
 * Dto для установки max и min значений скидок
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DiscountSettingsRequestDto {

    private Integer id;
    private Double maxValue;
    private Double minValue;

}
