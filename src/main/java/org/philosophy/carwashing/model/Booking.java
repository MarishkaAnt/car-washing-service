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
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "wash_type_id")
    private WashType washType;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "box_id")
    private Box box;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private BookingStatuses status;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "datetime_from")
    private LocalDateTime datetimeFrom;

    @Column(name = "datetime_to")
    private LocalDateTime datetimeTo;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "payment_time")
    private LocalDateTime paymentTime;

}
