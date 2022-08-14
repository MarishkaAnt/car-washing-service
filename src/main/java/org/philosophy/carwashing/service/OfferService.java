package org.philosophy.carwashing.service;

import lombok.RequiredArgsConstructor;
import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.model.Booking;
import org.philosophy.carwashing.model.Box;
import org.philosophy.carwashing.model.BoxType;
import org.philosophy.carwashing.model.WashType;
import org.philosophy.carwashing.repository.BookingRepository;
import org.philosophy.carwashing.repository.BoxRepository;
import org.philosophy.carwashing.repository.BoxTypeRepository;
import org.philosophy.carwashing.repository.WashTypeRepository;
import org.philosophy.carwashing.util.Offer;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.philosophy.carwashing.util.CommonStringConstants.*;

/**
 * сервис для автоматического создания бронирования
 */
@Service
@RequiredArgsConstructor
public class OfferService {

    private final BookingRepository bookingRepository;
    private final BoxRepository boxRepository;
    private final WashTypeRepository washTypeRepository;
    private final BoxTypeRepository boxTypeRepository;

    /**
     * Генерирует предложение для создания брони на основе запрашиваемого пользователем
     * промежутка времени и (опционально) номера бокса. Если бокс указан - предлагает ближайшую запись
     * в указанный бокс, если нет - ищет ближайшее свободное время в любом боксе.
     * Метод учитывает время работы боксов и уже имеющиеся в нем брони
     */
    public Booking applyBestOfferByParameters(@NonNull Booking booking, Integer boxId) {
        Box box = new Box();
        Offer foundedOffer;

        if (boxId != null && boxId > 0) {
            box = boxRepository.findById(boxId)
                    .orElseThrow(() -> new EntityNotFoundException(BOX_NOT_FOUND_MESSAGE));
        }
        if (box != null && box.getId() !=null) {
            foundedOffer = generateOfferByBox(booking, box);
        } else {
            List<Box> boxes = boxRepository.findAllOrderById();
            List<Offer> offersByBox = new ArrayList<>();
            for (Box b : boxes) {
                offersByBox.add(generateOfferByBox(booking, b));
            }
            foundedOffer = offersByBox.stream()
                    .filter(offer -> offer.getDuration() != null && offer.getTimeFrom() != null &&
                            offer.getTimeTo() != null && offer.getBox() != null)
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(NOTING_FOUND));
        }
        booking.setBox(foundedOffer.getBox());
        booking.setDatetimeFrom(foundedOffer.getTimeFrom());
        booking.setDatetimeTo(foundedOffer.getTimeTo());
        return booking;
    }

    private Offer generateOfferByBox(@NonNull Booking booking, @NonNull Box box) {
        Offer offer = new Offer();
        LocalTime openTime = box.getOpenTime();
        LocalTime closeTime = box.getCloseTime();
        WashType washType = washTypeRepository.findById(booking.getWashType().getId())
                .orElseThrow(() -> new EntityNotFoundException(WASH_TYPE_NOT_FOUND_MESSAGE));
        booking.setWashType(washType);
        BoxType boxType = boxTypeRepository.findById(box.getBoxType().getId())
                .orElseThrow(() -> new EntityNotFoundException(BOX_TYPE_NOT_FOUND_MESSAGE));
        Duration requestedWashTypeDuration = washType.getDuration();
        Double speedCoefficient = boxType.getSpeedCoefficient();

        LocalDateTime requestedDatetimeFrom = booking.getDatetimeFrom();
        if (openTime.isAfter(requestedDatetimeFrom.toLocalTime())) {
            requestedDatetimeFrom = LocalDateTime.of(requestedDatetimeFrom.toLocalDate(), openTime);
        }
        LocalDateTime requestedDatetimeTo = booking.getDatetimeTo() == null ? null :
                booking.getDatetimeTo();
        if (requestedDatetimeTo == null || closeTime.isBefore(requestedDatetimeTo.toLocalTime())){
            requestedDatetimeTo = LocalDateTime.of(requestedDatetimeFrom.toLocalDate(), closeTime);
        }
        if( requestedDatetimeTo.isBefore(requestedDatetimeFrom)){
            throw new IllegalArgumentException(WRONG_DATE_SEQUENCE);
        }

        requestedWashTypeDuration = Duration.of(
                (long) (requestedWashTypeDuration.toMillis() * speedCoefficient),
                ChronoUnit.MILLIS);

        List<BookingStatuses> statuses = List.of(BookingStatuses.CANCELLED, BookingStatuses.DELETED);
        List<Booking> bookings = bookingRepository
                .findAllByRequestAndBox(requestedDatetimeFrom, requestedDatetimeTo, statuses, box.getId());

        LocalDateTime start = requestedDatetimeFrom;
        LocalDateTime end = requestedDatetimeTo;
        for (Booking b : bookings) {
            Duration checkedDurationBeforeNextBooking = Duration.between(start, b.getDatetimeFrom());
            if (checkedDurationBeforeNextBooking.compareTo(requestedWashTypeDuration) >= 0) {
                offer.setDuration(requestedWashTypeDuration);
                offer.setTimeFrom(start);
                offer.setTimeTo(start.plus(requestedWashTypeDuration));
                offer.setBox(box);
                return offer;
            }
            start = b.getDatetimeTo();
        }

        Duration workingDuration = Duration.between(start, end);
        if (workingDuration.compareTo(requestedWashTypeDuration) >= 0) {
            offer.setDuration(requestedWashTypeDuration);
            offer.setTimeFrom(start);
            offer.setTimeTo(start.plus(requestedWashTypeDuration));
            offer.setBox(box);
        }

        return offer;
    }
}
