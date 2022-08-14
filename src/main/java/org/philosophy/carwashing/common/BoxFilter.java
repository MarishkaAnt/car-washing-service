package org.philosophy.carwashing.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.philosophy.carwashing.model.Box;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;

import static org.philosophy.carwashing.common.BoxSpecification.*;
import static org.springframework.data.jpa.domain.Specification.*;

@Getter
@Setter
@NoArgsConstructor
public class BoxFilter implements Filter<Box>{

    private Integer id;
    private Integer boxTypeId;
    private LocalTime openTime;
    private LocalTime closeTime;

    @Override
    public Specification<Box> toSpecification() {
        return where(hasId(id))
                .and(hasBoxTypeId(boxTypeId))
                .and(hasOpenTime(openTime))
                .and(hasCloseTime(closeTime));
    }
}
