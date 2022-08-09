package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.BookingRequestDto;
import org.philosophy.carwashing.dto.responsedto.BookingResponseDto;
import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.mapper.request.BookingRequestMapper;
import org.philosophy.carwashing.mapper.response.BookingResponseMapper;
import org.philosophy.carwashing.model.Booking;
import org.philosophy.carwashing.model.Request;
import org.philosophy.carwashing.repository.BookingRepository;
import org.philosophy.carwashing.repository.RequestRepository;
import org.philosophy.carwashing.util.Offer;
import org.philosophy.carwashing.validator.ParameterValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.philosophy.carwashing.util.CommonStringConstants.REQUEST_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements GenericService<Integer, BookingResponseDto, BookingRequestDto> {

    private final BookingRepository bookingRepository;
    private final RequestRepository requestRepository;
    private final BookingRequestMapper bookingRequestMapper;
    private final BookingResponseMapper bookingResponseMapper;
    private final ParameterValidator<BookingRequestDto> validator;

    @Override
    @Transactional
    public BookingResponseDto create(BookingRequestDto dto) {
        validator.validateDtoNotNull(dto);
        Booking booking = bookingRequestMapper.toEntity(dto);
        Request request = requestRepository.findById(booking.getRequest().getId())
                .orElseThrow(() -> new EntityNotFoundException(REQUEST_NOT_FOUND_MESSAGE));
        booking.setRequest(request);
        booking.setUserId(request.getUserId());

        Offer offer = generateOffer(request);
        if(offer.getDuration() == null || offer.getTimeFrom() == null || offer.getTimeTo() == null){
            throw new EntityNotFoundException("По вашему запросу ничего не найдено, измените параметры поиска");
        }


        Double totalCost = getTotalCost(request);
        booking.setTotalCost(totalCost);
        booking.setStatus(BookingStatuses.NEW);
        Booking saved = bookingRepository.save(booking);
        return bookingResponseMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if(booking.getRequest() != null) {
            throw new IllegalArgumentException("Удаление невозможно, сущность связана с другими сущностями ");
        }
        bookingRepository.deleteById(id);
    }

    @Override
    public BookingResponseDto findById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        return bookingRepository.findById(id)
                .map(bookingResponseMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<BookingResponseDto> findAll(Pageable pageable) {
        return bookingRepository.findAll(pageable)
                .map(bookingResponseMapper::toDto);
    }

    @Override
    public BookingResponseDto update(Integer id, BookingRequestDto dto) {
        validator.validateIdIsNullOrNegative(id);
        validator.validateDtoNotNull(dto);

        return null;
    }

    private Double getTotalCost(Request request) {
        Double totalCost;
        if (request.getWashType().getDiscountAmount() >= 1.0) {
            totalCost = request.getWashType().getCost() / 100 * request.getWashType().getDiscountAmount();
        } else {
            totalCost = request.getWashType().getCost();
        }
        return totalCost;
    }

    public Offer generateOffer(Request request){
        Offer offer = new Offer();
        LocalDateTime datetimeFrom = request.getDatetimeFrom();
        LocalDateTime datetimeTo = request.getDatetimeTo();
        Duration washTypeDuration = request.getWashType().getDuration();
/*
        washTypeDuration = Duration.of(
                (long) (washTypeDuration.toMillis() * speedCoefficient),
                ChronoUnit.MILLIS);
*/
        List<BookingStatuses> statuses = List.of(BookingStatuses.CANCELLED, BookingStatuses.DELETED);
        List<Booking> bookings = bookingRepository.findAllByDatetimeFromGreaterThanAndDatetimeToLessThanAndStatusNotInOrderByDatetimeFrom(
                datetimeFrom, datetimeTo, statuses
        );
        LocalDateTime start = datetimeFrom;
        LocalDateTime end = datetimeTo;
        for (int i = 0; i < bookings.size(); i++) {
            Booking b = bookings.get(i);
            Duration checkedDurationBeforeNextBooking = Duration.between(start, b.getDatetimeFrom());
            if (checkedDurationBeforeNextBooking.compareTo(washTypeDuration) >= 0) {
                offer.setDuration(washTypeDuration);
                offer.setTimeFrom(start);
                offer.setTimeTo(start.plus(washTypeDuration));
                return offer;
            }
            start = b.getDatetimeTo();
        }

        Duration workingDuration = Duration.between(start, end);
        if (workingDuration.compareTo(washTypeDuration) >= 0) {
            offer.setDuration(washTypeDuration);
            offer.setTimeFrom(start);
            offer.setTimeTo(start.plus(washTypeDuration));
        }

        return offer;
    }

    private void validateDtoFields(BookingRequestDto dto){

    }

}



