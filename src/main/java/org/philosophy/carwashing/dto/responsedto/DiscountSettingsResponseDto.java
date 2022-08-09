package org.philosophy.carwashing.dto.responsedto;

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
public class DiscountSettingsResponseDto {

    private Double maxValue;
    private Double minValue;

}
