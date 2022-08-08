package org.philosophy.carwashing.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.BoxResponseDto;
import org.philosophy.carwashing.mapper.GenericResponseMapper;
import org.philosophy.carwashing.model.Box;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BoxResponseMapper extends
        GenericResponseMapper<Box, BoxResponseDto> {

    @Mapping(target="boxTypeId", source="boxType.id")
    BoxResponseDto toDto(Box box);
}
