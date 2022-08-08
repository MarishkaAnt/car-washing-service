package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.RequestResponseDto;
import org.philosophy.carwashing.model.Request;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface RequestResponseMapper extends
        GenericEntityMapper<Request, RequestResponseDto>  {

    @Mapping(target = "box.id", source = "boxId")
    @Mapping(target = "washType.id", source = "washTypeId")
    @Mapping(target = "user.id", source = "userId")
    Request toEntity(RequestResponseDto requestDto);

    @Mapping(target = "boxId", source = "box.id")
    @Mapping(target = "washTypeId", source = "washType.id")
    @Mapping(target = "userId", source = "user.id")
    RequestResponseDto toDto(Request request);
}
