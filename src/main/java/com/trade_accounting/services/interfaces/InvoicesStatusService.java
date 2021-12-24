package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.InvoicesStatusDto;


public interface InvoicesStatusService extends AbstractService<InvoicesStatusDto> {

    InvoicesStatusDto getByName(String statusName );
}
