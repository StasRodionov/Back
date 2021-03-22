package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.utils.DtoMapper;
import org.mapstruct.factory.Mappers;

public class DtoStubs {
    public static DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);

    public static PaymentDto getPaymentDto(Long id) {
        return dtoMapper.paymentToPaymentDto(ModelStubs.getPayment(id));
    }

    public static CompanyDto getCompanyDto(Long id) {
        return dtoMapper.companyToCompanyDto(
                ModelStubs.getCompany(id)
        );
    }

    public static TypeOfContractorDto getTypeOfContractorDto(Long id){
        return dtoMapper.typeOfContractorToTypeOfContractorDto(
                ModelStubs.getTypeOfContractor(id)
        );
    }

    public static AttributeOfCalculationObjectDto getAttributeOfCalculationObjectDto(Long id) {
        return dtoMapper.attributeOfCalculationObjectToAttributeOfCalculationObjectDto(
                ModelStubs.getAttributeOfCalculationObject(id)
        );
    }
}
