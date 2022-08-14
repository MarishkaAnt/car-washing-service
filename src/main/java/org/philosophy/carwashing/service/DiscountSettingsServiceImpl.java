package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.responsedto.DiscountSettingsResponseDto;
import org.philosophy.carwashing.mapper.response.DiscountSettingsResponseMapper;
import org.philosophy.carwashing.repository.DiscountSettingsRepository;
import org.philosophy.carwashing.util.CommonStringConstants;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

import static org.philosophy.carwashing.util.CommonStringConstants.*;

@Service
@Validated
@RequiredArgsConstructor
public class DiscountSettingsServiceImpl {

    private final DiscountSettingsRepository discountSettingsRepository;
    private final DiscountSettingsResponseMapper discountSettingsResponseMapper;

    public Integer setMax(@Min(MIN_PERCENT_VALUE) @Max(MAX_PERCENT_VALUE) Integer newMaxValue) {
        return discountSettingsRepository.setMaxValue(newMaxValue);
    }

    public Integer setMin(@Min(MIN_PERCENT_VALUE) @Max(MAX_PERCENT_VALUE) Integer newMinValue) {
        return discountSettingsRepository.setMinValue(newMinValue);
    }

    public List<DiscountSettingsResponseDto> findAll(){
        return discountSettingsRepository.findAll()
                .stream().map(discountSettingsResponseMapper::toDto)
                .collect(Collectors.toList());
    }

}
