package org.philosophy.carwashing.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.philosophy.carwashing.enums.BookingStatuses;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Сущность брони
 */
@Entity
@Table(name = "bookings")
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne()
    @Fetch(FetchMode.JOIN)
    private Request request;

    @OneToOne()
    @Fetch(FetchMode.JOIN)
    private Box box;

    private Integer userId;

    private Double totalCost;
    private LocalDateTime datetimeFrom;
    private LocalDateTime datetimeTo;
    private Duration duration;

    @Enumerated(EnumType.STRING)
    private BookingStatuses status;
    private Boolean isPaid;
    private LocalDateTime paymentTime;

}
