package org.philosophy.carwashing.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.WashTypeRequestDto;
import org.philosophy.carwashing.mapper.GenericRequestMapper;
import org.philosophy.carwashing.model.WashType;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface WashTypeRequestMapper extends
        GenericRequestMapper<WashType, WashTypeRequestDto> {

    WashType toEntity(WashTypeRequestDto dto);

}
