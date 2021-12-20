package com.trade_accounting.utils.mapper;


import com.trade_accounting.models.Product;
import com.trade_accounting.models.ProductPrice;
import com.trade_accounting.models.PurchaseControl;
import com.trade_accounting.models.PurchaseForecast;
import com.trade_accounting.models.PurchaseHistoryOfSales;
import com.trade_accounting.models.dto.PurchaseControlDto;
import com.trade_accounting.models.dto.PurchaseHistoryOfSalesDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseHistoryOfSalesMapper {
    /**
     * @return PurchaseHistoryOfSales
     */


    default PurchaseHistoryOfSales toModel(PurchaseHistoryOfSalesDto purchaseHistoryOfSalesDto) {
        if ( purchaseHistoryOfSalesDto == null ) {
            return null;
        }

        return  PurchaseHistoryOfSales.builder()
        .id(purchaseHistoryOfSalesDto.getId())
        .sumOfProducts(purchaseHistoryOfSalesDto.getSumOfProducts())
        .productPrice(PurchaseHistoryOfSalesDtoToProductPrice(purchaseHistoryOfSalesDto))
        .productMargin(purchaseHistoryOfSalesDto.getProductMargin())
        .productSalesPerDay(purchaseHistoryOfSalesDto.getProductSalesPerDay())
                .build();

    }


    default PurchaseHistoryOfSalesDto toDto(PurchaseHistoryOfSales purchaseHistoryOfSales) {
        PurchaseHistoryOfSalesDto purchaseHistoryOfSalesDto = new PurchaseHistoryOfSalesDto();
        if ( purchaseHistoryOfSales == null ) {
            return null;
        }

        purchaseHistoryOfSalesDto.setId(purchaseHistoryOfSales.getId());
        purchaseHistoryOfSalesDto.setSumOfProducts(purchaseHistoryOfSales.getSumOfProducts());
        purchaseHistoryOfSalesDto.setProductPriceId(PurchaseHistoryOfSalesProductPrice(purchaseHistoryOfSales));
        purchaseHistoryOfSalesDto.setProductProfitMargin(purchaseHistoryOfSales.getProductProfitMargin());
        purchaseHistoryOfSalesDto.setProductSalesPerDay(purchaseHistoryOfSales.getProductSalesPerDay());

        return purchaseHistoryOfSalesDto;
    }


    private ProductPrice PurchaseHistoryOfSalesDtoToProductPrice (PurchaseHistoryOfSalesDto purchaseHistoryOfSalesDto){
    return ProductPrice.builder()
            .id(purchaseHistoryOfSalesDto.getProductPriceId()).build();

    }

    private Long PurchaseHistoryOfSalesProductPrice (PurchaseHistoryOfSales purchaseHistoryOfSales) {
        if ( purchaseHistoryOfSales == null ) {
            return null;
        }
        ProductPrice productPrice = purchaseHistoryOfSales.getProductPrice();
        if ( productPrice == null ) {
            return null;
        }
        Long id = productPrice.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

}
