package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tech_operations")
public class TechnicalOperations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    @NotNull
    @ColumnDefault(value = "00001")
    private String number;

    @Column(name = "time")
    private LocalDateTime time;

    @OneToOne
    private TechnicalCard technicalCard;

    @Column(name = "volume")
    private Integer volumeProduction;

    @OneToOne
    private Warehouse warehouse;
}
