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
    private Integer id;

    @Enumerated(EnumType.STRING)
    private BoxTypes type;

    private Double speedcoefficient;

}
