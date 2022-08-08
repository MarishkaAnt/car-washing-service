package org.philosophy.carwashing.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.DiscountSettingsResponseDto;
import org.philosophy.carwashing.mapper.GenericResponseMapper;
import org.philosophy.carwashing.model.DiscountSettings;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface DiscountSettingsResponseMapper extends
        GenericResponseMapper<DiscountSettings, DiscountSettingsResponseDto> {

    DiscountSettingsResponseDto toDto(DiscountSettings discount);
}
