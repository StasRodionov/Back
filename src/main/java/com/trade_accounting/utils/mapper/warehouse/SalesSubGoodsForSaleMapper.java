package com.trade_accounting.utils.mapper.warehouse;

import com.trade_accounting.models.dto.warehouse.SalesSubGoodsForSaleDto;
import com.trade_accounting.models.entity.warehouse.SalesSubGoodsForSale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesSubGoodsForSaleMapper {
    default SalesSubGoodsForSale toModel(SalesSubGoodsForSaleDto salesSubGoodsForSaleDto) {
        if (salesSubGoodsForSaleDto == null) {
            return null;
        } else {
            return SalesSubGoodsForSale.builder()
                    .id(salesSubGoodsForSaleDto.getId())
                    .name(salesSubGoodsForSaleDto.getName())
                    .code(salesSubGoodsForSaleDto.getCode())
                    .vendorCode(salesSubGoodsForSaleDto.getVendorCode())
                    .transferred(salesSubGoodsForSaleDto.getTransferred())
                    .accepted(salesSubGoodsForSaleDto.getAccepted())
                    .amount(salesSubGoodsForSaleDto.getAmount())
                    .sum(salesSubGoodsForSaleDto.getSum())
                    .returned(salesSubGoodsForSaleDto.getReturned())
                    .remainder(salesSubGoodsForSaleDto.getRemainder())
                    .reportAmount(salesSubGoodsForSaleDto.getReportAmount())
                    .reportSum(salesSubGoodsForSaleDto.getReportSum())
                    .notReportAmount(salesSubGoodsForSaleDto.getNotReportAmount())
                    .notReportSum(salesSubGoodsForSaleDto.getNotReportSum())
                    .build();
        }
    }

        default SalesSubGoodsForSaleDto toDto(SalesSubGoodsForSale salesSubGoodsForSale) {
            if (salesSubGoodsForSale == null) {
                return null;
            } else {
                return SalesSubGoodsForSaleDto.builder()
                        .id(salesSubGoodsForSale.getId())
                        .name(salesSubGoodsForSale.getName())
                        .code(salesSubGoodsForSale.getCode())
                        .vendorCode(salesSubGoodsForSale.getVendorCode())
                        .transferred(salesSubGoodsForSale.getTransferred())
                        .accepted(salesSubGoodsForSale.getAccepted())
                        .amount(salesSubGoodsForSale.getAmount())
                        .sum(salesSubGoodsForSale.getSum())
                        .returned(salesSubGoodsForSale.getReturned())
                        .remainder(salesSubGoodsForSale.getRemainder())
                        .reportAmount(salesSubGoodsForSale.getReportAmount())
                        .reportSum(salesSubGoodsForSale.getReportSum())
                        .notReportAmount(salesSubGoodsForSale.getNotReportAmount())
                        .notReportSum(salesSubGoodsForSale.getNotReportSum())
                        .build();
            }
    }
}
