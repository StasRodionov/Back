package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requsts_productions")
public class RequestsProductions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_of_the_certificate")
    private String numberOfTheCertificate;

    @Column(name = "date_of_the_certificate")
    private LocalDate dateOfTheCertificate;

    @OneToOne
    private TechnicalCard technicalCard;

    @Column(name = "volume")
    private Integer volume;

    @OneToOne
    private Warehouse warehouse;

}
