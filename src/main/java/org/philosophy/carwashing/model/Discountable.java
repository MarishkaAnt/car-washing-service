package org.philosophy.carwashing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@MappedSuperclass
@Setter
@Getter
public abstract class Discountable {

    protected Boolean hasDiscount;
    protected BigDecimal discountAmount;
}
