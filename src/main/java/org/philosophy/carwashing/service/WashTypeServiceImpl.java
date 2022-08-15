package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.WashTypeRequestDto;
import org.philosophy.carwashing.dto.responsedto.WashTypeResponseDto;
import org.philosophy.carwashing.enums.WashTypes;
import org.philosophy.carwashing.mapper.request.WashTypeRequestMapper;
import org.philosophy.carwashing.mapper.response.WashTypeResponseMapper;
import org.philosophy.carwashing.model.WashType;
import org.philosophy.carwashing.repository.WashTypeRepository;
import org.philosophy.carwashing.validator.ParameterValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static org.philosophy.carwashing.util.CommonStringConstants.WASH_TYPE_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class WashTypeServiceImpl implements GenericService<Integer,
        WashTypeResponseDto, WashTypeRequestDto> {

    private final WashTypeRepository washTypeRepository;
    private final WashTypeRequestMapper washTypeRequestMapper;
    private final WashTypeResponseMapper washTypeResponseMapper;
    private final ParameterValidator<WashTypeRequestDto> validator;

    @Override
    public WashTypeResponseDto create(WashTypeRequestDto dto) {
        validator.validateDtoNotNull(dto);
        WashType washType = washTypeRequestMapper.toEntity(dto);
        WashType saved = washTypeRepository.save(washType);
        return washTypeResponseMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        washTypeRepository.deleteById(id);
    }

    @Override
    public WashTypeResponseDto findById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        return washTypeRepository.findById(id)
                .map(washTypeResponseMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(WASH_TYPE_NOT_FOUND_MESSAGE));
    }

    @Override
    public Page<WashTypeResponseDto> findAll(Pageable pageable) {
        return washTypeRepository.findAll(pageable)
                .map(washTypeResponseMapper::toDto);
    }

    @Override
    public WashTypeResponseDto update(Integer id, WashTypeRequestDto dto) {
        WashType washType = washTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(WASH_TYPE_NOT_FOUND_MESSAGE));
        washType.setName(WashTypes.valueOf(dto.getName()));
        washType.setCost(dto.getCost());
        washType.setDuration(dto.getDuration());

        WashType saved = washTypeRepository.save(washType);
        return washTypeResponseMapper.toDto(saved);
    }
}
