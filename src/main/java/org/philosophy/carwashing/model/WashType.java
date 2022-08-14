package org.philosophy.carwashing.model;

import com.vladmihalcea.hibernate.type.interval.PostgreSQLIntervalType;
import lombok.*;
import org.hibernate.annotations.TypeDef;
import org.philosophy.carwashing.enums.WashTypes;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;

@Entity
@Table(name = "wash_types")
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(
        typeClass = PostgreSQLIntervalType.class,
        defaultForType = Duration.class
)
public class WashType extends Discountable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "wash_type_name")
    private WashTypes name;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "wash_cost")
    private BigDecimal cost;
}
