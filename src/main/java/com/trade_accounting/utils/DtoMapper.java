package com.trade_accounting.utils;

import com.trade_accounting.models.AttributeOfCalculationObject;
import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.ContractorGroup;
import com.trade_accounting.models.Currency;
import com.trade_accounting.models.Department;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Image;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.Payment;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.ProductGroup;
import com.trade_accounting.models.ProductPrice;
import com.trade_accounting.models.Project;
import com.trade_accounting.models.Role;
import com.trade_accounting.models.TaxSystem;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.Unit;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.models.dto.ProductPriceDto;
import com.trade_accounting.models.dto.ProjectDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.models.dto.WarehouseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    //AttributeOfCalculationObjectDto
    AttributeOfCalculationObjectDto
    attributeOfCalculationObjectToAttributeOfCalculationObjectDto(
            AttributeOfCalculationObject attributeOfCalculationObject
    );

    AttributeOfCalculationObject
    attributeOfCalculationObjectDtoToAttributeOfCalculationObject(
            AttributeOfCalculationObjectDto attributeOfCalculationObjectDto
    );

    //BankAccount
    BankAccountDto bankAccountToBankAccountDto(BankAccount bankAccount);

    BankAccount bankAccountDtoToBankAccount(BankAccountDto bankAccountDto);

    //Company
    @Mappings({
            @Mapping(source = "bankAccounts", target = "bankAccountDto"),
            @Mapping(source = "legalDetail", target = "legalDetailDto")
    })
    CompanyDto companyToCompanyDto(Company company);

    @Mappings({
            @Mapping(source = "bankAccountDto", target = "bankAccounts"),
            @Mapping(source = "legalDetailDto", target = "legalDetail")
    })
    Company companyDtoToCompany(CompanyDto companyDto);

    //Contract
    @Mappings({
            @Mapping(source = "company", target = "companyDto"),
            @Mapping(source = "bankAccount", target = "bankAccountDto"),
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "legalDetail", target = "legalDetailDto")
    })
    ContractDto contractToContractDto(Contract contract);

    @Mappings({
            @Mapping(source = "companyDto", target = "company"),
            @Mapping(source = "bankAccountDto", target = "bankAccount"),
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "legalDetailDto", target = "legalDetail")
    })
    Contract contractDtoToContract(ContractDto contractDto);

    //Contractor
    ContractorDto contractorToContractorDto(Contractor contractor);

    Contractor contractorDtoToContractor(ContractorDto contractorDto);

    //ContractorGroup
    ContractorGroupDto contractorGroupToContractorGroupDto(ContractorGroup contractorGroup);

    ContractorGroup contractorGroupDtoToContractorGroup(ContractorGroupDto contractorGroupDto);

    //Currency
    CurrencyDto currencyToCurrencyDto(Currency currency);

    Currency currencyDtoToCurrency(CurrencyDto currencyDto);

    //Department
    DepartmentDto departmentToDepartmentDto(Department department);

    Department departmentDtoToDepartment(DepartmentDto department);

    // Employee
    @Mappings({
            @Mapping(source = "department", target = "departmentDto"),
            @Mapping(source = "position", target = "positionDto"),
            @Mapping(source = "roles", target = "roleDto"),
            @Mapping(source = "image", target = "imageDto")
    })
    EmployeeDto employeeToEmployeeDto(Employee emp);

    @Mappings({
            @Mapping(source = "departmentDto", target = "department"),
            @Mapping(source = "positionDto", target = "position"),
            @Mapping(source = "roleDto", target = "roles"),
            @Mapping(source = "imageDto", target = "image")
    })
    Employee employeeDtoToEmployee(EmployeeDto emp);

    //Image
    ImageDto imageToImageDto(Image image);

    Image imageDtoToImage(ImageDto image);

    //Invoice
    @Mappings({
            @Mapping(source = "company", target = "companyDto"),
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "warehouse", target = "warehouseDto"),
    })
    InvoiceDto invoiceToInvoiceDto(Invoice invoice);

    @Mappings({
            @Mapping(source = "companyDto", target = "company"),
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "warehouseDto", target = "warehouse"),
    })
    Invoice invoiceDtoToInvoice(InvoiceDto emp);

    //InvoiceProduct
    @Mappings({
            @Mapping(source = "invoice", target = "invoiceDto"),
            @Mapping(source = "product", target = "productDto")
    })
    InvoiceProductDto invoiceProductToInvoiceProductDto(InvoiceProduct invoiceProduct);

    @Mappings({
            @Mapping(source = "invoiceDto", target = "invoiceDto"),
            @Mapping(source = "productDto", target = "product")
    })
    InvoiceProduct invoiceProductDtoToInvoiceProduct(InvoiceProductDto invoiceProductDto);

    //LegalDetail
    @Mappings({
            @Mapping(source = "typeOfContractor", target = "typeOfContractorDto")
    })
    LegalDetailDto legalDetailToLegalDetailDto(LegalDetail legalDetail);

    @Mappings({
            @Mapping(source = "typeOfContractor", target = "typeOfContractorDto")
    })
    LegalDetail legalDetailDtoToLegalDetail(LegalDetailDto legalDetailDto);

    //Payment
    @Mappings({
            @Mapping(source = "company", target = "companyDto"),
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "contract", target = "contractDto"),
            @Mapping(source = "project", target = "projectDto")
    })
    PaymentDto paymentToPaymentDto(Payment payment);

    @Mappings({
            @Mapping(source = "companyDto", target = "company"),
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "contractDto", target = "contract"),
            @Mapping(source = "projectDto", target = "project")
    })
    Payment paymentDtoToPayment(PaymentDto paymentDto);

    //Position
    PositionDto positionToPositionDto(Position position);

    Position positionDtoToPosition(PositionDto position);

    //Product
    @Mappings({
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "productPrices", target = "productPriceDtos"),
            @Mapping(source = "taxSystem", target = "taxSystemDto"),
            @Mapping(source = "images", target = "imageDto"),
            @Mapping(source = "productGroup", target = "productGroupDto"),
            @Mapping(source = "attributeOfCalculationObject", target = "attributeOfCalculationObjectDto")
    })
    ProductDto productToProductDto(Product product);

    @Mappings({
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "productPriceDtos", target = "productPrices"),
            @Mapping(source = "taxSystemDto", target = "taxSystem"),
            @Mapping(source = "imageDto", target = "images"),
            @Mapping(source = "productGroupDto", target = "productGroup"),
            @Mapping(source = "attributeOfCalculationObjectDto", target = "attributeOfCalculationObject")
    })
    Product productDtoToProduct(ProductDto productDto);

    //ProductGroup
    //На момент написания не известно работает или нет из-за рекурсии
    @Mappings({
            @Mapping(source = "productGroup.id", target = "parentId")
    })
    ProductGroupDto productGroupToProductGroupDto(ProductGroup productGroup);

    @Mappings({
            @Mapping(source = "parentId", target = "productGroup.id")
    })
    ProductGroup productGroupDtoToProductGroup(ProductGroupDto productGroupDto);

    //ProductPrice
    @Mappings({
            @Mapping(source = "typeOfPrice", target = "typeOfPriceDto")
    })
    ProductPriceDto productPriceToProductPriceDto(ProductPrice productPrice);

    @Mappings({
            @Mapping(source = "typeOfPriceDto", target = "typeOfPrice")
    })
    ProductPrice productPriceDtoToProductPrice(ProductPriceDto productPriceDto);


    //Project
    ProjectDto projectToProjectDto(Project project);

    Project projectDtoToProject(ProjectDto projectDto);

    //Role
    RoleDto roleToRoleDto(Role role);

    Role roleDtoToRole(RoleDto role);

    //TaxSystem
    TaxSystemDto taxSystemToTaxSystemDto(TaxSystem taxSystem);

    TaxSystem taxSystemDtoToTaxSystem(TaxSystemDto taxSystemDto);

    //TypeOfContractor
    TypeOfContractorDto typeOfContractorToTypeOfContractorDto(TypeOfContractor typeOfContractor);

    TypeOfContractor typeOfContractorDtoToTypeOfContractor(TypeOfContractorDto typeOfContractorDto);

    //TypeOfPrice
    TypeOfPriceDto typeOfPriceToTypeOfPriceDto(TypeOfPrice typeOfPrice);

    TypeOfPrice typeOfPriceDtoToTypeOfPrice(TypeOfPriceDto typeOfPriceDto);

    //Unit
    UnitDto unitToUnitDto(Unit unit);

    Unit unitDtoToUnit(Unit unit);

    //Warehouse
    WarehouseDto warehouseToWarehouseDto(Warehouse warehouse);

    Warehouse warehouseDtoToWarehouse(WarehouseDto warehouseDto);
}
