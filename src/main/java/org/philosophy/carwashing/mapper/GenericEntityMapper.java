package org.philosophy.carwashing.mapper;

public interface GenericEntityMapper <E, D>{

    E toEntity(D d);
    D toDto(E e);
}
