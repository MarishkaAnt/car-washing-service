package org.philosophy.carwashing.repository;

import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    List<Booking> findAllByDatetimeFromGreaterThanAndDatetimeToLessThanAndStatusNotIn(
            LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo, List<BookingStatuses> status);
}
