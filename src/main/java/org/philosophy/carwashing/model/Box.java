package org.philosophy.carwashing.model;

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

    @OneToOne()
    @Fetch(FetchMode.JOIN)
    private BoxType boxType;
    private LocalTime openTime;
    private LocalTime closeTime;
}