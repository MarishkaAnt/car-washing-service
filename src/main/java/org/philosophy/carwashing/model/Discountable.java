package org.philosophy.carwashing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.math.RoundingMode;

@MappedSuperclass
@Setter
@Getter
public abstract class Discountable {

    @Column(name = "has_discount")
    protected Boolean hasDiscount;

    @Min(0)
    @Max(100)
    @Column(name = "discount_amount")
    protected Integer discountAmount;

}
