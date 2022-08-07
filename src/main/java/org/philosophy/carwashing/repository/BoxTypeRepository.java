package org.philosophy.carwashing.repository;

import org.philosophy.carwashing.model.BoxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxTypeRepository extends JpaRepository<BoxType,Integer> {
}
