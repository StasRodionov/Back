package com.trade_accounting.services.impl.Stubs;


import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.models.dto.ProjectDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.utils.DtoMapper;
import org.mapstruct.factory.Mappers;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.models.dto.DepartmentDto;

public class DtoStubs {
    public static DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);

    public static PaymentDto getPaymentDto(Long id) {
        return dtoMapper.paymentToPaymentDto(ModelStubs.getPayment(id));
    }

    public static TypeOfPriceDto getTypeOfPriceDto(Long id) {
        return dtoMapper.typeOfPriceToTypeOfPriceDto(ModelStubs.getTypeOfPrice(id));
    }

    public static BankAccountDto getBankAccountDto(Long id) {
        return dtoMapper.bankAccountToBankAccountDto(
                ModelStubs.getBankAccount(id)
        );
    }

    public static CompanyDto getCompanyDto(Long id) {
        return dtoMapper.companyToCompanyDto(
                ModelStubs.getCompany(id)
        );
    }

    public static ProjectDto getProjectDto(Long id) {
        return dtoMapper.projectToProjectDto(
                ModelStubs.getProject(id)
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

    public static EmployeeDto getEmployeeDto(Long id) {
        return dtoMapper.employeeToEmployeeDto(
                ModelStubs.getEmployee(id)
        );
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
    public static DepartmentDto getDepartmentDto(Long id){
        return dtoMapper.departmentToDepartmentDto(
                ModelStubs.getDepartment(id));
    }

    public static PositionDto getPositionDto(Long id){
        return dtoMapper.positionToPositionDto(
                ModelStubs.getPosition(id));
    }
}
