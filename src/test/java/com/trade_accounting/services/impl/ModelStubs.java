package com.trade_accounting.services.impl;

import com.trade_accounting.models.TypeOfPrice;

public class ModelStubs {
    public static TypeOfPrice getTypeOfPrice(Long id) {
        return new TypeOfPrice(id, "name", "00001");
    }
}
