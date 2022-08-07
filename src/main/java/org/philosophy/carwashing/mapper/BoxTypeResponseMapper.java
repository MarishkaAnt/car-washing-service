package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.BoxTypeResponseDto;
import org.philosophy.carwashing.model.BoxType;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BoxTypeResponseMapper extends
        GenericEntityMapper<BoxType, BoxTypeResponseDto>  {

    BoxType toEntity(BoxTypeResponseDto boxTypeResponseDto);

    BoxTypeResponseDto toDto(BoxType boxType);
}
