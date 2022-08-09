package org.philosophy.carwashing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Setter
@Getter
public abstract class Discountable {

    protected Boolean hasDiscount;
    protected Double discountAmount;
}
