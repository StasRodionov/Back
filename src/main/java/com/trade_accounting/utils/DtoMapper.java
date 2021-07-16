package com.trade_accounting.utils;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.AcceptanceProduction;
import com.trade_accounting.models.AccessParameters;
import com.trade_accounting.models.Address;
import com.trade_accounting.models.AgentReports;
import com.trade_accounting.models.AttributeOfCalculationObject;
import com.trade_accounting.models.BalanceAdjustment;
import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contact;
import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.ContractorGroup;
import com.trade_accounting.models.ContractorStatus;
import com.trade_accounting.models.Correction;
import com.trade_accounting.models.CorrectionProduct;
import com.trade_accounting.models.Currency;
import com.trade_accounting.models.Department;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Image;
import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.Inventarization;
import com.trade_accounting.models.InventarizationProduct;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.Movement;
import com.trade_accounting.models.MovementProduct;
import com.trade_accounting.models.Payment;
import com.trade_accounting.models.Payouts;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.PriceList;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.ProductGroup;
import com.trade_accounting.models.ProductPrice;
import com.trade_accounting.models.Production;
import com.trade_accounting.models.Project;
import com.trade_accounting.models.Remain;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.ReturnToSupplier;
import com.trade_accounting.models.Role;
import com.trade_accounting.models.SupplierAccount;
import com.trade_accounting.models.Task;
import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.TaxSystem;
import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.TechnicalCardGroup;
import com.trade_accounting.models.TechnicalCardProduction;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.Unit;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.AcceptanceDto;
import com.trade_accounting.models.dto.AcceptanceProductionDto;
import com.trade_accounting.models.dto.AccessParametersDto;
import com.trade_accounting.models.dto.AddressDto;
import com.trade_accounting.models.dto.AgentReportsDto;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.models.dto.BalanceAdjustmentDto;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContactDto;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.models.dto.ContractorStatusDto;
import com.trade_accounting.models.dto.CorrectionDto;
import com.trade_accounting.models.dto.CorrectionProductDto;
import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.InventarizationDto;
import com.trade_accounting.models.dto.InventarizationProductDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.models.dto.MovementDto;
import com.trade_accounting.models.dto.MovementProductDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.PriceListDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.models.dto.ProductPriceDto;
import com.trade_accounting.models.dto.ProductionDto;
import com.trade_accounting.models.dto.ProjectDto;
import com.trade_accounting.models.dto.RemainDto;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.models.dto.ReturnToSupplierDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.models.dto.TaskCommentDto;
import com.trade_accounting.models.dto.TaskDto;
import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.models.dto.TechnicalCardDto;
import com.trade_accounting.models.dto.TechnicalCardGroupDto;
import com.trade_accounting.models.dto.TechnicalCardProductionDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.models.dto.fias.CityDto;
import com.trade_accounting.models.dto.fias.DistrictDto;
import com.trade_accounting.models.dto.fias.FiasAddressModelDto;
import com.trade_accounting.models.dto.fias.RegionDto;
import com.trade_accounting.models.dto.fias.StreetDto;
import com.trade_accounting.models.fias.City;
import com.trade_accounting.models.fias.District;
import com.trade_accounting.models.fias.FiasAddressModel;
import com.trade_accounting.models.fias.Region;
import com.trade_accounting.models.fias.Street;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import lombok.SneakyThrows;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class DtoMapper {

    private static final String UPLOAD_DIR = "images";

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Autowired
    public final void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public final void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    //AttributeOfCalculationObjectDto
    public abstract AttributeOfCalculationObjectDto
    attributeOfCalculationObjectToAttributeOfCalculationObjectDto(
            AttributeOfCalculationObject attributeOfCalculationObject
    );

    public abstract AttributeOfCalculationObject
    attributeOfCalculationObjectDtoToAttributeOfCalculationObject(
            AttributeOfCalculationObjectDto attributeOfCalculationObjectDto
    );

    //Production
    public abstract ProductionDto productionToProductionDto(Production production);

    public abstract Production productionDtoToProduction(ProductionDto productionDto);

    //Remain
    @Mappings({
            @Mapping(source = "unit.id", target = "unitDtoId")
    })
    public abstract RemainDto remainToRemainDto(Remain remain);

    @Mappings({
            @Mapping(source = "unitDtoId", target = "unit.id")
    })
    public abstract Remain remainDtoToRemain(RemainDto remainDto);

    //AccessParameters
    public AccessParametersDto accessParametersToAccessParametersDto(AccessParameters accessParameters) {
        if (accessParameters == null) {
            return null;
        } else {
            return new AccessParametersDto(
                    accessParameters.getId(),
                    accessParameters.getGeneralAccess(),
                    accessParameters.getEmployee().getId(),
                    accessParameters.getDepartment().getId()
            );
        }
    }

    public AccessParameters accessParametersDtoToAccessParameters(AccessParametersDto accessParametersDto) {
        if (accessParametersDto == null) {
            return null;
        }
        return AccessParameters.builder().id(accessParametersDto.getId()).generalAccess(accessParametersDto.getGeneralAccess())
                .employee(employeeDtoToEmployee(employeeRepository.getById(accessParametersDto.getEmployeeId())))
                .department(departmentDtoToDepartment(departmentRepository.getById(accessParametersDto.getDepartmentId()))).build();
    }

    // Acceptance
    @Mappings({
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "project.id", target = "projectId"),
            @Mapping(source = "warehouse.id", target = "warehouseId"),
            @Mapping(source = "contract.id", target = "contractId"),
    })
    public abstract AcceptanceDto acceptanceToAcceptanceDto(Acceptance acceptance);

    public abstract Acceptance acceptanceDtoToAcceptance(AcceptanceDto acceptance);

    // Address
    public abstract AddressDto addressToAddressDto(Address address);

    public abstract Address addressDtoToAddress(AddressDto address);

    //AgentReports
    public abstract AgentReportsDto agentReportsToAgentReportsDto(AgentReports agentReports);

    public abstract AgentReports agentReportsDtoToAgentReports(AgentReportsDto agentReportsDto);

    //BankAccount
    public abstract BankAccountDto bankAccountToBankAccountDto(BankAccount bankAccount);

    public abstract BankAccount bankAccountDtoToBankAccount(BankAccountDto bankAccountDto);

    public abstract List<BankAccount> bankAccountDtoListToBankAccountList(List<BankAccountDto> bankAccountDtoList);

    public abstract List<BankAccountDto> bankAccountListToBankAccountDtoList(List<BankAccount> bankAccountList);

    //Company
    @Mappings({
            @Mapping(source = "address.id", target = "addressId"),
            @Mapping(source = "legalDetail.id", target = "legalDetailDtoId")
    })
    public abstract CompanyDto companyToCompanyDto(Company company);

    @AfterMapping
    public void listBankAccountsIdToListBankAccountDtoIds(Company company, @MappingTarget CompanyDto companyDto) {
        if (company.getBankAccounts() == null) {
            companyDto.setBankAccountDtoIds(null);
        } else {
            List<Long> bankAccountDtoIds = company.getBankAccounts().stream()
                    .map(o -> o.getId()).collect(Collectors.toList());
            companyDto.setBankAccountDtoIds(bankAccountDtoIds);
        }
    }

    @Mappings({
            @Mapping(source = "addressId", target = "address.id"),
            @Mapping(source = "legalDetailDtoId", target = "legalDetail.id")
    })
    public abstract Company companyDtoToCompany(CompanyDto companyDto);

    @AfterMapping
    public void listBankAccountsDtoIdsToListBankAccount(CompanyDto companyDto, @MappingTarget Company company, @Context BankAccountRepository bankAccountRepository) {
        if (companyDto.getBankAccountDtoIds() == null) {
            company.setBankAccounts(null);
        } else {
            List<BankAccount> bankAccounts = companyDto.getBankAccountDtoIds()
                    .stream()
                    .map(id -> bankAccountRepository.getOne(id))
                    .collect(Collectors.toList());
            company.setBankAccounts(bankAccounts);
        }
    }

    //Contact
    public abstract ContactDto contactToContactDto(Contact contact);

    public abstract Contact contactDtoToContact(ContactDto contactDto);

    public abstract List<Contact> contactDtoListToContactList(List<ContactDto> contactDtoList);

    public abstract List<ContactDto> contactListToContactDtoList(List<Contact> contactList);

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

    public abstract List<ContractDto> toContractDtoList(List<Contract> contracts);

    //Contractor
    @Mappings({
            @Mapping(source = "contractorGroup", target = "contractorGroupDto"),
            @Mapping(source = "typeOfPrice", target = "typeOfPriceDto"),
            @Mapping(source = "legalDetail", target = "legalDetailDto"),
            @Mapping(source = "bankAccounts", target = "bankAccountDto"),
            @Mapping(source = "address", target = "addressDto"),
            @Mapping(source = "contact", target = "contactDto"),
            @Mapping(source = "contractorStatus", target = "contractorStatusDto"),
            @Mapping(source = "accessParameters", target = "accessParametersDto"),
    })
    public abstract ContractorDto contractorToContractorDto(Contractor contractor);

    @Mappings({
            @Mapping(source = "contractorGroupDto", target = "contractorGroup"),
            @Mapping(source = "typeOfPriceDto", target = "typeOfPrice"),
            @Mapping(source = "bankAccountDto", target = "bankAccounts"),
            @Mapping(source = "legalDetailDto", target = "legalDetail"),
            @Mapping(source = "addressDto", target = "address"),
            @Mapping(source = "contactDto", target = "contact"),
            @Mapping(source = "contractorStatusDto", target = "contractorStatus"),
            @Mapping(source = "accessParametersDto", target = "accessParameters"),
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
        if (image == null) {
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
        if (imageDtos == null) {
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
        if (Files.exists(path)) {
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

    // SupplierAccounts
    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contract.id", target = "contractId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "warehouse.id", target = "warehouseId"),
    })
    public abstract SupplierAccountDto supplierAccountToSupplierAccountDto(SupplierAccount SupplierAccount);

    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractId", target = "contract.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "warehouseId", target = "warehouse.id"),
    })
    public abstract SupplierAccount supplierAccountDtoToSupplierAccount(SupplierAccountDto SupplierAccountDto);

    //LegalDetail
    @Mappings({
            @Mapping(source = "typeOfContractor.id", target = "typeOfContractorDtoId"),
            @Mapping(source = "address.id", target = "addressDtoId")
    })
    public abstract LegalDetailDto legalDetailToLegalDetailDto(LegalDetail legalDetail);

    @Mappings({
            @Mapping(source = "typeOfContractorDtoId", target = "typeOfContractor.id"),
            @Mapping(source = "addressDtoId", target = "address.id")
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

    //PriceList
    public abstract PriceListDto priceListToPriceListDto(PriceList priceList);

    public abstract PriceList priceListDtoToPriceList(PriceListDto priceListDto);

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

    //ContractorStatus
    public abstract ContractorStatusDto statusToStatusDto(ContractorStatus contractorStatus);

    public abstract ContractorStatus statusDtoToStatus(ContractorStatusDto status);

    //Task *Test
    @Mappings({
            @Mapping(source = "taskEmployee.id", target = "employeeId"),
            @Mapping(source = "taskAuthor.id", target = "taskAuthorId")
    })
    public abstract TaskDto taskToTaskDto(Task task);

    @Mappings({
            @Mapping(source = "employeeId", target = "taskEmployee.id"),
            @Mapping(source = "taskAuthorId", target = "taskAuthor.id"),
            @Mapping(target = "creationDateTime", ignore = true),
            @Mapping(target = "deadlineDateTime", ignore = true)
    })
    public abstract Task taskDtoToTask(TaskDto taskDto);

    //TaskComment *Test
    @Mappings({
            @Mapping(source = "publisher.id", target = "publisherId"),
            @Mapping(source = "task.id", target = "taskId"),
    })
    public abstract TaskCommentDto taskCommentToTaskCommentDto(TaskComment taskComment);

    @Mappings({
            @Mapping(source = "publisherId", target = "publisher.id"),
            @Mapping(source = "taskId", target = "task.id")
    })
    public abstract TaskComment taskCommentDtoToTaskComment(TaskCommentDto taskCommentDto);

    //TaxSystem
    public abstract TaxSystemDto taxSystemToTaxSystemDto(TaxSystem taxSystem);

    public abstract TaxSystem taxSystemDtoToTaxSystem(TaxSystemDto taxSystemDto);

    //TechnicalCard
    @Mappings({
            @Mapping(source = "technicalCardGroup", target = "technicalCardGroupDto"),
            @Mapping(source = "finalProduction", target = "finalProductionDto"),
            @Mapping(source = "materials", target = "materialsDto"),
    })
    public abstract TechnicalCardDto technicalCardToTechnicalCardDto(TechnicalCard technicalCard);

    @Mappings({
            @Mapping(source = "technicalCardGroupDto", target = "technicalCardGroup"),
            @Mapping(source = "finalProductionDto", target = "finalProduction"),
            @Mapping(source = "materialsDto", target = "materials"),
    })
    public abstract TechnicalCard technicalCardDtoToTechnicalCard(TechnicalCardDto technicalCardDto);

    //TechnicalCardGroup
    public abstract TechnicalCardGroupDto technicalCardGroupToTechnicalCardGroupDto(TechnicalCardGroup technicalCardGroup);

    public abstract TechnicalCardGroup technicalCardGroupDtoToTechnicalCardGroup(TechnicalCardGroupDto technicalCardGroupDto);

    //TechnicalCardProduction
    @Mappings({
            @Mapping(source = "product.id", target = "productId"),
    })
    public abstract TechnicalCardProductionDto technicalCardProductionToTechnicalCardProductionDto(TechnicalCardProduction technicalCardProduction);

    public abstract TechnicalCardProduction technicalCardProductionDtoToTechnicalCardProduction(TechnicalCardProductionDto technicalCardProductionDto);

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

    //RetailStore
    @Mappings({
            @Mapping(source = "organization", target = "organizationDto"),
            @Mapping(source = "cashiers", target = "cashiersDto"),
    })
    public abstract RetailStoreDto retailStoreToRetailStoreDto(RetailStore retailStore);

    @Mappings({
            @Mapping(source = "organizationDto", target = "organization"),
            @Mapping(source = "cashiersDto", target = "cashiers"),
    })
    public abstract RetailStore toRetailStore(RetailStoreDto retailStoreDto);

    @Mappings({
            @Mapping(source = "districtDtos", target = "districts")
    })
    public abstract Region toRegion(RegionDto regionDto);

/*
    public abstract RegionDto toRegionDto(Region region);

    public abstract District toDistrict(DistrictDto districtDto);

    public abstract DistrictDto toDistrictDto(District district);

    public abstract City toCity(CityDto cityDto);

    public abstract CityDto toCityDto(City city);

    public abstract Street toStreet(StreetDto streetDto);

    public abstract StreetDto toStreetDto(Street street);

 */


    @Mappings({
            @Mapping(source = "districts", target = "districtDtos")
    })
    public abstract RegionDto toRegionDto(Region region);

    @Mappings({
            @Mapping(source = "regionDto", target = "region"),
            @Mapping(source = "citiesDto", target = "cities")
    })
    public abstract District toDistrict(DistrictDto districtDto);

    @Mappings({
            @Mapping(source = "region", target = "regionDto"),
            @Mapping(source = "cities", target = "citiesDto")
    })
    public abstract DistrictDto toDistrictDto(District district);

    @Mappings({
            @Mapping(source = "districtDto", target = "district"),
            @Mapping(source = "streetsDto", target = "streets")
    })
    public abstract City toCity(CityDto cityDto);

    @Mappings({
            @Mapping(source = "district", target = "districtDto"),
            @Mapping(source = "streets", target = "streetsDto")
    })
    public abstract CityDto toCityDto(City city);

    @Mappings({
            @Mapping(source = "cityDto", target = "city")
    })
    public abstract Street toStreet(StreetDto streetDto);

    @Mappings({
            @Mapping(source = "city", target = "cityDto")
    })
    public abstract StreetDto toStreetDto(Street street);

    public abstract FiasAddressModel toFiasAddressModel(FiasAddressModelDto dto);

    public abstract FiasAddressModelDto toFiasAddressModelDto(FiasAddressModel model);

    //    Correction
    public CorrectionDto toCorrectionDto(Correction correction) {
        CorrectionDto correctionDto = new CorrectionDto();
        if (correction == null) {
            return null;
        } else {
            correctionDto.setId(correction.getId());
            correctionDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(correction.getDate()));
            correctionDto.setIsSent(correction.getIsSent());
            correctionDto.setIsPrint(correction.getIsPrint());
            correctionDto.setWriteOffProduct(correction.getWriteOffProduct());
            correctionDto.setComment(correction.getComment());

            Warehouse warehouse = correction.getWarehouse();
            if (warehouse == null) {
                return null;
            } else {
                correctionDto.setWarehouseId(warehouse.getId());

                Company company = correction.getCompany();
                if (company == null) {
                    return null;
                } else {
                    correctionDto.setCompanyId(company.getId());

                    List<Long> correctionProductIds = correction.getCorrectionProducts().stream()
                            .map(CorrectionProduct::getId)
                            .collect(Collectors.toList());
                    correctionDto.setCorrectionProductIds(correctionProductIds);
                    return correctionDto;
                }
            }
        }
    }

    @Mapping(target = "date", ignore = true)
    public abstract Correction toCorrection(CorrectionDto correctionDto);

    //    CorrectionProduct
    @Mappings({
            @Mapping(source = "product.id", target = "productId")
    })
    public abstract CorrectionProductDto toCorrectionProductDto(CorrectionProduct correction);

    public abstract CorrectionProduct toCorrectionProduct(CorrectionProductDto correctionDto);

    // ReturnToSupplier
    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contract.id", target = "contractId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "warehouse.id", target = "warehouseId"),
    })
    public abstract ReturnToSupplierDto returnToSupplierToReturnToSupplierDto(ReturnToSupplier returnToSupplier);

    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractId", target = "contract.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "warehouseId", target = "warehouse.id"),
    })
    public abstract ReturnToSupplier returnToSupplierDtoToReturnToSupplier(ReturnToSupplierDto returnToSupplierDto);

    //Inventarization
    public InventarizationDto toInventarizationDto(Inventarization inventarization) {

        InventarizationDto inventarizationDto = new InventarizationDto();

        if (inventarization == null) {
            return null;
        } else {
            inventarizationDto.setId(inventarization.getId());
            inventarizationDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(inventarization.getDate()));
            inventarizationDto.setStatus(inventarization.getStatus());
            inventarizationDto.setComment(inventarization.getComment());

            Warehouse warehouse = inventarization.getWarehouse();
            if (warehouse == null) {
                return null;
            } else {
                inventarizationDto.setWarehouseId(warehouse.getId());

                Company company = inventarization.getCompany();
                if (company == null) {
                    return null;
                } else {
                    inventarizationDto.setCompanyId(company.getId());

                    List<Long> listIds = inventarization.getInventarizationProducts().stream()
                            .map(InventarizationProduct::getId)
                            .collect(Collectors.toList());
                    inventarizationDto.setInventarizationProductIds(listIds);

                    return inventarizationDto;
                }
            }
        }
    }

    @Mapping(target = "date", ignore = true)
    public abstract Inventarization toInventarization(InventarizationDto inventarizationDto);

    @Mapping(source = "product.id", target = "productId")
    public abstract InventarizationProductDto toInventarizationProductDto(InventarizationProduct inventarizationProduct);

    public abstract InventarizationProduct toInventarizationProduct(InventarizationProductDto inventarizationProductDto);

    //    BalanceAdjustment
    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
    })
    public abstract BalanceAdjustmentDto balanceAdjustmentToBalanceAdjustmentDto(BalanceAdjustment balanceAdjustment);

    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
    })
    public abstract BalanceAdjustment balanceAdjustmentDtoToBalanceAdjustment(BalanceAdjustmentDto balanceAdjustmentDto);


    //    Movement
    public MovementDto toMovementDto(Movement movement) {
        MovementDto movementDto = new MovementDto();
        if (movement == null) {
            return null;
        } else {
            movementDto.setId(movement.getId());
            movementDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(movement.getDate()));
            movementDto.setIsSent(movement.getIsSent());
            movementDto.setIsPrint(movement.getIsPrint());
            movementDto.setComment(movement.getComment());

            Warehouse warehouseFrom = movement.getWarehouseFrom();
            Warehouse warehouseTo = movement.getWarehouseTo();
            if (warehouseFrom == null) {
                return null;
            } else {
                movementDto.setWarehouseFromId(warehouseFrom.getId());
                if (warehouseTo == null){
                    return null;
                } else {
                    movementDto.setWarehouseToId(warehouseTo.getId());

                    Company company = movement.getCompany();
                    if (company == null){
                        return null;
                    } else {
                        movementDto.setCompanyId(company.getId());

                        List<Long> movementProductIds = movement.getMovementProducts().stream()
                                .map(MovementProduct::getId)
                                .collect(Collectors.toList());

                        movementDto.setMovementProductsIds(movementProductIds);
                        return movementDto;
                    }
                }
            }
        }
    }

    @Mapping(target = "date", ignore = true)
    public abstract Movement toMovement(MovementDto movementDto);

    //    MovementProduct
    @Mappings({
            @Mapping(source = "product.id", target = "productId")
    })
    public abstract MovementProductDto toMovementProductDto(MovementProduct movement);

    public abstract MovementProduct toMovementProduct(MovementProductDto movementDto);

    // AcceptanceProductionService
    public AcceptanceProductionDto toAcceptanceProductionDto(AcceptanceProduction acceptanceProduction) {
        AcceptanceProductionDto acceptanceProductionDto = new AcceptanceProductionDto();
        if (acceptanceProduction == null) {
            return null;
        } else {
            acceptanceProductionDto.setId(acceptanceProduction.getId());
            acceptanceProductionDto.setAmount(acceptanceProduction.getAmount());

            Product product = acceptanceProduction.getProduct();
            if (product == null) {
                return null;
            } else {
                acceptanceProductionDto.setProductId(product.getId());
                return acceptanceProductionDto;
            }
        }
    }

    public AcceptanceProduction acceptanceProductionDtoToAcceptanceProduction(AcceptanceProductionDto acceptanceProductionDto) {
        AcceptanceProduction acceptanceProduction = new AcceptanceProduction();
        if (acceptanceProductionDto == null) {
            return null;
        }

        acceptanceProduction.setId(acceptanceProductionDto.getId());
        acceptanceProduction.setAmount(acceptanceProductionDto.getAmount());

        return acceptanceProduction;
    }

    /**
     * @return InternalOrder
     */
    @Mappings({
            @Mapping(source = "companyId", target = "company.id")
    })
    public abstract InternalOrder internalOrderDtoToInternalOrder(InternalOrderDto internalOrderDto);

    /**
     * @return InternalOrderDto
     */
    public InternalOrderDto internalOrderToInternalOrderDto(InternalOrder internalOrder) {
        InternalOrderDto internalOrderDto = new InternalOrderDto();
        if (internalOrder == null) {
            return null;
        } else {
            internalOrderDto.setId(internalOrder.getId());
            internalOrderDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(internalOrder.getDate()));
            internalOrderDto.setIsSent(internalOrder.getIsSent());
            internalOrderDto.setIsPrint(internalOrder.getIsPrint());
            internalOrderDto.setComment(internalOrder.getComment());
            internalOrderDto.setInternalOrderProductsIds(
                    internalOrder.getInternalOrderProducts().stream()
                            .map(InternalOrderProduct::getId)
                            .collect(Collectors.toList())
            );

            if (internalOrder.getCompany() == null) {
                return null;
            } else {
                internalOrderDto.setCompanyId(internalOrder.getCompany().getId());
                return internalOrderDto;
            }
        }
    }

    /**
     * @return InternalOrderProducts
     */
    @Mapping(source = "productId", target = "product.id")
    public abstract InternalOrderProduct internalOrderProductsDtoToInternalOrderProducts(InternalOrderProductsDto internalOrderProductsDto);

    /**
     * @return InternalOrderProductsDto
     */
    @Mapping(source = "product.id", target = "productId")
    public abstract InternalOrderProductsDto internalOrderProductsToInternalOrderProductsDto(InternalOrderProduct internalOrderProduct);

    //Payouts
    @Mappings({
            @Mapping(source = "retailStore.id", target = "retailStoreId"),
            @Mapping(source = "company.id", target = "companyId")
    })
    public abstract PayoutsDto payoutsToPayoutsDto(Payouts payouts);

    @Mappings({
            @Mapping(source = "retailStoreId", target = "retailStore.id"),
            @Mapping(source = "companyId", target = "company.id")
    })
    public abstract Payouts payoutsDtoToPayouts(PayoutsDto payoutsDto);

}


//abstract class CustomDtoMapper extends DtoMapper {
//
//    @Override
//    public CompanyDto companyToCompanyDto(Company company) {
//        CompanyDto companyDto = new CompanyDto();
//        if (company.getBankAccounts() == null) {
//            companyDto.setBankAccountDtoIds(null);
//        } else {
//            List<Long> bankAccountDtoIds = company.getBankAccounts().stream()
//                    .map(o -> o.getId()).collect(Collectors.toList());
//            companyDto.setBankAccountDtoIds(bankAccountDtoIds);
//        }
//        return companyDto;
//    }

//}
