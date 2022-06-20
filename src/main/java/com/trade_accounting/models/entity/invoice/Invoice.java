package com.trade_accounting.models.entity.invoice;

import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Класс-модель накладной
 *
 * @param date           - дата составления накладной
 * @param typeOfInvoce   - тип накладной
 * @param company        - наименование компании
 * @param contractor     - контрагент
 * @param invoicesStatus - статус накладной
 * @author Sanych
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
@SuperBuilder
@EqualsAndHashCode
public class Invoice extends OperationsAbstract {

    @NotNull
    @Column(name = "type_of_invoice")
    private TypeOfInvoice typeOfInvoice;

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

    @OneToOne
//    @ColumnDefault("1")
    @JoinColumn(name = "invoices_status_id")
    private InvoicesStatus invoicesStatus = new InvoicesStatus(1L, "Новый");

    @OneToMany(fetch = FetchType.LAZY)
    private List<InvoiceProduct> invoiceProducts;

}
