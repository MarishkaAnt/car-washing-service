package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.responsedto.BoxResponseDto;
import org.philosophy.carwashing.dto.responsedto.DiscountSettingsResponseDto;
import org.philosophy.carwashing.mapper.response.DiscountSettingsResponseMapper;
import org.philosophy.carwashing.model.DiscountSettings;
import org.philosophy.carwashing.repository.DiscountSettingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountSettingsServiceImpl {

    private final DiscountSettingsRepository discountSettingsRepository;
    private final DiscountSettingsResponseMapper discountSettingsResponseMapper;

    public BoxResponseDto setMax(Integer id, Double newMaxValue) {
        return null;
    }

    public BoxResponseDto setMin(Integer id, Double newMinValue) {
        return null;
    }


    public void deleteById(Integer id) {

    }

    public BoxResponseDto findById(Integer id) {
        if(!(id == null)){

        }
        return null;
    }

    public List<DiscountSettingsResponseDto> findAll(){
        List<DiscountSettings> discounts = discountSettingsRepository.findAll();
        return discounts.stream()
                .map(discountSettingsResponseMapper::toDto)
                .collect(Collectors.toList());

    }

}
