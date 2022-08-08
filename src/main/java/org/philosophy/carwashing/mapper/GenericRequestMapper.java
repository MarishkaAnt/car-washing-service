package org.philosophy.carwashing.mapper;

public interface GenericRequestMapper<E, D>{

    E toEntity(D d);
}
