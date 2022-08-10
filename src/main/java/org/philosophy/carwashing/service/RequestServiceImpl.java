package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.RequestRequestDto;
import org.philosophy.carwashing.dto.responsedto.RequestResponseDto;
import org.philosophy.carwashing.mapper.request.RequestRequestMapper;
import org.philosophy.carwashing.mapper.response.RequestResponseMapper;
import org.philosophy.carwashing.model.*;
import org.philosophy.carwashing.repository.*;
import org.philosophy.carwashing.validator.ParameterValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;

import static org.philosophy.carwashing.util.CommonStringConstants.*;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements GenericService<Integer,
        RequestResponseDto, RequestRequestDto> {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final WashTypeRepository washTypeRepository;
    private final RequestRequestMapper requestRequestMapper;
    private final RequestResponseMapper requestResponseMapper;
    private final ParameterValidator<RequestRequestDto> validator;

    @Override
    public RequestResponseDto create(RequestRequestDto dto) {
        validator.validateDtoNotNull(dto);
        validateRequestRequestDtoFields(dto);
        validateTimeFromBeforeTimeTo(dto);
        Request request = requestRequestMapper.toEntity(dto);
        WashType washType = washTypeRepository.findById(dto.getWashTypeId())
                .orElseThrow(() -> new EntityNotFoundException(WASH_TYPE_NOT_FOUND_MESSAGE));
        request.setWashType(washType);
        userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_MESSAGE));
        Request saved = requestRepository.save(request);
        return requestResponseMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        requestRepository.deleteById(id);
    }

    @Override
    public RequestResponseDto findById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        return requestRepository.findById(id)
                .map(requestResponseMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<RequestResponseDto> findAll(Pageable pageable) {
        return requestRepository.findAll(pageable)
                .map(requestResponseMapper::toDto);
    }

    @Override
    public RequestResponseDto update(Integer id, RequestRequestDto dto) {
        validator.validateDtoNotNull(dto);
        validateRequestRequestDtoFields(dto);
        validateTimeFromBeforeTimeTo(dto);
        Request request = requestRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        request.setDatetimeFrom(dto.getDatetimeFrom());
        request.setDatetimeTo(dto.getDatetimeTo());
        WashType washType = washTypeRepository.findById(dto.getWashTypeId())
                .orElseThrow(() -> new EntityNotFoundException(WASH_TYPE_NOT_FOUND_MESSAGE));
        request.setWashType(washType);
        userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_MESSAGE));
        Request saved = requestRepository.save(request);
        return requestResponseMapper.toDto(saved);
    }

    private void validateRequestRequestDtoFields(RequestRequestDto dto) {
        if (
                dto.getDatetimeFrom() == null ||
                        dto.getDatetimeTo() == null ||
                        dto.getUserId() == null ||
                        dto.getWashTypeId() == null
        ) {
            throw new IllegalArgumentException(NULL_FIELDS_IN_REQUEST_DTO);
        }
    }

    private void validateTimeFromBeforeTimeTo(RequestRequestDto dto) {

        LocalDateTime datetimeFrom = dto.getDatetimeFrom();
        LocalDateTime datetimeTo = dto.getDatetimeTo();
        if (datetimeTo.isBefore(datetimeFrom)) {
            throw new IllegalArgumentException(WRONG_DATE_SEQUENCE);
        }

    }

}
