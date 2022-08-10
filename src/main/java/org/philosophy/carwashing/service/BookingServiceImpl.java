package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.BookingRequestDto;
import org.philosophy.carwashing.dto.responsedto.BookingResponseDto;
import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.mapper.request.BookingRequestMapper;
import org.philosophy.carwashing.mapper.response.BookingResponseMapper;
import org.philosophy.carwashing.model.Booking;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.model.Request;
import org.philosophy.carwashing.repository.BookingRepository;
import org.philosophy.carwashing.repository.BoxRepository;
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
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.philosophy.carwashing.util.CommonStringConstants.*;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements GenericService<Integer, BookingResponseDto, BookingRequestDto> {

    private final BookingRepository bookingRepository;
    private final RequestRepository requestRepository;
    private final BoxRepository boxRepository;
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
        Integer requestedBoxId = dto.getBoxId();
        Offer foundedOffer = new Offer();
        if (requestedBoxId != null) {
            validator.validateIdIsNullOrNegative(requestedBoxId);
            Box box = boxRepository.findById(requestedBoxId)
                    .orElseThrow(EntityNotFoundException::new);
            foundedOffer = generateOffer(request, box);
        } else {
            List<Box> boxes = boxRepository.findAllOrderById();
            List<Offer> offersByBox = new ArrayList<>();
            for (Box box : boxes) {
                offersByBox.add(generateOffer(request, box));
            }
            foundedOffer = offersByBox.stream()
                    .filter(offer -> offer.getDuration() != null && offer.getTimeFrom() != null &&
                            offer.getTimeTo() != null && offer.getBox() != null)
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(NOTING_FOUND));
        }

        Double totalCost = getTotalCost(request);
        booking.setTotalCost(totalCost);
        booking.setStatus(BookingStatuses.NEW);
        booking.setBox(foundedOffer.getBox());
        booking.setDatetimeFrom(foundedOffer.getTimeFrom());
        booking.setDatetimeTo(foundedOffer.getTimeTo());
        booking.setDuration(foundedOffer.getDuration());
        booking.setIsPaid(false);
        Booking saved = bookingRepository.save(booking);
        return bookingResponseMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (booking.getRequest() != null) {
            throw new IllegalArgumentException(DELETING_NOT_ALOWED);
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

    /*
    ToDo
     */
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

    public Offer generateOffer(Request request, Box box) {
        Offer offer = new Offer();
        LocalDateTime datetimeFrom = request.getDatetimeFrom();
        LocalDateTime datetimeTo = request.getDatetimeTo();
        Duration washTypeDuration = request.getWashType().getDuration();
        Double speedCoefficient = box.getBoxType().getSpeedcoefficient();
        LocalTime openTime = box.getOpenTime();
        LocalTime closeTime = box.getCloseTime();
        if(openTime.isAfter(datetimeFrom.toLocalTime())){
            datetimeFrom = LocalDateTime.of(datetimeFrom.toLocalDate(), openTime);
        }
        if(closeTime.isBefore(datetimeTo.toLocalTime())){
            datetimeTo = LocalDateTime.of(datetimeTo.toLocalDate(), closeTime);
        }
        washTypeDuration = Duration.of(
                (long) (washTypeDuration.toMillis() * speedCoefficient),
                ChronoUnit.MILLIS);
        List<BookingStatuses> statuses = List.of(BookingStatuses.CANCELLED, BookingStatuses.DELETED);
        List<Booking> bookings = bookingRepository
                .findAllByRequestAndBox(
                datetimeFrom, datetimeTo, statuses, box.getId());
        LocalDateTime start = datetimeFrom;
        LocalDateTime end = datetimeTo;
        for (Booking b : bookings) {
            Duration checkedDurationBeforeNextBooking = Duration.between(start, b.getDatetimeFrom());
            if (checkedDurationBeforeNextBooking.compareTo(washTypeDuration) >= 0) {
                offer.setDuration(washTypeDuration);
                offer.setTimeFrom(start);
                offer.setTimeTo(start.plus(washTypeDuration));
                offer.setBox(box);
                return offer;
            }
            start = b.getDatetimeTo();
        }

        Duration workingDuration = Duration.between(start, end);
        if (workingDuration.compareTo(washTypeDuration) >= 0) {
            offer.setDuration(washTypeDuration);
            offer.setTimeFrom(start);
            offer.setTimeTo(start.plus(washTypeDuration));
            offer.setBox(box);
        }

        return offer;
    }

}



