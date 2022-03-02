package com.trade_accounting.services.interfaces.invoice;

import com.trade_accounting.models.dto.invoice.InvoiceReceivedDto;
import com.trade_accounting.services.interfaces.util.AbstractService;

import java.util.List;

public interface InvoiceReceivedService extends AbstractService<InvoiceReceivedDto> {

    List<InvoiceReceivedDto> searchString(String search);

}
