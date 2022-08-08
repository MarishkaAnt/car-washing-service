package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.WashTypeRequestDto;
import org.philosophy.carwashing.model.WashType;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface WashTypeRequestMapper extends
        GenericEntityMapper<WashType, WashTypeRequestDto>  {

    WashType toEntity(WashTypeRequestDto dto);

    WashTypeRequestDto toDto(WashType washType);
}
