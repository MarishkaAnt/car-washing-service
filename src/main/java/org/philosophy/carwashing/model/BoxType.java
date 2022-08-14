package org.philosophy.carwashing.model;

import lombok.*;
import org.philosophy.carwashing.enums.BoxTypes;

import javax.persistence.*;

@Entity
@Table(name = "box_types")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoxType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private BoxTypes type;

    @Column(name = "speedcoefficient")
    private Double speedCoefficient;

}
