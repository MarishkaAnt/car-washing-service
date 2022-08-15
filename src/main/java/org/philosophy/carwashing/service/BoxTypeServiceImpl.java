package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.BoxTypeRequestDto;
import org.philosophy.carwashing.dto.responsedto.BoxTypeResponseDto;
import org.philosophy.carwashing.enums.BoxTypes;
import org.philosophy.carwashing.mapper.request.BoxTypeRequestMapper;
import org.philosophy.carwashing.mapper.response.BoxTypeResponseMapper;
import org.philosophy.carwashing.model.BoxType;
import org.philosophy.carwashing.repository.BoxTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static org.philosophy.carwashing.util.CommonStringConstants.BOX_TYPE_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class BoxTypeServiceImpl implements GenericService<Integer, BoxTypeResponseDto, BoxTypeRequestDto> {

    private final BoxTypeRepository boxTypeRepository;
    private final BoxTypeResponseMapper responseMapper;
    private final BoxTypeRequestMapper requestMapper;

    @Override
    public BoxTypeResponseDto create(BoxTypeRequestDto dto) {
        BoxType boxType = requestMapper.toEntity(dto);
        BoxType saved = boxTypeRepository.save(boxType);
        return responseMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        boxTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BOX_TYPE_NOT_FOUND_MESSAGE));
        boxTypeRepository.deleteById(id);
    }

    @Override
    public BoxTypeResponseDto findById(Integer id) {
        BoxType boxType = boxTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BOX_TYPE_NOT_FOUND_MESSAGE));
        return responseMapper.toDto(boxType);
    }

    @Override
    public Page<BoxTypeResponseDto> findAll(Pageable pageable) {
        return boxTypeRepository.findAll(pageable)
                .map(responseMapper::toDto);
    }

    @Override
    public BoxTypeResponseDto update(Integer id, BoxTypeRequestDto dto) {
        BoxType boxType = boxTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BOX_TYPE_NOT_FOUND_MESSAGE));
        boxType.setType(BoxTypes.valueOf(dto.getType()));
        boxType.setSpeedCoefficient(dto.getSpeedcoefficient());
        BoxType saved = boxTypeRepository.save(boxType);
        return responseMapper.toDto(saved);
    }

}
