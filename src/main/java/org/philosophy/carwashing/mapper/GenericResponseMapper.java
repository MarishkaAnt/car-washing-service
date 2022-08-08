package org.philosophy.carwashing.mapper;

public interface GenericResponseMapper<E, D>{

    D toDto(E e);
}
