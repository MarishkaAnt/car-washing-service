package org.philosophy.carwashing.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.RequestRequestDto;
import org.philosophy.carwashing.mapper.GenericRequestMapper;
import org.philosophy.carwashing.model.Request;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface RequestRequestMapper extends
        GenericRequestMapper<Request, RequestRequestDto> {

    @Mapping(target = "box.id", source = "boxId")
    @Mapping(target = "washType.id", source = "washTypeId")
    @Mapping(target = "user.id", source = "userId")
    Request toEntity(RequestRequestDto requestDto);

}