package org.philosophy.carwashing.model;

import lombok.*;
import org.philosophy.carwashing.enums.BoxTypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BoxTypes type;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Boolean hasDiscount;
    private BigDecimal discountAmount;
    private BigDecimal speedCoefficient;
}