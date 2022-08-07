package org.philosophy.carwashing.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "discount_constrains_settings")
@Getter
@Setter
@RequiredArgsConstructor
public class DiscountSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double maxValue;
    private Double minValue;

}
