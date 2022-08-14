package org.philosophy.carwashing.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "boxes")
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Box extends Discountable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "box_type_id")
    private BoxType boxType;

    @Column(name = "open_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openTime;

    @Column(name = "close_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closeTime;
}