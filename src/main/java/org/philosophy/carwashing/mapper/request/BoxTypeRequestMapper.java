package org.philosophy.carwashing.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.BoxTypeRequestDto;
import org.philosophy.carwashing.mapper.GenericRequestMapper;
import org.philosophy.carwashing.model.BoxType;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface BoxTypeRequestMapper extends
        GenericRequestMapper<BoxType, BoxTypeRequestDto> {

    BoxType toEntity(BoxTypeRequestDto boxTypeRequestDto);

}
