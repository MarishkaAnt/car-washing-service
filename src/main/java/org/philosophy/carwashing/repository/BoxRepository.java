package org.philosophy.carwashing.repository;

import org.philosophy.carwashing.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer> {
}
