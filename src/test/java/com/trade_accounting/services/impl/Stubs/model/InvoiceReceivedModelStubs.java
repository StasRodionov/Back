package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.InvoiceReceived;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class InvoiceReceivedModelStubs {
    public static InvoiceReceived getInvoiceReceived(Long id) {
        return InvoiceReceived.builder()
                .id(id)
                .acceptance(AcceptanceModelStubs.getAcceptance(id))
                .contractor(ContractorModelStubs.getContractor(id))
                .company(CompanyModelStubs.getCompany(id))
                .build();
    }
}
