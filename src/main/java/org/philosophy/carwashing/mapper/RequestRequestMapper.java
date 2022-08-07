package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.RequestRequestDto;
import org.philosophy.carwashing.model.Request;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface RequestRequestMapper extends
        GenericEntityMapper<Request, RequestRequestDto>  {

    Request toEntity(RequestRequestDto requestDto);

    RequestRequestDto toDto(Request request);
}
