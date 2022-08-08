package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.BoxResponseDto;
import org.philosophy.carwashing.dto.responsedto.BoxTypeResponseDto;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.model.BoxType;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BoxResponseMapper extends
        GenericEntityMapper<Box, BoxResponseDto>  {

    @Mapping(target="boxType.id", source="boxTypeId")
    Box toEntity(BoxResponseDto boxResponseDto);

    @Mapping(target="boxTypeId", source="boxType.id")
    BoxResponseDto toDto(Box box);
}
