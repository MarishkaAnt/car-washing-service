package org.philosophy.carwashing.repository;

import org.philosophy.carwashing.dto.requestdto.BoxRequestDto;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.model.BoxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer>, JpaSpecificationExecutor<Box> {

    @NonNull
    @Query("select b from Box as b order by b.id asc")
    List<Box> findAllOrderById();

}
