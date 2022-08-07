package org.philosophy.carwashing.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService<ID, R, D> {

    R create(D dto);
    R update(ID id, D dto);
    void delete(ID id);
    R findById(ID id);
    Page<R> findAll(Pageable pageable);

}

