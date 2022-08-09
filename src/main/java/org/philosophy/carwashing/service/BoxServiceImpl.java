package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.philosophy.carwashing.dto.requestdto.BoxRequestDto;
import org.philosophy.carwashing.dto.responsedto.BoxResponseDto;
import org.philosophy.carwashing.mapper.request.BoxRequestMapper;
import org.philosophy.carwashing.mapper.response.BoxResponseMapper;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.model.BoxType;
import org.philosophy.carwashing.repository.BoxRepository;
import org.philosophy.carwashing.repository.BoxTypeRepository;
import org.philosophy.carwashing.validator.ParameterValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.philosophy.carwashing.util.CommonStringConstants.BOX_TYPE_NOT_FOUND_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements GenericService<Integer, BoxResponseDto, BoxRequestDto> {

    private final BoxRepository boxRepository;
    private final BoxTypeRepository boxTypeRepository;
    private final BoxRequestMapper boxRequestMapper;
    private final BoxResponseMapper boxResponseMapper;

    private final ParameterValidator<BoxRequestDto> parameterValidator;

    @Override
    @Transactional
    public BoxResponseDto create(BoxRequestDto dto) {
        parameterValidator.validateDtoNotNull(dto);
        Box box = boxRequestMapper.toEntity(dto);
        BoxType boxType = boxTypeRepository.findById(box.getBoxType().getId())
                .orElseThrow(() -> new EntityNotFoundException(BOX_TYPE_NOT_FOUND_MESSAGE));
        box.setBoxType(boxType);
        box.setHasDiscount(false);
        box.setDiscountAmount(0.0);
        Box saved = boxRepository.save(box);
        return boxResponseMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        parameterValidator.validateIdIsNullOrNegative(id);
        boxRepository.deleteById(id);
    }

    @Override
    public BoxResponseDto findById(Integer id) {

        return boxRepository.findById(id)
                .map(boxResponseMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<BoxResponseDto> findAll(Pageable pageable) {
        return boxRepository.findAll(pageable)
                .map(boxResponseMapper::toDto);
    }

    @Override
    public BoxResponseDto update(Integer integer, BoxRequestDto dto) {
        return null;
    }

    public Page<BoxResponseDto> findAll(Specification<Box> specification, Pageable pageable){
        return boxRepository.findAll(specification, pageable)
                .map(boxResponseMapper::toDto);
    }

}
