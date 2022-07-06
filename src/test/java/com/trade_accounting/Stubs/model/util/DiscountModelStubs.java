package com.trade_accounting.Stubs.model.util;

import com.trade_accounting.models.dto.util.DiscountDto;
import com.trade_accounting.models.entity.util.Discount;

public class DiscountModelStubs {
    public static Discount getDiscount(Long id) {
        return Discount.builder()
                .id(id)
                .name("Скидка 1")
                .isActive(true)
                .isBonusProgram(true)
                .bonusProgram(BonusProgramModelStubs.getBonusProgram(id))
                .type("Бонусная программа")
                .build();
    }

    public static DiscountDto getDiscountDto(Long id) {
        return new DiscountDto(id, true, "Скидка 1", "Бонусная программа", true, id);
    }
}
