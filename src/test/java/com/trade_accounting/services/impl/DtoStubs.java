package com.trade_accounting.services.impl;

import com.trade_accounting.models.AttributeOfCalculationObject;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.utils.DtoMapper;
import org.mapstruct.factory.Mappers;

public class DtoStubs {
    private static DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);

    public static CompanyDto getCompanyDto(Long id) {
        return dtoMapper.companyToCompanyDto(
                ModelStubs.getCompany(id)
        );
    }

    public static AttributeOfCalculationObjectDto getAttributeOfCalculationObjectDto(Long id) {
        return dtoMapper.attributeOfCalculationObjectToAttributeOfCalculationObjectDto(
                ModelStubs.getAttributeOfCalculationObject(id)
        );
    }

    public static ContractDto getContractDto(Long id){
        return dtoMapper.contractToContractDto(ModelStubs.getContract(id));
    }
}
