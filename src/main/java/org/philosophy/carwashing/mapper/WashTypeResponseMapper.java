package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.WashTypeResponseDto;
import org.philosophy.carwashing.model.WashType;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface WashTypeResponseMapper extends
        GenericEntityMapper<WashType, WashTypeResponseDto>  {

    WashType toEntity(WashTypeResponseDto dto);

    WashTypeResponseDto toDto(WashType washType);
}
