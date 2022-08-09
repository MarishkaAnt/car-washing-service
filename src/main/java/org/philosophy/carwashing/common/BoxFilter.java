package org.philosophy.carwashing.common;

import org.philosophy.carwashing.dto.requestdto.BoxRequestDto;
import org.philosophy.carwashing.dto.responsedto.BoxResponseDto;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.model.BoxType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;

import static org.philosophy.carwashing.common.BoxSpecification.*;
import static org.springframework.data.jpa.domain.Specification.*;

public class BoxFilter implements Filter<Box>{

    private Integer id;
    private BoxType boxType;
    private LocalTime openTime;
    private LocalTime closeTime;


    @Override
    public Specification<Box> toSpecification() {
        return where(hasId(id))
                .and(hasBoxType(boxType));
    }
}
