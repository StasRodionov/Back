package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "requsts_productions")
public class RequestsProductions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberOfTheCertificate;

    private LocalDate dateOfTheCertificate;

    @OneToOne(fetch = FetchType.LAZY)
    private TechnicalCard technicalCard;

    private Integer volume;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;
}
