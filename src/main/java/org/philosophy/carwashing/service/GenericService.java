package org.philosophy.carwashing.service;

import org.philosophy.carwashing.dto.requestdto.BookingRequestDto;
import org.philosophy.carwashing.dto.responsedto.BookingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GenericService<ID, R, D> {

    R create(D dto);

    void deleteById(ID id);

    R findById(ID id);

    Page<R> findAll(Pageable pageable);

    R update(ID id, D dto);
}

