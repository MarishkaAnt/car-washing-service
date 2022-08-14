package org.philosophy.carwashing.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Discounter {

    public static final int MAX_PERCENT_VALUE = 100;
    public static final int MIN_PERCENT_VALUE = 0;

    public static BigDecimal applyDiscount(BigDecimal cost, Integer discountAmount){
        return discountAmount == MIN_PERCENT_VALUE ? cost :
        cost.multiply(BigDecimal.valueOf(MAX_PERCENT_VALUE - discountAmount))
                .divide(BigDecimal.valueOf(MAX_PERCENT_VALUE), RoundingMode.HALF_UP);
    }

}
