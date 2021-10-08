package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.AcceptanceProduction;
import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.Revenue;
import com.trade_accounting.models.dto.RevenueDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;


@Mapper(componentModel = "spring")
public interface RevenueMapper {
    default RevenueDto toDto(Revenue revenue) {
        RevenueDto revenueDto = new RevenueDto();
        if (revenue == null) {
            return null;
        } else {
            revenueDto.setId(revenue.getId());

            Product product = revenue.getProduct();
            if (product == null) {
                return null;
            } else {
                revenueDto.setIdProduct(product.getId());
                revenueDto.setDescription(product.getDescription());
                revenueDto.setUnitId(product.getUnit().getId());

                AcceptanceProduction acceptanceProduction = revenue.getAcceptanceProduction();
                if (acceptanceProduction == null) {
                    return null;
                } else {
                    revenueDto.setAmountAcceptance(acceptanceProduction.getAmount());

                    Acceptance acceptance = revenue.getAcceptance();
                    if (acceptance == null) {
                        return null;
                    } else {
                        revenueDto.setIncomingNumberDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(
                                acceptance.getIncomingNumberDate()));

                        InvoiceProduct invoiceProduct = revenue.getInvoiceProduct();
                        if (invoiceProduct == null) {
                            return null;
                        } else {
                            revenueDto.setAmountShipment(invoiceProduct.getAmount());
                            return revenueDto;
                        }
                    }
                }
            }
        }
    }
}
