package org.philosophy.carwashing.repository;

import org.philosophy.carwashing.model.WashType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WashTypeRepository extends JpaRepository<WashType,Integer> {
}
