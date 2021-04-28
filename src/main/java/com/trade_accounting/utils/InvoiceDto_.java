package com.trade_accounting.utils;

import com.trade_accounting.models.dto.InvoiceDto;

import javax.persistence.metamodel.SingularAttribute;

public abstract class InvoiceDto_ {
    public static volatile SingularAttribute<InvoiceDto, Long> id;
    public static volatile SingularAttribute<InvoiceDto, String> date;



    public static final String ID = "id";
    public static final String DATE = "date";
}
