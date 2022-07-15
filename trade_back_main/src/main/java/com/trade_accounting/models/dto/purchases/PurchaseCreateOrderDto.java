package com.trade_accounting.models.dto.purchases;

import com.trade_accounting.models.dto.invoice.TypeOfOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Класс-обертка для создания накладной
 *
 * @param purchaseControlDtoList   - лист с необходимыми данными для создания накладной
 * @param typeOfOrder   - тип заказа (общий или по поставщикам)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseCreateOrderDto {

    private List<PurchaseControlDto> purchaseControlDtoList;

    private TypeOfOrder typeOfOrder;

}
