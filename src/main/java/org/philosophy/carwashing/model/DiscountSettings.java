package org.philosophy.carwashing.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "discount_settings")
@Getter
@Setter
@RequiredArgsConstructor
public class DiscountSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "max_value")
    private Integer maxValue;

    @Column(name = "min_value")
    private Integer minValue;

}
