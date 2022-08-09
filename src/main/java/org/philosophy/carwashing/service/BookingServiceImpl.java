package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.BookingRequestDto;
import org.philosophy.carwashing.dto.responsedto.BookingResponseDto;
import org.philosophy.carwashing.mapper.request.BookingRequestMapper;
import org.philosophy.carwashing.mapper.response.BookingResponseMapper;
import org.philosophy.carwashing.model.Booking;
import org.philosophy.carwashing.model.Request;
import org.philosophy.carwashing.repository.BookingRepository;
import org.philosophy.carwashing.repository.RequestRepository;
import org.philosophy.carwashing.validator.ParameterValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements GenericService<Integer, BookingResponseDto, BookingRequestDto> {

    private final BookingRepository bookingRepository;
    private final RequestRepository requestRepository;
    private final BookingRequestMapper bookingRequestMapper;
    private final BookingResponseMapper bookingResponseMapper;
    private final ParameterValidator<BookingRequestDto> validator;

    public BookingResponseDto create(BookingRequestDto dto) {
        validator.validateDtoNotNull(dto);
        Booking booking = bookingRequestMapper.toEntity(dto);
        Request request = requestRepository.findById(booking.getRequest().getId())
                .orElseThrow(() -> new EntityNotFoundException("Бронь с таким Id не найден"));
        booking.setRequest(request);
        booking.setUserId(request.getUserId());

        Double totalCost = getTotalCost(request);
        booking.setTotalCost(totalCost);

        Booking saved = bookingRepository.save(booking);
        return bookingResponseMapper.toDto(saved);

    }


    public void deleteById(Integer integer) {

    }

    public BookingResponseDto findById(Integer integer) {
        return null;
    }

    public Page<BookingResponseDto> findAll(Pageable pageable) {
        return null;
    }

    private Double getTotalCost(Request request) {
        Double totalCost;
        if(request.getWashType().getDiscountAmount() >= 1.0){
            totalCost = request.getWashType().getCost() / 100 * request.getWashType().getDiscountAmount();
        } else {
            totalCost = request.getWashType().getCost();
        }
        return totalCost;
    }

}

