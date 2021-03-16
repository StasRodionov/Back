package com.trade_accounting.utils;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Department;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Image;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.Payment;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.Role;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.models.dto.WarehouseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DtoMapper {
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



    //Department
    DepartmentDto departmentToDepartmentDto(Department department);

    Department departmentDtoToDepartment(DepartmentDto department);

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

    //Role
    RoleDto roleToRoleDto(Role role);

    Role roleDtoToRole(RoleDto role);

    //Image
    ImageDto imageToImageDto(Image image);

    Image imageDtoToImage(ImageDto image);

    //**************************************************************************************************
    //Invoice

    @Mappings({
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "typeOfInvoice", target = "typeOfInvoice"),
            @Mapping(source = "company", target = "companyDto"),
            @Mapping(source = "contractor", target = "contractorDto"),
            @Mapping(source = "warehouse", target = "warehouseDto"),
            @Mapping(source = "spend", target = "spend")
    })
    InvoiceDto invoiceToInvoiceDto(Invoice invoice);

    @Mappings({
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "typeOfInvoice", target = "typeOfInvoice"),
            @Mapping(source = "companyDto", target = "company"),
            @Mapping(source = "contractorDto", target = "contractor"),
            @Mapping(source = "warehouseDto", target = "warehouse"),
            @Mapping(source = "spend", target = "spend")
    })
    Invoice invoiceDtoToInvoice(InvoiceDto emp);

    //Company
    CompanyDto companyToCompanyDto(Company company);

    Company companyDtoToCompany(CompanyDto companyDto);

    //Contractor
    ContractorDto contractorToContractorDto(Contractor contractor);

    Contractor contractorDtoToContractor(ContractorDto contractorDto);

    //WareHouse
    WarehouseDto warehouseToWareHouseDto (Warehouse warehouse);

    Warehouse warehouseDtoToWareHouse (WarehouseDto warehouseDto);

}
