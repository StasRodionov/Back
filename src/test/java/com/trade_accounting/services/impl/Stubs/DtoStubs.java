package com.trade_accounting.services.impl.Stubs;

import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.utils.DtoMapper;
import org.mapstruct.factory.Mappers;

public class DtoStubs {
    private static final DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);

    public static TypeOfPriceDto getTypeOfPriceDto(Long id) {
        return dtoMapper.typeOfPriceToTypeOfPriceDto(ModelStubs.getTypeOfPrice(id));
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

    public static DepartmentDto getDepartmentDto(Long id){
        return dtoMapper.departmentToDepartmentDto(
                ModelStubs.getDepartment(id));
    }

    public static PositionDto getPositionDto(Long id){
        return dtoMapper.positionToPositionDto(
            ModelStubs.getPosition(id));
    }

    public static ContractDto getContractDto(Long id){
        return dtoMapper.contractToContractDto(ModelStubs.getContract(id));
    }

    public static TaxSystemDto getTaxSystemDto(Long id){
        return dtoMapper.taxSystemToTaxSystemDto(
                ModelStubs.getTaxSystem(id)
        );
    }

    public static InvoiceDto getInvoiceDto(Long id) {
        return dtoMapper.invoiceToInvoiceDto(
                ModelStubs.getInvoice(id)
        );
    }

    public static InvoiceProductDto getInvoiceProductDto(Long id) {
        return dtoMapper.invoiceProductToInvoiceProductDto(ModelStubs.getInvoiceProduct(id));
    }

    public static LegalDetailDto getLegalDetailDto(Long id) {
        return dtoMapper.legalDetailToLegalDetailDto(ModelStubs.getLegalDetail(1L));
    }
}
