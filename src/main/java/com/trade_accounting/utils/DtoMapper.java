package com.trade_accounting.utils;

import com.trade_accounting.models.Address;
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
import com.trade_accounting.models.Task;
import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.TaxSystem;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.Unit;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.AddressDto;
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
import com.trade_accounting.models.dto.TaskCommentDto;
import com.trade_accounting.models.dto.TaskDto;
import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.models.dto.WarehouseDto;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DtoMapper {

    private static final String UPLOAD_DIR = "images";

    //AttributeOfCalculationObjectDto
    public abstract AttributeOfCalculationObjectDto
    attributeOfCalculationObjectToAttributeOfCalculationObjectDto(
            AttributeOfCalculationObject attributeOfCalculationObject
    );

    public abstract AttributeOfCalculationObject
    attributeOfCalculationObjectDtoToAttributeOfCalculationObject(
            AttributeOfCalculationObjectDto attributeOfCalculationObjectDto
    );

    // Address
    public abstract AddressDto addressToAddressDto(Address address);

    public abstract Address addressDtoToAddress(AddressDto address);

    //BankAccount
    public abstract BankAccountDto bankAccountToBankAccountDto(BankAccount bankAccount);

    public abstract BankAccount bankAccountDtoToBankAccount(BankAccountDto bankAccountDto);

    //Company
    @Mappings({
            @Mapping(source = "bankAccounts", target = "bankAccountDto"),
            @Mapping(source = "legalDetail", target = "legalDetailDto")
    })
    public abstract CompanyDto companyToCompanyDto(Company company);

    @Mappings({
            @Mapping(source = "bankAccountDto", target = "bankAccounts"),
            @Mapping(source = "legalDetailDto", target = "legalDetail")
    })
    public abstract Company companyDtoToCompany(CompanyDto companyDto);

    //Contract
    @Mappings({
            @Mapping(source = "company", target = "companyDto"),
            @Mapping(source = "bankAccount", target = "bankAccountDto"),
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "legalDetail", target = "legalDetailDto")
    })
    public abstract ContractDto contractToContractDto(Contract contract);

    @Mappings({
            @Mapping(source = "companyDto", target = "company"),
            @Mapping(source = "bankAccountDto", target = "bankAccount"),
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "legalDetailDto", target = "legalDetail")
    })
    public abstract Contract contractDtoToContract(ContractDto contractDto);

    public abstract List<ContractDto> toContractDtoList (List<Contract> contracts);

    //Contractor
    @Mappings({
            @Mapping(source = "contractorGroup", target = "contractorGroupDto"),
            @Mapping(source = "typeOfContractor", target = "typeOfContractorDto"),
            @Mapping(source = "typeOfPrice", target = "typeOfPriceDto"),
            @Mapping(source = "legalDetail", target = "legalDetailDto"),
            @Mapping(source = "bankAccounts", target = "bankAccountDto"),
            @Mapping(source = "address", target = "addressDto")
    })
    public abstract ContractorDto contractorToContractorDto(Contractor contractor);

    @Mappings({
            @Mapping(source = "contractorGroupDto", target = "contractorGroup"),
            @Mapping(source = "typeOfContractorDto", target = "typeOfContractor"),
            @Mapping(source = "typeOfPriceDto", target = "typeOfPrice"),
            @Mapping(source = "bankAccountDto", target = "bankAccounts"),
            @Mapping(source = "legalDetailDto", target = "legalDetail"),
            @Mapping(source = "addressDto", target = "address")

    })
    public abstract Contractor contractorDtoToContractor(ContractorDto contractorDto);

    //ContractorGroup
    public abstract ContractorGroupDto contractorGroupToContractorGroupDto(ContractorGroup contractorGroup);

    public abstract ContractorGroup contractorGroupDtoToContractorGroup(ContractorGroupDto contractorGroupDto);

    //Currency
    public abstract CurrencyDto currencyToCurrencyDto(Currency currency);

    public abstract Currency currencyDtoToCurrency(CurrencyDto currencyDto);

    //Department
    public abstract DepartmentDto departmentToDepartmentDto(Department department);

    public abstract Department departmentDtoToDepartment(DepartmentDto department);

    // Employee
    @Mappings({
            @Mapping(source = "department", target = "departmentDto"),
            @Mapping(source = "position", target = "positionDto"),
            @Mapping(source = "roles", target = "roleDto"),
            @Mapping(source = "image", target = "imageDto")
    })
    public abstract EmployeeDto employeeToEmployeeDto(Employee emp);

    @Mappings({
            @Mapping(source = "departmentDto", target = "department"),
            @Mapping(source = "positionDto", target = "position"),
            @Mapping(source = "roleDto", target = "roles"),
//            @Mapping(source = "imageDto", target = "image"),
            @Mapping(target = "authorities", ignore = true)
    })
    public abstract Employee employeeDtoToEmployee(EmployeeDto emp);

    //Image
    public ImageDto imageToImageDto(Image image) {
        if (image == null){
            return null;
        }
        return ImageDto.builder()
                .id(image.getId())
                .content(downloadImage(image.getImageUrl()))
                .sortNumber(image.getSortNumber())
                .build();
    }

    public Image imageDtoToImage(ImageDto imageDto, String imageDir) {
        String url = uploadImage(imageDto.getContent(), imageDir,
                    new Date().getTime() + imageDto.getFileExtension());
        return Image.builder()
                .id(imageDto.getId())
                .imageUrl(url)
                .build();
    }

    public List<Image> toImage(Collection<ImageDto> imageDtos, String imageDir) {
        if ( imageDtos == null ) {
            return new ArrayList<>();
        }
        List<Image> list = new ArrayList<>(imageDtos.size());
        for (ImageDto imageDto : imageDtos) {
            list.add(imageDtoToImage(imageDto, imageDir));
        }
        return list;
    }

    public abstract List<ImageDto> toImageDto(Collection<Image> images);

    @SneakyThrows
    private String uploadImage(byte[] content, String imageDir, String fileName) {
        Path path = Paths.get(UPLOAD_DIR + File.separator + imageDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        path = Paths.get(path.toString() + File.separator + fileName);
        if (content != null) {
            Files.write(path, content);
        }
        return path.toString();
    }

    @SneakyThrows
    private byte[] downloadImage(String url) {
        Path path = Paths.get(url);
        if (Files.exists(path)){
            return Files.readAllBytes(path);
        } else {
            return new byte[0];
        }
    }

    //Invoice
    @Mappings({
            @Mapping(source = "company", target = "companyDto"),
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "warehouse", target = "warehouseDto"),
    })
    public abstract InvoiceDto invoiceToInvoiceDto(Invoice invoice);

    @Mappings({
            @Mapping(source = "companyDto", target = "company"),
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "warehouseDto", target = "warehouse"),
    })
    public abstract Invoice invoiceDtoToInvoice(InvoiceDto emp);

    //InvoiceProduct
    @Mappings({
            @Mapping(source = "invoice", target = "invoiceDto"),
            @Mapping(source = "product", target = "productDto")
    })
    public abstract InvoiceProductDto invoiceProductToInvoiceProductDto(InvoiceProduct invoiceProduct);

    @Mappings({
            @Mapping(source = "invoiceDto", target = "invoice"),
            @Mapping(source = "productDto", target = "product")
    })
    public abstract InvoiceProduct invoiceProductDtoToInvoiceProduct(InvoiceProductDto invoiceProductDto);

    //LegalDetail
    @Mappings({
            @Mapping(source = "typeOfContractor", target = "typeOfContractorDto")
    })
    public abstract LegalDetailDto legalDetailToLegalDetailDto(LegalDetail legalDetail);

    @Mappings({
            @Mapping(source = "typeOfContractorDto", target = "typeOfContractor")
    })
    public abstract LegalDetail legalDetailDtoToLegalDetail(LegalDetailDto legalDetailDto);

    //Payment
    @Mappings({
            @Mapping(source = "company", target = "companyDto"),
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "contract", target = "contractDto"),
            @Mapping(source = "project", target = "projectDto")
    })
    public abstract PaymentDto paymentToPaymentDto(Payment payment);

    @Mappings({
            @Mapping(source = "companyDto", target = "company"),
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "contractDto", target = "contract"),
            @Mapping(source = "projectDto", target = "project")
    })
    public abstract Payment paymentDtoToPayment(PaymentDto paymentDto);

    //Position
    public abstract PositionDto positionToPositionDto(Position position);

    public abstract Position positionDtoToPosition(PositionDto position);

    //Product
    @Mappings({
            @Mapping(source = "unit", target = "unitDto"),
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "productPrices", target = "productPriceDtos"),
            @Mapping(source = "taxSystem", target = "taxSystemDto"),
            @Mapping(source = "productGroup", target = "productGroupDto"),
            @Mapping(source = "attributeOfCalculationObject", target = "attributeOfCalculationObjectDto")
    })
    public abstract ProductDto productToProductDto(Product product);

    @Mappings({
            @Mapping(source = "unitDto", target = "unit"),
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "productPriceDtos", target = "productPrices"),
            @Mapping(source = "taxSystemDto", target = "taxSystem"),
            @Mapping(source = "productGroupDto", target = "productGroup"),
            @Mapping(source = "attributeOfCalculationObjectDto", target = "attributeOfCalculationObject")
    })
    public abstract Product productDtoToProduct(ProductDto productDto);

    public abstract List<ProductDto> toProductDto(Collection<Product> products);

    //ProductGroup
    //На момент написания не известно работает или нет из-за рекурсии
    @Mappings({
            @Mapping(source = "productGroup.id", target = "parentId")
    })
    public abstract ProductGroupDto productGroupToProductGroupDto(ProductGroup productGroup);

    @Mappings({
            @Mapping(source = "parentId", target = "productGroup.id")
    })
    public abstract ProductGroup productGroupDtoToProductGroup(ProductGroupDto productGroupDto);

    //ProductPrice
    @Mappings({
            @Mapping(source = "typeOfPrice", target = "typeOfPriceDto")
    })
    public abstract ProductPriceDto productPriceToProductPriceDto(ProductPrice productPrice);

    @Mappings({
            @Mapping(source = "typeOfPriceDto", target = "typeOfPrice")
    })
    public abstract ProductPrice productPriceDtoToProductPrice(ProductPriceDto productPriceDto);

    //Project
    public abstract ProjectDto projectToProjectDto(Project project);

    public abstract Project projectDtoToProject(ProjectDto projectDto);

    //Role
    public abstract RoleDto roleToRoleDto(Role role);

    public abstract Role roleDtoToRole(RoleDto role);

    //Task
    public abstract TaskDto taskToTaskDto(Task task);

    public abstract Task taskDtoToTask(TaskDto taskDto);

    //TaskComment
    public abstract TaskCommentDto taskCommentToTaskCommentDto(TaskComment taskComment);

    public abstract TaskComment taskCommentDtoToTaskComment(TaskCommentDto taskCommentDto);

    //TaxSystem
    public abstract TaxSystemDto taxSystemToTaxSystemDto(TaxSystem taxSystem);

    public abstract TaxSystem taxSystemDtoToTaxSystem(TaxSystemDto taxSystemDto);

    //TypeOfContractor
    public abstract TypeOfContractorDto typeOfContractorToTypeOfContractorDto(TypeOfContractor typeOfContractor);

    public abstract TypeOfContractor typeOfContractorDtoToTypeOfContractor(TypeOfContractorDto typeOfContractorDto);

    //TypeOfPrice
    public abstract TypeOfPriceDto typeOfPriceToTypeOfPriceDto(TypeOfPrice typeOfPrice);

    public abstract TypeOfPrice typeOfPriceDtoToTypeOfPrice(TypeOfPriceDto typeOfPriceDto);

    //Unit
    public abstract UnitDto unitToUnitDto(Unit unit);

    public abstract Unit unitDtoToUnit(UnitDto unitDto);

    //Warehouse
    public abstract WarehouseDto warehouseToWarehouseDto(Warehouse warehouse);

    public abstract Warehouse warehouseDtoToWarehouse(WarehouseDto warehouseDto);
}
