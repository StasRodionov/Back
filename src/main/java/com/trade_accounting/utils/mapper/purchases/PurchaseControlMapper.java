package com.trade_accounting.utils.mapper.purchases;

import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.purchases.PurchaseControl;
import com.trade_accounting.models.entity.purchases.PurchaseCurrentBalance;
import com.trade_accounting.models.entity.purchases.PurchaseForecast;
import com.trade_accounting.models.entity.purchases.PurchaseHistoryOfSales;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface PurchaseControlMapper {
    /**
     * @return PurchaseControl
     */

    default PurchaseControl toModel(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }
//        warehouseService
//        contractorService
//        companyService
        PurchaseControl.PurchaseControlBuilder purchaseControl = PurchaseControl.builder();

        purchaseControl.historyOfSales(purchaseControlDtoToPurchaseHistoryOfSales(purchaseControlDto));
        purchaseControl.currentBalance(purchaseControlDtoToPurchaseCurrentBalance(purchaseControlDto));
        purchaseControl.forecast(purchaseControlDtoToPurchaseForecast(purchaseControlDto));
        purchaseControl.product(purchaseControlDtoToProduct(purchaseControlDto));
        purchaseControl.warehouse(purchaseControlDtoToWarehouse(purchaseControlDto));
        purchaseControl.contractor(purchaseControlDtoToContractorService(purchaseControlDto));
        purchaseControl.company(purchaseControlDtoToCompanyService(purchaseControlDto));

        purchaseControl.id(purchaseControlDto.getId());
        purchaseControl.date(LocalDateTime.parse(purchaseControlDto.getDate()));
        purchaseControl.productCode(purchaseControlDto.getProductCode());
        purchaseControl.articleNumber(purchaseControlDto.getArticleNumber());
        purchaseControl.productMeasure(purchaseControlDto.getProductMeasure());
        purchaseControl.productQuantity(purchaseControlDto.getProductQuantity());

        return purchaseControl.build();
    }

    private PurchaseHistoryOfSales purchaseControlDtoToPurchaseHistoryOfSales(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }

        PurchaseHistoryOfSales.PurchaseHistoryOfSalesBuilder purchaseHistoryOfSales = PurchaseHistoryOfSales.builder();

        purchaseHistoryOfSales.id(purchaseControlDto.getHistoryOfSalesId());

        return purchaseHistoryOfSales.build();
    }

    private PurchaseCurrentBalance purchaseControlDtoToPurchaseCurrentBalance(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }

        PurchaseCurrentBalance.PurchaseCurrentBalanceBuilder purchaseCurrentBalance = PurchaseCurrentBalance.builder();

        purchaseCurrentBalance.id(purchaseControlDto.getCurrentBalanceId());

        return purchaseCurrentBalance.build();
    }

    private PurchaseForecast purchaseControlDtoToPurchaseForecast(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }

        PurchaseForecast.PurchaseForecastBuilder purchaseForecast = PurchaseForecast.builder();

        purchaseForecast.id(purchaseControlDto.getForecastId());

        return purchaseForecast.build();
    }

    private Product purchaseControlDtoToProduct(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }
        Product.ProductBuilder productBuilder = Product.builder();
        productBuilder.id(purchaseControlDto.getProductNameId());
        return productBuilder.build();
    }

    private Warehouse purchaseControlDtoToWarehouse(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }
        Warehouse.WarehouseBuilder warehouseBuilder = Warehouse.builder();
        warehouseBuilder.id(purchaseControlDto.getWarehouseId());
        return warehouseBuilder.build();
    }

    private Contractor purchaseControlDtoToContractorService(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }
        Contractor.ContractorBuilder contractorBuilder = Contractor.builder();
        contractorBuilder.id(purchaseControlDto.getContractorId());
        return contractorBuilder.build();
    }

    private Company purchaseControlDtoToCompanyService(PurchaseControlDto purchaseControlDto) {
        if (purchaseControlDto == null) {
            return null;
        }
        Company.CompanyBuilder companyBuilder = Company.builder();
        companyBuilder.id(purchaseControlDto.getCompanyId());
        return companyBuilder.build();
    }

    /**
     * @return PurchaseControlDto
     */
//    @Mappings({
//            @Mapping(source = "historyOfSales.id", target = "historyOfSalesId"),
//            @Mapping(source = "currentBalance.id", target = "currentBalanceId"),
//            @Mapping(source = "forecast.id", target = "forecastId")
//    })
    default PurchaseControlDto toDto(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }

        PurchaseControlDto.PurchaseControlDtoBuilder purchaseControlDto = PurchaseControlDto.builder();

        purchaseControlDto.historyOfSalesId(purchaseControlHistoryOfSalesId(purchaseControl));
        purchaseControlDto.currentBalanceId(purchaseControlCurrentBalanceId(purchaseControl));
        purchaseControlDto.forecastId(purchaseControlForecastId(purchaseControl));
        purchaseControlDto.productNameId(purchaseControlProductId(purchaseControl));
        purchaseControlDto.warehouseId(purchaseControlWarehouseId(purchaseControl));
        purchaseControlDto.contractorId(purchaseControlContractorId(purchaseControl));
        purchaseControlDto.companyId(purchaseControlCompanyId(purchaseControl));

        purchaseControlDto.id(purchaseControl.getId());
        purchaseControlDto.date(String.valueOf(purchaseControl.getDate()));
        purchaseControlDto.productCode(purchaseControl.getProductCode());
        purchaseControlDto.articleNumber(purchaseControl.getArticleNumber());
        purchaseControlDto.productMeasure(purchaseControl.getProductMeasure());
        purchaseControlDto.productQuantity(purchaseControl.getProductQuantity());

        return purchaseControlDto.build();
    }

    private Long purchaseControlProductId(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }
        Product product = purchaseControl.getProduct();
        if (product == null) {
            return null;
        }
        Long id = product.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long purchaseControlHistoryOfSalesId(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }
        PurchaseHistoryOfSales historyOfSales = purchaseControl.getHistoryOfSales();
        if (historyOfSales == null) {
            return null;
        }
        Long id = historyOfSales.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long purchaseControlCurrentBalanceId(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }
        PurchaseCurrentBalance currentBalance = purchaseControl.getCurrentBalance();
        if (currentBalance == null) {
            return null;
        }
        Long id = currentBalance.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long purchaseControlForecastId(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }
        PurchaseForecast forecast = purchaseControl.getForecast();
        if (forecast == null) {
            return null;
        }
        Long id = forecast.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long purchaseControlWarehouseId(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }
        Warehouse warehouse = purchaseControl.getWarehouse();
        if (warehouse == null) {
            return null;
        }
        Long id = warehouse.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long purchaseControlContractorId(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }
        Contractor contractor = purchaseControl.getContractor();
        if (contractor == null) {
            return null;
        }
        Long id = contractor.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long purchaseControlCompanyId(PurchaseControl purchaseControl) {
        if (purchaseControl == null) {
            return null;
        }
        Company company = purchaseControl.getCompany();
        if (company == null) {
            return null;
        }
        Long id = company.getId();
        if (id == null) {
            return null;
        }
        return id;
    }
}
