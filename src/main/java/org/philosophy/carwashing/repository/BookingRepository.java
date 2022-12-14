package org.philosophy.carwashing.repository;

import org.philosophy.carwashing.enums.BookingStatuses;
import org.philosophy.carwashing.model.Booking;
import org.philosophy.carwashing.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    List<Booking> findAllByDatetimeToGreaterThanEqualAndDatetimeFromLessThanEqualAndStatusNotInAndBoxOrderByDatetimeFrom(
            LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo, List<BookingStatuses> statuses, Box box);

    @Query("select b " +
            "from Booking as b " +
            "where b.datetimeFrom >= ?1" +
            "  and b.datetimeTo <= ?2 " +
            "  and  b.status in (?3) " +
            "  and b.box.id = ?4 " +
            "order by b.datetimeFrom asc")
    List<Booking> findAllByRequestAndBox(
            LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo, List<BookingStatuses> statuses, Integer boxId);


    @Query(nativeQuery = true,
    value = "select sum(total_cost) as total_money_amount " +
            "from bookings where status = 'PAID' and is_paid = true  " +
            "group by status")
    BigDecimal getMoneyAmount();
}
