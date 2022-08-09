package org.philosophy.carwashing.repository;

import org.philosophy.carwashing.dto.responsedto.RequestResponseDto;
import org.philosophy.carwashing.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {

}
