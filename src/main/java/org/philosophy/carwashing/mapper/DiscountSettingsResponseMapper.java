package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.responsedto.DiscountSettingsResponseDto;
import org.philosophy.carwashing.model.DiscountSettings;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface DiscountSettingsResponseMapper extends
        GenericEntityMapper<DiscountSettings, DiscountSettingsResponseDto>  {

    DiscountSettings toEntity(DiscountSettingsResponseDto discountDto);

    DiscountSettingsResponseDto toDto(DiscountSettings discount);
}
