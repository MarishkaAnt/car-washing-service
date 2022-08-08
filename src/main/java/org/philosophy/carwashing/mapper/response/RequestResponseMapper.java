package org.philosophy.carwashing.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.RequestResponseDto;
import org.philosophy.carwashing.mapper.GenericResponseMapper;
import org.philosophy.carwashing.model.Request;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface RequestResponseMapper extends
        GenericResponseMapper<Request, RequestResponseDto> {

    @Mapping(target = "boxId", source = "box.id")
    @Mapping(target = "washTypeId", source = "washType.id")
    @Mapping(target = "userId", source = "user.id")
    RequestResponseDto toDto(Request request);
}
