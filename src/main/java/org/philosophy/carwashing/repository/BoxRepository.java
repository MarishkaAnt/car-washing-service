package org.philosophy.carwashing.repository;

import org.philosophy.carwashing.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer> {
    @Override
    @NonNull
    @Query("select b from Box as b join BoxType bt on b.boxType.id=bt.id")
    List<Box> findAll();
}
