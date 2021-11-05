package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.SupplierAccount;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.SupplierAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface SupplierAccountMapper {
    // SupplierAccounts
//    @Mappings({
//            @Mapping(source = "company.id", target = "companyId"),
//            @Mapping(source = "contract.id", target = "contractId"),
//            @Mapping(source = "contractor.id", target = "contractorId"),
//            @Mapping(source = "warehouse.id", target = "warehouseId"),
//    })
    default SupplierAccountDto toDto(SupplierAccount supplierAccount){
            SupplierAccountDto supplierAccountDto = new SupplierAccountDto();
            if(supplierAccount==null) {
                return null;
            } else {
                supplierAccountDto.setId(supplierAccount.getId());
                supplierAccountDto.setIsSpend(supplierAccount.getIsSpend());
                supplierAccountDto.setComment(supplierAccount.getComment());
                supplierAccountDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                        .format(supplierAccount.getDate()));
                Warehouse warehouse = supplierAccount.getWarehouse();
                Company company = supplierAccount.getCompany();
                Contractor contractor = supplierAccount.getContractor();
                Contract contract = supplierAccount.getContract();
                if (warehouse==null){
                    return null;
                } else {
                    supplierAccountDto.setWarehouseId(warehouse.getId());
                    if (company==null) {
                        return null;
                    } else {
                        supplierAccountDto.setCompanyId(company.getId());
                        if (contractor==null) {
                            return null;
                        } else {
                            supplierAccountDto.setContractorId(contractor.getId());
                            if (contract==null) {
                                return null;
                            } else {
                                supplierAccountDto.setContractId(contract.getId());
                                return supplierAccountDto;
                            }
                        }
                    }
                }
            }
        }

//    @Mappings({
//            @Mapping(source = "companyId", target = "company.id"),
//            @Mapping(source = "contractId", target = "contract.id"),
//            @Mapping(source = "contractorId", target = "contractor.id"),
//            @Mapping(source = "warehouseId", target = "warehouse.id"),
//    })
    @Mapping(target = "date", ignore = true)
    SupplierAccount toModel(SupplierAccountDto supplierAccountDto);
}
