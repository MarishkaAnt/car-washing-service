package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.RequestRequestDto;
import org.philosophy.carwashing.dto.responsedto.RequestResponseDto;
import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.mapper.request.RequestRequestMapper;
import org.philosophy.carwashing.mapper.response.RequestResponseMapper;
import org.philosophy.carwashing.model.*;
import org.philosophy.carwashing.repository.*;
import org.philosophy.carwashing.validator.ParameterValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements GenericService<Integer,
        RequestResponseDto, RequestRequestDto> {

    private final RequestRepository requestRepository;
    private final BookingRepository bookingRepository;
    private final BoxRepository boxRepository;
    private final UserRepository userRepository;
    private final WashTypeRepository washTypeRepository;
    private final RequestRequestMapper requestRequestMapper;
    private final RequestResponseMapper requestResponseMapper;
    private final ParameterValidator<RequestRequestDto> validator;

    @Override
    public RequestResponseDto create(RequestRequestDto dto) {
        validator.validateEntityNotNull(dto);
        validateRequestRequestDtoFields(dto);
        Request request = requestRequestMapper.toEntity(dto);
        Box box = boxRepository.findById(dto.getBoxId()).orElseThrow(
                () -> new EntityNotFoundException("Бокс не найден по id"));
        WashType washType = washTypeRepository.findById(dto.getWashTypeId()).orElseThrow(
                () -> new EntityNotFoundException("услуга не найдена по id"));
        System.out.println(washType);
        Offer offer = generateOffer(request);
        request.setBox(box);
        request.setWashType(washType);
        request.setResponseDatetimeFrom(offer.timeFrom);
        request.setResponseDatetimeTo(offer.timeTo);
        request.setDuration(offer.duration);
        Request saved = requestRepository.save(request);
        return requestResponseMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public RequestResponseDto findById(Integer integer) {
        return null;
    }

    @Override
    public Page<RequestResponseDto> findAll(Pageable pageable) {
        return null;
    }

    private Offer generateOffer(Request request){
        Offer offer = new Offer();
        LocalDateTime datetimeFrom = request.getDatetimeFrom();
        LocalDateTime datetimeTo = request.getDatetimeTo();
        Double speedcoefficient = request.getBox().getBoxType().getSpeedcoefficient();
        Duration washTypeDuration = request.getWashType().getDuration();
        washTypeDuration = Duration.of(
                (long) (washTypeDuration.toMillis() * speedcoefficient),
                ChronoUnit.MILLIS);
        List<BookingStatuses> statuses = List.of(BookingStatuses.CANCELLED, BookingStatuses.DELETED);
        List<Booking> bookings = bookingRepository.findAllByDatetimeFromGreaterThanAndDatetimeToLessThanAndStatusNotIn(
                datetimeFrom, datetimeTo, statuses
        );
        LocalDateTime start = datetimeFrom;
        for (Booking b: bookings) {
            Duration checkedDuration = Duration.between(b.getDatetimeFrom(), start);
            if(checkedDuration.compareTo(washTypeDuration) >= 0){
                offer.setDuration(washTypeDuration);
                offer.setTimeFrom(start);
                offer.setTimeTo(start.plus(washTypeDuration));
                break;
            } else {
                start = b.getDatetimeTo();
            }
        }
        return offer;
    }

    private void validateRequestRequestDtoFields(RequestRequestDto dto){
        if(
             dto.getDatetimeFrom() == null ||
             dto.getDatetimeTo() == null ||
             dto.getUserId() == null ||
             dto.getWashTypeId() == null
        ) {
            throw new IllegalArgumentException("null fields in request dto");
        }
    }
}
