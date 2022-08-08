package org.philosophy.carwashing.mapper.request;

import org.mapstruct.*;
import org.philosophy.carwashing.dto.requestdto.BoxRequestDto;
import org.philosophy.carwashing.mapper.GenericRequestMapper;
import org.philosophy.carwashing.model.Box;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BoxRequestMapper extends
        GenericRequestMapper<Box, BoxRequestDto> {

    @Mapping(target="boxType.id", source = "boxTypeId")
    Box toEntity(BoxRequestDto dto);

}
