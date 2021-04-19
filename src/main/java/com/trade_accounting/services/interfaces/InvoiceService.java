package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.dto.InvoiceDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface InvoiceService extends AbstractService<InvoiceDto> {

    List<InvoiceDto> search(Specification<Invoice> specification);

    List<InvoiceDto> getAll(String typeOfInvoice);
}
