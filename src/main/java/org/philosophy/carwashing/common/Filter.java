package org.philosophy.carwashing.common;

import org.springframework.data.jpa.domain.Specification;

@FunctionalInterface
public interface Filter<R> {
    Specification<R> toSpecification();
}
