package org.philosophy.carwashing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.DiscountSettingsRequestDto;
import org.philosophy.carwashing.model.DiscountSettings;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface DiscountSettingsRequestMapper extends
        GenericEntityMapper<DiscountSettings, DiscountSettingsRequestDto>  {

    DiscountSettings toEntity(DiscountSettingsRequestDto discountDto);

    DiscountSettingsRequestDto toDto(DiscountSettings discount);
}
