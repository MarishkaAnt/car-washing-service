package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.BoxRequestDto;
import org.philosophy.carwashing.model.Box;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BoxRequestMapper extends
        GenericEntityMapper<Box, BoxRequestDto>  {

    Box toEntity(BoxRequestDto boxRequestDto);

    BoxRequestDto toDto(Box box);
}
