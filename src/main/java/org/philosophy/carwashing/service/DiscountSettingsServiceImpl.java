package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.DiscountSettingsRequestDto;
import org.philosophy.carwashing.dto.responsedto.BoxResponseDto;
import org.philosophy.carwashing.dto.responsedto.DiscountSettingsResponseDto;
import org.philosophy.carwashing.mapper.request.DiscountSettingsRequestMapper;
import org.philosophy.carwashing.mapper.response.DiscountSettingsResponseMapper;
import org.philosophy.carwashing.model.DiscountSettings;
import org.philosophy.carwashing.repository.DiscountSettingsRepository;
import org.philosophy.carwashing.validator.ParameterValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountSettingsServiceImpl implements GenericService<Integer,
        DiscountSettingsResponseDto, DiscountSettingsRequestDto> {

    private final DiscountSettingsRepository discountSettingsRepository;
    private final DiscountSettingsResponseMapper discountSettingsResponseMapper;
    private final DiscountSettingsRequestMapper discountSettingsRequestMapper;
    private final ParameterValidator<DiscountSettingsRequestDto> validator;

    public BoxResponseDto setMax(Integer id, Double newMaxValue) {
        return null;
    }

    public BoxResponseDto setMin(Integer id, Double newMinValue) {
        return null;
    }

    @Override
    @Transactional
    public DiscountSettingsResponseDto create(DiscountSettingsRequestDto dto) {
        validator.validateDtoNotNull(dto);
        DiscountSettings discountSettings = discountSettingsRequestMapper.toEntity(dto);
        DiscountSettings saved = discountSettingsRepository.save(discountSettings);
        return discountSettingsResponseMapper.toDto(saved);
    }

    public void deleteById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        discountSettingsRepository.deleteById(id);
    }

    @Override
    public DiscountSettingsResponseDto findById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        return discountSettingsRepository.findById(id)
                .map(discountSettingsResponseMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public Page<DiscountSettingsResponseDto> findAll(Pageable pageable) {
        return discountSettingsRepository.findAll(pageable)
                .map(discountSettingsResponseMapper::toDto);
    }

    @Override
    public DiscountSettingsResponseDto update(Integer integer, DiscountSettingsRequestDto dto) {
        return null;
    }

    public List<DiscountSettingsResponseDto> findAll(){
        List<DiscountSettings> discounts = discountSettingsRepository.findAll();
        return discounts.stream()
                .map(discountSettingsResponseMapper::toDto)
                .collect(Collectors.toList());

    }

}
