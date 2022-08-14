package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.dto.requestdto.BookingRequestDto;
import org.philosophy.carwashing.dto.responsedto.BookingResponseDto;
import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.mapper.request.BookingRequestMapper;
import org.philosophy.carwashing.mapper.response.BookingResponseMapper;
import org.philosophy.carwashing.model.*;
import org.philosophy.carwashing.repository.BookingRepository;
import org.philosophy.carwashing.repository.UserRepository;
import org.philosophy.carwashing.util.Discounter;
import org.philosophy.carwashing.util.Offer;
import org.philosophy.carwashing.validator.ParameterValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.math.BigDecimal;

import static org.philosophy.carwashing.util.CommonStringConstants.BOOKING_NOT_FOUND_MESSAGE;

/**
 * Сервис для работы с бронью
 */
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements GenericService<Integer, BookingResponseDto, BookingRequestDto> {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final OfferService offerService;
    private final BookingRequestMapper bookingRequestMapper;
    private final BookingResponseMapper bookingResponseMapper;
    private final ParameterValidator<BookingRequestDto> validator;

    /**
     * Автоматически создает запись основываясь на параметрах входящей Dto
     * @param dto - запрос на создание брони
     * @return - зозданную автоматически и сохраненную в БД бронь
     */
    @Override
    @Transactional
    public BookingResponseDto create(BookingRequestDto dto) {
        validator.validateDtoNotNull(dto);
        Booking booking = bookingRequestMapper.toEntity(dto);
        Integer requestedBoxId = dto.getBoxId();

        Offer foundedOffer = offerService.generateOffer(booking, requestedBoxId);
        booking.setStatus(BookingStatuses.NEW);
        booking.setBox(foundedOffer.getBox());
        booking.setDatetimeFrom(foundedOffer.getTimeFrom());
        booking.setDatetimeTo(foundedOffer.getTimeTo());

        BigDecimal totalCost = getTotalCost(booking);
        booking.setTotalCost(totalCost);
        booking.setIsPaid(false);
        Booking saved = bookingRepository.save(booking);
        return bookingResponseMapper.toDto(saved);
    }

    /**
     * Выполняет софт-делит со сменой статуса на DELETED
     *
     * @param id - идентификатор брони
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        validator.validateIdIsNullOrNegative(id);
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        booking.setStatus(BookingStatuses.DELETED);
        bookingRepository.save(booking);
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

    /**
     * Полное редактирование брони по запросу, заменяет все не null поля из входящего DTO
     * Если пользователь хочет изменить время брони, тип услуги или тип бокса,
     * то текущая бронь помечается как CANCELLED, и создается новая бронь.
     *
     * @param id  - идентификатор редактируемой брони
     * @param dto - DTO содержащая поля для замены в найденной по id брони
     * @return - возвращает измененную бронь
     */
    @Override
    public BookingResponseDto update(Integer id, BookingRequestDto dto) {
        validator.validateIdIsNullOrNegative(id);
        Booking founded = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BOOKING_NOT_FOUND_MESSAGE));
        founded.setStatus(BookingStatuses.CANCELLED);
        bookingRepository.save(founded);
        if (dto.getDatetimeFrom() == null && dto.getDatetimeTo() == null) {
            dto.setDatetimeFrom(founded.getDatetimeFrom());
            dto.setDatetimeTo(founded.getDatetimeTo());
        }
        if (dto.getWashTypeId() != null) {
            founded.setWashType(WashType.builder().id(dto.getWashTypeId()).build());
        }
        if (dto.getBoxId() != null) {
            founded.setBox(Box.builder().id(dto.getBoxId()).build());
        }
        return create(dto);
    }

    public BookingResponseDto changeStatus(Integer id, BookingStatuses status) {
        validator.validateIdIsNullOrNegative(id);
        Booking founded = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BOOKING_NOT_FOUND_MESSAGE));
        founded.setStatus(status);
        Booking saved = bookingRepository.save(founded);
        return bookingResponseMapper.toDto(saved);
    }

    private BigDecimal getTotalCost(Booking booking) {
        BigDecimal totalCost;
        BigDecimal washTypeCost = booking.getWashType().getCost();
        Integer washTypeDiscountAmount = booking.getWashType().getDiscountAmount();
        Integer boxDiscountAmount = booking.getBox().getDiscountAmount();
        Integer userDiscountAmount = userRepository.findById(booking.getUser().getId())
                .map(Discountable::getDiscountAmount).orElse(0);
        totalCost = Discounter.applyDiscount(washTypeCost, washTypeDiscountAmount);
        totalCost = Discounter.applyDiscount(totalCost, boxDiscountAmount);
        totalCost = Discounter.applyDiscount(totalCost, userDiscountAmount);
        return totalCost;
    }

    /*
    ToDo добавить расчет выручки за период, переименовать метод ниже
     */
    public BigDecimal getAccounting() {
        return bookingRepository.getMoneyAmount();
    }

}



