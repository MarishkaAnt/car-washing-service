package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.BoxTypeRequestDto;
import org.philosophy.carwashing.dto.responsedto.BoxTypeResponseDto;
import org.philosophy.carwashing.repository.BoxTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoxTypeServiceImpl implements GenericService<Integer, BoxTypeResponseDto, BoxTypeRequestDto> {

    private final BoxTypeRepository boxTypeRepository;

    @Override
    public BoxTypeResponseDto create(BoxTypeRequestDto dto) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public BoxTypeResponseDto findById(Integer id) {
        return null;
    }

    @Override
    public Page<BoxTypeResponseDto> findAll(Pageable pageable) {
        return null;
    }
}
