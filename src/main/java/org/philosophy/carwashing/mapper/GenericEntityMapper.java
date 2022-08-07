package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface GenericEntityMapper <E, D>{

    E toEntity(D d);
    D toDto(E e);
}
