package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Класс-модель накладной
 *
 * @param date         - дата составления накладной
 * @param typeOfInvoce - тип накладной (приход/расход)
 * @param company      - наименование компании
 * @param contractor   - контрагент
 * @author Sanych
 * @see Invoice#Invoice(LocalDateTime, TypeOfInvoice, Company, Contractor)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data")
    private LocalDateTime date;

    @NotNull
    @Column(name = "type_of_invoice")
    private TypeOfInvoice typeOfInvoice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @Column(name = "is_Spend")
    @ColumnDefault("false")
    private boolean isSpend;

    public Invoice(@NotNull LocalDateTime date, @NotNull TypeOfInvoice typeOfInvoice,
                   @NotNull Company company, @NotNull Contractor contractor) {
        this.date = date;
        this.typeOfInvoice = typeOfInvoice;
        this.company = company;
        this.contractor = contractor;
    }
}
