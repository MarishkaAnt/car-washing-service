package org.philosophy.carwashing.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.philosophy.carwashing.dto.requestdto.RequestRequestDto;
import org.philosophy.carwashing.dto.responsedto.RequestResponseDto;
import org.philosophy.carwashing.mapper.request.RequestRequestMapper;
import org.philosophy.carwashing.mapper.request.RequestRequestMapperImpl;
import org.philosophy.carwashing.mapper.response.RequestResponseMapper;
import org.philosophy.carwashing.mapper.response.RequestResponseMapperImpl;
import org.philosophy.carwashing.model.*;
import org.philosophy.carwashing.repository.BookingRepository;
import org.philosophy.carwashing.repository.BoxRepository;
import org.philosophy.carwashing.repository.RequestRepository;
import org.philosophy.carwashing.repository.WashTypeRepository;
import org.philosophy.carwashing.validator.ParameterValidator;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.philosophy.carwashing.service.utilits.CommonBuilders.*;

class RequestServiceImplTest {

    private static RequestServiceImpl requestService;
    private static RequestRepository requestRepository;
    private static BookingRepository bookingRepository;
    private static BoxRepository boxRepository;
    private static WashTypeRepository washTypeRepository;
    private static final RequestRequestMapper requestRequestMapper = new RequestRequestMapperImpl();
    private static final RequestResponseMapper requestResponseMapper = new RequestResponseMapperImpl();
    private static final ParameterValidator<RequestRequestDto> validator = new ParameterValidator<>();

    @BeforeAll
    static void init() {
        requestRepository = Mockito.mock(RequestRepository.class);
        bookingRepository = Mockito.mock(BookingRepository.class);
        boxRepository = Mockito.mock(BoxRepository.class);
        washTypeRepository = Mockito.mock(WashTypeRepository.class);
        requestService = new RequestServiceImpl(
                requestRepository,
                bookingRepository,
                boxRepository,
                washTypeRepository,
                requestRequestMapper,
                requestResponseMapper,
                validator
        );

    }

    @BeforeEach
    void setUp() {
        Booking correctBooking = getCorrectBooking();
        when(bookingRepository.findAllByDatetimeFromGreaterThanAndDatetimeToLessThanAndStatusNotInOrderByDatetimeFrom(any(), any(), any()))
                .thenReturn(List.of(correctBooking));
        when(boxRepository.findById(any())).thenReturn(Optional.ofNullable(getCorrectBoxSpeedStandard()));
        when(washTypeRepository.findById(any())).thenReturn(Optional.ofNullable(getCorrectWashTypeLight()));
        when(requestRepository.save(any())).thenReturn(getCorrectRequestAfterSaving());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreate_correctEntity_returnCorrectRequestResponseDto() {
        //given
        RequestRequestDto input = getCorrectInputDto();
        RequestResponseDto expected = getCorrectOutputDto();
        //when
        RequestResponseDto actual = requestService.create(input);
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }



}