package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.BoxRequestDto;
import org.philosophy.carwashing.model.Box;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BoxRequestMapper extends
        GenericEntityMapper<Box, BoxRequestDto>  {

    @Mapping(target="boxType.id", source="boxTypeId")
    Box toEntity(BoxRequestDto dto);

    @Mapping(target="boxTypeId", source="boxType.id")
    BoxRequestDto toDto(Box box);
}
