package com.trade_accounting.utils;

import com.trade_accounting.models.Address;
import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contact;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.ContractorGroup;
import com.trade_accounting.models.Department;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Image;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.ProductPrice;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.Role;
import com.trade_accounting.models.Status;
import com.trade_accounting.models.Task;
import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.AddressDto;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContactDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.ProductPriceDto;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.models.dto.StatusDto;
import com.trade_accounting.models.dto.TaskCommentDto;
import com.trade_accounting.models.dto.TaskDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
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
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelDtoConverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    private ModelDtoConverter() { }

    public static ProductPriceDto convertToProductPriceDto(ProductPrice productPrice) {
        return modelMapper.map(productPrice, ProductPriceDto.class);
    }

    public static AddressDto convertToAddressDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }

    public static Address convertToAddress(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }

    public static ProductPrice convertToProductPrice(ProductPriceDto productPriceDto){
        return modelMapper.map(productPriceDto, ProductPrice.class);
    }

    public static TypeOfPriceDto convertToTypeOfPriceDto(TypeOfPrice typeOfPrice){
        return modelMapper.map(typeOfPrice, TypeOfPriceDto.class);
    }

    public static CompanyDto convertToCompanyDto(Company company) {
        CompanyDto companyDto = modelMapper.map(company, CompanyDto.class);
        companyDto.setLegalDetailDto(modelMapper.map(company.getLegalDetail(), LegalDetailDto.class));

        companyDto.getLegalDetailDto().setTypeOfContractorDto(
                modelMapper.map(company.getLegalDetail().getTypeOfContractor(), TypeOfContractorDto.class));

        return companyDto;
    }

    public static Company convertToCompany(CompanyDto dto, LegalDetail legalDetail, List<BankAccount> bankAccounts) {
        return new Company(
                dto.getId(),
                dto.getName(),
                dto.getInn(),
                dto.getSortNumber(),
                dto.getPhone(),
                dto.getFax(),
                dto.getEmail(),
                dto.getPayerVat(),
                dto.getAddress(),
                dto.getCommentToAddress(),
                dto.getLeader(),
                dto.getLeaderManagerPosition(),
                dto.getLeaderSignature(),
                dto.getChiefAccountant(),
                dto.getChiefAccountantSignature(),
                dto.getStamp(),
                legalDetail, bankAccounts);
    }

    public static ContactDto convertToContactDto(Contact contact) {
        return modelMapper.map(contact, ContactDto.class);
    }

    public static Contact convertToContact(ContactDto contactDto) {
        return modelMapper.map(contactDto, Contact.class);
    }

    public static List<Contact> convertToListOfContact(List<ContactDto> list) {
        List<Contact> contactList = new ArrayList<>();
        for (ContactDto contactDto : list) {
            contactList.add(new Contact(
                    contactDto.getId(),
                    contactDto.getFullName(),
                    contactDto.getPosition(),
                    contactDto.getPhone(),
                    contactDto.getEmail(),
                    contactDto.getComment()
            ));
        }
        return contactList;
    }



    public static EmployeeDto convertToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        if (employee.getDepartment() != null) {
            employeeDto.setDepartmentDto(modelMapper.map(employee.getDepartment(), DepartmentDto.class));
        }
        if (employee.getPosition() != null) {
            employeeDto.setPositionDto((modelMapper.map(employee.getPosition(), PositionDto.class)));
        }
        if (employee.getRoles() != null) {
            employeeDto.setRoleDto(employee.getRoles().stream()
                    .map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toSet()));
        }
        if (employee.getImage() != null) {
            employeeDto.setImageDto(modelMapper.map(employee.getImage(), ImageDto.class));
        }
        return employeeDto;
    }

    public static Employee convertToEmployeeEntity(EmployeeDto employeedto) {
        Employee employee = modelMapper.map(employeedto, Employee.class);
        if (employeedto.getDepartmentDto() != null) {
            employee.setDepartment(modelMapper.map(employeedto.getDepartmentDto(), Department.class));
        }
        if (employeedto.getPositionDto() != null) {
            employee.setPosition((modelMapper.map(employeedto.getPositionDto(), Position.class)));
        }
        if (employeedto.getRoleDto() != null) {
            employee.setRoles(employeedto.getRoleDto().stream()
                    .map(role -> modelMapper.map(role, Role.class)).collect(Collectors.toSet()));
        }
        if (employeedto.getImageDto() != null) {
            employee.setImage(modelMapper.map(employeedto.getImageDto(), Image.class));
        }
        return employee;
    }

    public static InvoiceDto convertToInvoiceDto(Invoice invoice) {

        InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);

        if (invoice.getCompany() != null) {
            invoiceDto.setCompanyDto(modelMapper.map(invoice.getCompany(), CompanyDto.class));
        }
        if (invoice.getContractor() != null) {
            invoiceDto.setContractorDto((modelMapper.map(invoice.getContractor(), ContractorDto.class)));
        }

        if (invoice.getWarehouse() != null) {
            invoiceDto.setWarehouseDto(modelMapper.map(invoice.getWarehouse(), WarehouseDto.class));
        }
        return invoiceDto;
    }

    public static ContractorDto convertToContractorDto(Contractor contractor) {

        ContractorDto contractorDto = modelMapper.map(contractor, ContractorDto.class);

        if (contractor.getContractorGroup() != null) {
            contractorDto.setContractorGroupDto(modelMapper.map(contractor.getContractorGroup(), ContractorGroupDto.class));
        }

        if (contractor.getTypeOfPrice() != null) {
            contractorDto.setTypeOfPriceDto(modelMapper.map(contractor.getTypeOfPrice(), TypeOfPriceDto.class));
        }

        if (contractor.getContact() != null) {
            contractorDto.setContactDto(modelMapper.map(contractor.getContact(), new TypeToken<List<ContactDto>>(){}.getType()));
        }
//        if (contractor.getBankAccounts() != null) {
//            contractorDto.setBankAccountDto(modelMapper.map(contractor.getBankAccounts(),  null));
//        }
        if (contractor.getLegalDetail() != null) {
            contractorDto.setLegalDetailDto(modelMapper.map(contractor.getLegalDetail(), LegalDetailDto.class));
        }

        return contractorDto;
    }

    public static LegalDetail convertToLegalDetail(LegalDetailDto dto, TypeOfContractor typeOfContractor) {
        return new LegalDetail(
                dto.getId(),
                dto.getLastName(),
                dto.getFirstName(),
                dto.getMiddleName(),
                convertToAddress(dto.getAddressDto()),
                dto.getCommentToAddress(),
                dto.getInn(),
                dto.getKpp(),
                dto.getOkpo(),
                dto.getOgrn(),
                dto.getNumberOfTheCertificate(),
                dto.getDateOfTheCertificate(),
                typeOfContractor);
    }

    public static TypeOfContractor convertToTypeOfContractor(TypeOfContractorDto dto) {
        return new TypeOfContractor(
                dto.getId(),
                dto.getName(),
                dto.getSortNumber());
    }

    public static Invoice convertToInvoice(InvoiceDto dto, TypeOfInvoice typeOfInvoice, Company company,
                                           Contractor contractor, Warehouse warehouse) {
        return new Invoice(
                dto.getId(),
                LocalDateTime.parse(dto.getDate()),
                typeOfInvoice,
                company,
                contractor,
                warehouse,
                dto.isSpend(),
                dto.getComment()
        );
    }

    public static TypeOfInvoice convertToTypeOfInvoice(TypeOfInvoice typeOfInvoice) {
        return TypeOfInvoice.valueOf(typeOfInvoice.toString());
    }

    public static Warehouse convertToWarehouse(WarehouseDto dto) {
        return new Warehouse(
                dto.getId(),
                dto.getName(),
                dto.getSortNumber(),
                dto.getAddress(),
                dto.getCommentToAddress(),
                dto.getComment()
        );
    }

    public static Contractor convertToContractor(ContractorDto dto, ContractorGroup contractorGroup,
                                                 TypeOfPrice typeOfPrice,
                                                 List<BankAccount> bankAccount,
                                                 LegalDetail legalDetail) {
        return new Contractor(
                dto.getId(),
                dto.getName(),
                dto.getSortNumber(),
                dto.getPhone(),
                dto.getFax(),
                dto.getEmail(),
                convertToAddress(dto.getAddressDto()),
                dto.getCommentToAddress(),
                dto.getComment(),
                dto.getDiscountCardNumber(),
                dto.getGeneralAccess(),
                convertToListOfContact(dto.getContactDto()),
                contractorGroup,
                typeOfPrice,
                bankAccount,
                legalDetail,
                toStatusEntity(dto.getStatusDto()),
                convertToEmployeeEntity(dto.getEmployeeDto()),
                toDepartmentEntity(dto.getDepartmentDto())
        );
    }

    public static ContractorGroup convertToContractorGroup(ContractorGroupDto dto) {
        return new ContractorGroup(
                dto.getId(),
                dto.getName(),
                dto.getSortNumber()
        );
    }

    public static TypeOfPrice convertToTypeOfPrice(TypeOfPriceDto dto) {
        return new TypeOfPrice(
                dto.getId(),
                dto.getName(),
                dto.getSortNumber()
        );
    }

    public static BankAccount convertToBankAccount(BankAccountDto dto) {
        return new BankAccount(
                dto.getId(),
                dto.getRcbic(),
                dto.getBank(),
                dto.getAddress(),
                dto.getCorrespondentAccount(),
                dto.getAccount(),
                dto.getMainAccount(),
                dto.getSortNumber()
        );
    }

    public static BankAccountDto convertToBankAccountDto(BankAccount bankAccount){
        return new BankAccountDto(
                bankAccount.getId(),
                bankAccount.getRcbic(),
                bankAccount.getBank(),
                bankAccount.getAddress(),
                bankAccount.getCorrespondentAccount(),
                bankAccount.getAccount(),
                bankAccount.getMainAccount(),
                bankAccount.getSortNumber()
        );
    }

    public static List<BankAccount> convertToListOfBankAccount(List<BankAccountDto> list) {
        List<BankAccount> bankAccountList = new ArrayList<>();
        for (BankAccountDto bankAccountDto : list) {
            bankAccountList.add(new BankAccount(
                    bankAccountDto.getId(),
                    bankAccountDto.getRcbic(),
                    bankAccountDto.getBank(),
                    bankAccountDto.getAddress(),
                    bankAccountDto.getCorrespondentAccount(),
                    bankAccountDto.getAccount(),
                    bankAccountDto.getMainAccount(),
                    bankAccountDto.getSortNumber()
            ));
        }
        return bankAccountList;
    }

    public static Department toDepartmentEntity(DepartmentDto dto) {
        var entity = new Department();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSortNumber(dto.getSortNumber());
        return entity;
    }

    public static StatusDto toStatusDTO(Status entity) {
        return new StatusDto(
                entity.getId(),
                entity.getTypeOfStatus()
        );
    }

    public static Status toStatusEntity(StatusDto dto) {
        var entity = new Status();

        entity.setId(dto.getId());
        entity.setTypeOfStatus(dto.getTypeOfStatus());

        return entity;
    }

    public static TaskDto toTaskDTO(Task entity) {
        return new TaskDto(
                entity.getId(),
                entity.getDescription(),
                entity.getTaskEmployee().getId(),
                entity.getTaskAuthor().getId(),
                entity.getCreationDateTime(),
                entity.getDeadlineDateTime(),
                entity.isCompleted(),
                0
        );
    }

    public static Task toTaskEntity(TaskDto dto) {
        var entity = new Task();

        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setCreationDateTime(dto.getCreationDateTime());
        entity.setDeadlineDateTime(dto.getDeadlineDateTime());
        entity.setCompleted(dto.isCompleted());

        return entity;
    }

    public static TaskCommentDto toTaskCommentDTO(TaskComment entity) {
        var dto = new TaskCommentDto();

        dto.setId(entity.getId());
        dto.setCommentContent(entity.getCommentContent());
        dto.setPublisherId(entity.getPublisher().getId());
        dto.setPublishedDateTime(entity.getPublishedDateTime());
        dto.setTaskId(entity.getTask().getId());

        return dto;
    }

    public static TaskComment toTaskCommentEntity(TaskCommentDto dto) {
        var entity = new TaskComment();

        entity.setId(dto.getId());
        entity.setCommentContent(dto.getCommentContent());
        entity.setPublishedDateTime(dto.getPublishedDateTime());

        return entity;
    }

    public static RetailStore convertToRetailStore(RetailStoreDto dto) {

        RetailStore retailStore = modelMapper.map(dto, RetailStore.class);

        if (dto.getOrganizationDto() != null) {
            retailStore.setOrganization(modelMapper.map(dto.getOrganizationDto(), Company.class));
        }

        if (dto.getCashiersDto() != null) {
            List<Employee> cashiers = dto.getCashiersDto().stream()
                    .map(e -> modelMapper.map(e, Employee.class))
                    .collect(Collectors.toList());
            retailStore.setCashiers(cashiers);
        }

        return retailStore;
    }

    public static Region toRegion(RegionDto regionDto) {
        return modelMapper.map(regionDto, Region.class);
    }

    public static RegionDto toRegionDto(Region region) {
        return modelMapper.map(region, RegionDto.class);
    }

    public static District toDistrict(DistrictDto districtDto) {
        return modelMapper.map(districtDto, District.class);
    }

    public static DistrictDto toDistrictDto(District district) {
        return modelMapper.map(district, DistrictDto.class);
    }

    public static City toCity(CityDto cytyDto) {
        return modelMapper.map(cytyDto, City.class);
    }

    public static CityDto toCityDto(City city) {
        return modelMapper.map(city, CityDto.class);
    }

    public static Street toStreet(StreetDto streetDto) {
        return modelMapper.map(streetDto, Street.class);
    }

    public static StreetDto toStreetDto(Street street) {
        return modelMapper.map(street, StreetDto.class);
    }

    public static FiasAddressModelDto toFiasAddressModelDto(FiasAddressModel model) {
        return modelMapper.map(model, FiasAddressModelDto.class);
    }

    public static FiasAddressModel toFiasAddressModel(FiasAddressModelDto modelDto) {
        return modelMapper.map(modelDto, FiasAddressModel.class);
    }
}
