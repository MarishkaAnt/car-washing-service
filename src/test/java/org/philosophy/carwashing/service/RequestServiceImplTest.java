package org.philosophy.carwashing.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.philosophy.carwashing.dto.requestdto.RequestRequestDto;
import org.philosophy.carwashing.dto.responsedto.RequestResponseDto;
import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.enums.BoxTypes;
import org.philosophy.carwashing.enums.WashTypes;
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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class RequestServiceImplTest {

    public static final int CORRECT_ID_EQUALS_1 = 1;
    public static final double SPEEDCOEFFICIENT_EQUAL_1 = 1.0;
    public static final LocalTime CORRECT_OPEN_TIME_8_00 = LocalTime.parse("08:00");
    public static final LocalDateTime CORRECT_REQUEST_START_DATE_TIME = LocalDateTime.parse("2022-08-09T09:00");
    public static final LocalTime CORRECT_CLOSE_TIME_21_00 = LocalTime.parse("21:00");
    public static final LocalDateTime CORRECT_REQUEST_END_DATE_TIME = LocalDateTime.parse("2022-08-09T21:00");
    public static final int SAVED_ENTITY_ID_EQUAL_3 = 3;
    public static final Duration CORRECT_DURATION_PT_15_M = Duration.parse("PT15M");
    public static final double CORRECT_COST_300 = 300.0;
    public static final LocalDateTime DATETIME_FROM_10 = LocalDateTime.parse("2022-08-09T10:00:00");
    public static final LocalDateTime DATE_TIME_TO_10_15 = LocalDateTime.parse("2022-08-09T10:15:00");
    public static final Duration PT_15_M = Duration.parse("PT15M");
    public static final LocalDateTime RESPONSE_DATETIME_FROM = LocalDateTime.parse("2022-08-09T09:00:00");
    public static final LocalDateTime RESPONSE_DATETIME_TO = LocalDateTime.parse("2022-08-09T09:15:00");
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
/*
        requestRequestMapper = Mockito.mock(RequestRequestMapper.class);
        requestResponseMapper = Mockito.mock(RequestResponseMapper.class);
*/
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
        Request correctRequestEntityAfterMapping = getCorrectRequestAfterMapping();
        //doNothing().when(validator).validateEntityNotNull(any());
        //when(requestRequestMapper.toEntity(any())).thenCallRealMethod();
        when(bookingRepository.findAllByDatetimeFromGreaterThanAndDatetimeToLessThanAndStatusNotInOrderByDatetimeFrom(any(), any(), any()))
                .thenReturn(List.of(correctBooking));
        when(boxRepository.findById(any())).thenReturn(Optional.ofNullable(getCorrectBoxSpeedStandard()));
        when(washTypeRepository.findById(any())).thenReturn(Optional.ofNullable(getCorrectWashTypeLight()));
        when(requestRepository.save(any())).thenReturn(getCorrectRequestAfterSaving());
        //when(requestResponseMapper.toDto(any())).thenCallRealMethod();
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


/*
    @Test
    void deleteById() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }
*/
    private RequestRequestDto getCorrectInputDto(){
        RequestRequestDto dto = new RequestRequestDto();
        dto.setBoxId(CORRECT_ID_EQUALS_1);
        dto.setWashTypeId(CORRECT_ID_EQUALS_1);
        dto.setUserId(CORRECT_ID_EQUALS_1);
        dto.setDatetimeFrom(LocalDateTime.parse("2022-08-09T09:00:00"));
        dto.setDatetimeTo(LocalDateTime.parse("2022-08-09T18:00:00"));
        return dto;
    }

    private RequestResponseDto getCorrectOutputDto(){
        RequestResponseDto dto = new RequestResponseDto();
        dto.setId(CORRECT_ID_EQUALS_1);
        dto.setBoxId(CORRECT_ID_EQUALS_1);
        dto.setWashTypeId(CORRECT_ID_EQUALS_1);
        dto.setUserId(CORRECT_ID_EQUALS_1);
        dto.setResponseDatetimeFrom(LocalDateTime.parse("2022-08-09T09:00:00"));
        dto.setResponseDatetimeTo(LocalDateTime.parse("2022-08-09T09:15:00"));
        return dto;
    }


    private Request getCorrectRequestAfterMapping() {
        return Request.builder()
                .box(Box.builder().id(CORRECT_ID_EQUALS_1).build())
                .washType(WashType.builder().id(CORRECT_ID_EQUALS_1).build())
                .userId(CORRECT_ID_EQUALS_1)
                .datetimeFrom(CORRECT_REQUEST_START_DATE_TIME)
                .datetimeTo(CORRECT_REQUEST_END_DATE_TIME)
                .build();
    }

    private Box getCorrectBoxSpeedStandard() {
        BoxType correctBoxType = BoxType.builder()
                .id(CORRECT_ID_EQUALS_1)
                .type(BoxTypes.STANDARD)
                .speedcoefficient(SPEEDCOEFFICIENT_EQUAL_1)
                .build();

        return Box.builder()
                .id(CORRECT_ID_EQUALS_1)
                .boxType(correctBoxType)
                .openTime(CORRECT_OPEN_TIME_8_00)
                .closeTime(CORRECT_CLOSE_TIME_21_00)
                .build();
    }

    private Request getCorrectRequestAfterSaving(){
        return Request.builder()
                .id(CORRECT_ID_EQUALS_1)
                .box(getCorrectBoxSpeedStandard())
                .washType(getCorrectWashTypeLight())
                .userId(CORRECT_ID_EQUALS_1)
                .datetimeFrom(CORRECT_REQUEST_START_DATE_TIME)
                .datetimeTo(CORRECT_REQUEST_END_DATE_TIME)
                .responseDatetimeFrom(RESPONSE_DATETIME_FROM)
                .responseDatetimeTo(RESPONSE_DATETIME_TO)
                .build();
    }

    private RequestResponseDto getRequestResponseDto(){
        RequestResponseDto expected = new RequestResponseDto();
        expected.setId(SAVED_ENTITY_ID_EQUAL_3);
        expected.setBoxId(CORRECT_ID_EQUALS_1);
        expected.setUserId(CORRECT_ID_EQUALS_1);
        expected.setWashTypeId(CORRECT_ID_EQUALS_1);
        expected.setResponseDatetimeFrom(RESPONSE_DATETIME_FROM);
        expected.setResponseDatetimeTo(RESPONSE_DATETIME_TO);
        expected.setDuration(PT_15_M);
        return expected;
    }

    private WashType getCorrectWashTypeLight() {
        return WashType.builder()
                .id(CORRECT_ID_EQUALS_1)
                .name(WashTypes.LIGHT)
                .duration(CORRECT_DURATION_PT_15_M)
                .cost(CORRECT_COST_300)
                .build();
    }
    private Booking getCorrectBooking(){
        return Booking.builder()
                .id(CORRECT_ID_EQUALS_1)
                .request(getCorrectRequestAfterSaving())
                .userId(CORRECT_ID_EQUALS_1)
                .totalCost(CORRECT_COST_300)
                .datetimeFrom(DATETIME_FROM_10)
                .datetimeFrom(DATE_TIME_TO_10_15)
                .duration(PT_15_M)
                .status(BookingStatuses.NEW)
                .isPaid(false)
                .build();
    }

}