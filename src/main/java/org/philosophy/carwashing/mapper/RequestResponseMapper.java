package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.RequestResponseDto;
import org.philosophy.carwashing.model.Request;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface RequestResponseMapper extends
        GenericEntityMapper<Request, RequestResponseDto>  {

    Request toEntity(RequestResponseDto requestDto);

    RequestResponseDto toDto(Request request);
}
