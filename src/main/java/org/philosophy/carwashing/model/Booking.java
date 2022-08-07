package org.philosophy.carwashing.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.philosophy.carwashing.enums.BookingStatuses;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Сущность брони
 */
@Entity
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

    @OneToOne(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Request request;

    private BigDecimal totalCost;

    @Enumerated(EnumType.STRING)
    private BookingStatuses status;
    private Boolean isPaid;
    private LocalDateTime payment_time;

}
