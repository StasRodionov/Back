package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.company.SupplierAccount;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.company.SupplierAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface SupplierAccountMapper {
    //Supplier
//    @Mapping(target = "date", ignore = true)
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "contractId", target = "contract.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    SupplierAccount toModel(SupplierAccountDto supplierAccountDto);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "contractId", source = "contract.id")
    @Mapping(target = "contractorId", source = "contractor.id")
    SupplierAccountDto toDto(SupplierAccount supplierAccount);
}
