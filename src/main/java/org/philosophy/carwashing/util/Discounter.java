package org.philosophy.carwashing.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.philosophy.carwashing.util.CommonStringConstants.MAX_PERCENT_VALUE;
import static org.philosophy.carwashing.util.CommonStringConstants.MIN_PERCENT_VALUE;

public class Discounter {

    public static BigDecimal applyDiscount(BigDecimal cost, Integer discountAmount){
        return discountAmount == MIN_PERCENT_VALUE ? cost :
        cost.multiply(BigDecimal.valueOf(MAX_PERCENT_VALUE - discountAmount))
                .divide(BigDecimal.valueOf(MAX_PERCENT_VALUE), RoundingMode.HALF_UP);
    }

}
