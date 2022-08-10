package org.philosophy.carwashing.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Сущность запроса пользователя на создание брони
 */
@Entity
@Table(name = "request")
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @Fetch(FetchMode.JOIN)
    private WashType washType;

    private Integer userId;

    private LocalDateTime datetimeFrom;
    private LocalDateTime datetimeTo;
}
