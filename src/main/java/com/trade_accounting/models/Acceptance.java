package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acceptances")
public class Acceptance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "incoming_number")
    private String incomingNumber;

    @Column(name = "incoming_number_date", columnDefinition = "date default current_date")
    private LocalDate incomingNumberDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @OneToOne(fetch = FetchType.LAZY)
    private Project project;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @OneToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @OneToMany(fetch = FetchType.LAZY)
    private List<AcceptanceProduction> acceptanceProduction;
}
