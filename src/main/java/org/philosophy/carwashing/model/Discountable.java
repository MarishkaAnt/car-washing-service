package org.philosophy.carwashing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.philosophy.carwashing.util.CommonStringConstants.MAX_PERCENT_VALUE;
import static org.philosophy.carwashing.util.CommonStringConstants.MIN_PERCENT_VALUE;

@MappedSuperclass
@Setter
@Getter
public abstract class Discountable {

    @Column(name = "has_discount")
    protected Boolean hasDiscount;

    @Min(MIN_PERCENT_VALUE)
    @Max(MAX_PERCENT_VALUE)
    @Column(name = "discount_amount")
    protected Integer discountAmount;

}
