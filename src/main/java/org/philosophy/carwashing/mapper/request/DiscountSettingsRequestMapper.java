package org.philosophy.carwashing.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.philosophy.carwashing.dto.requestdto.DiscountSettingsRequestDto;
import org.philosophy.carwashing.mapper.GenericRequestMapper;
import org.philosophy.carwashing.model.DiscountSettings;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface DiscountSettingsRequestMapper extends
        GenericRequestMapper<DiscountSettings, DiscountSettingsRequestDto> {

    DiscountSettings toEntity(DiscountSettingsRequestDto discountDto);

}
