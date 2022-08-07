package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.BoxTypeRequestDto;
import org.philosophy.carwashing.model.BoxType;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BoxTypeRequestMapper extends
        GenericEntityMapper<BoxType, BoxTypeRequestDto>  {

    BoxType toEntity(BoxTypeRequestDto boxTypeRequestDto);

    BoxTypeRequestDto toDto(BoxType boxType);
}
