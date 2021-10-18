package com.trade_accounting.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Класс-модель накладной
 *
 * @param date         - дата составления накладной
 * @param typeOfInvoce - тип накладной
 * @param company      - наименование компании
 * @param contractor   - контрагент
 * @author Sanych
 */
@Data
@Entity
@Table(name = "invoice")
@Builder
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
    @ToString.Exclude
    private Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Contractor contractor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Warehouse warehouse;

    @Column(name = "is_Spend")
    @ColumnDefault("false")
    private Boolean isSpend;

    @Column(name = "comment")
    private String comment;

    @OneToOne
    @ColumnDefault("1")
    private InvoicesStatus invoicesStatus;

    public Invoice(Long id, @NotNull LocalDateTime date, @NotNull TypeOfInvoice typeOfInvoice, @NotNull Company company, @NotNull Contractor contractor, @NotNull Warehouse warehouse, Boolean isSpend, String comment, InvoicesStatus invoiceStatus) {
        this.id = id;
        this.date = date;
        this.typeOfInvoice = typeOfInvoice;
        this.company = company;
        this.contractor = contractor;
        this.warehouse = warehouse;
        this.isSpend = isSpend;
        this.comment = comment;
        this.invoicesStatus = invoicesStatus;
    }

    public Invoice() {
    }
}
