package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.InvoicesStatus;
import com.trade_accounting.models.Movement;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.MovementDto;
import com.trade_accounting.services.interfaces.InvoiceProductService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
//    Invoice
//    @Mappings({
//            @Mapping(source = "company.id", target = "companyId"),
//            @Mapping(source = "contractor.id", target = "contractorId"),
//            @Mapping(source = "warehouse.id", target = "warehouseId")
//    })
//    InvoiceDto toDto(Invoice invoice);
//
//    @Mappings({
//            @Mapping(source = "companyId", target = "company.id"),
//            @Mapping(source = "contractorId", target = "contractor.id"),
//            @Mapping(source = "warehouseId", target = "warehouse.id")
//    })
//    Invoice toModel(InvoiceDto emp);
//}

    default InvoiceDto toDto(Invoice invoice) {
        if (invoice == null) {
            return null;
        }

        InvoiceDto.InvoiceDtoBuilder invoiceDto = InvoiceDto.builder()
                .companyId(invoiceCompanyId(invoice))
                .contractorId(invoiceContractorId(invoice))
                .warehouseId(invoiceWarehouseId(invoice))
                .id(invoice.getId())
                .invoiceProductsIds(
                        invoice.getInvoiceProducts().stream()
                                .map(InvoiceProduct::getId)
                                .collect(Collectors.toList()))
                .isRecyclebin(invoice.getIsRecyclebin())
                .comment(invoice.getComment())
                .isSpend(invoice.getIsSpend())
                .isSent(invoice.getIsSent())
                .isPrint(invoice.getIsPrint())
                .invoicesStatusId(invoiceStatusId(invoice));

        if (invoice.getDate() != null) {
            invoiceDto.date(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(invoice.getDate()));
        }
        if (invoice.getTypeOfInvoice() != null) {
            invoiceDto.typeOfInvoice(invoice.getTypeOfInvoice().name());
        }

        return invoiceDto.build();
    }


    default Invoice toModel(InvoiceDto emp) {
        if (emp == null) {
            return null;
        }

        Invoice.InvoiceBuilder invoice = Invoice.builder()
                .company(invoiceDtoToCompany(emp))
                .contractor(invoiceDtoToContractor(emp))
                .warehouse(invoiceDtoToWarehouse(emp))
                .id(emp.getId())
                .isSpend(emp.getIsSpend())
                .isSent(emp.getIsSent())
                .isPrint(emp.getIsPrint())
                .comment(emp.getComment())
                .invoiceProducts(invoiceDtoToInvoiceProducts(emp))
                .invoicesStatus(invoiceDtoToInvoicesStatus(emp))
                .isRecyclebin(emp.getIsRecyclebin());
        if (emp.getDate() != null) {
            invoice.date(LocalDateTime.parse(emp.getDate()));
        }
        if (emp.getTypeOfInvoice() != null) {
            invoice.typeOfInvoice(Enum.valueOf(TypeOfInvoice.class, emp.getTypeOfInvoice()));
        }
        return invoice.build();
    }

    private Long invoiceCompanyId(Invoice invoice) {
        if (invoice == null) {
            return null;
        }
        Company company = invoice.getCompany();
        if (company == null) {
            return null;
        }
        Long id = company.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long invoiceContractorId(Invoice invoice) {
        if (invoice == null) {
            return null;
        }
        Contractor contractor = invoice.getContractor();
        if (contractor == null) {
            return null;
        }
        Long id = contractor.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long invoiceStatusId(Invoice invoice) {
        if (invoice == null) {
            return null;
        }
        InvoicesStatus invoicesStatus = invoice.getInvoicesStatus();
        if (invoicesStatus == null) {
            return null;
        }
        Long id = invoicesStatus.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long invoiceWarehouseId(Invoice invoice) {
        if (invoice == null) {
            return null;
        }
        Warehouse warehouse = invoice.getWarehouse();
        if (warehouse == null) {
            return null;
        }
        Long id = warehouse.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    default Company invoiceDtoToCompany(InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }

        Company company = new Company();

        company.setId(invoiceDto.getCompanyId());

        return company;
    }

    default Contractor invoiceDtoToContractor(InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }

        Contractor contractor = new Contractor();

        contractor.setId(invoiceDto.getContractorId());

        return contractor;
    }

    default Warehouse invoiceDtoToWarehouse(InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }

        Warehouse.WarehouseBuilder warehouse = Warehouse.builder();

        warehouse.id(invoiceDto.getWarehouseId());

        return warehouse.build();
    }

    default InvoicesStatus invoiceDtoToInvoicesStatus (InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }

        InvoicesStatus invoicesStatus = new InvoicesStatus();

        invoicesStatus.setId(invoiceDto.getContractorId());

        return invoicesStatus;
    }

    default List<InvoiceProduct> invoiceDtoToInvoiceProducts(InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }
        return invoiceDto.getInvoiceProductsIds().stream()
                .map(id -> {
                    InvoiceProduct invoiceProduct = new InvoiceProduct();
                    invoiceProduct.setId(id);
                    return invoiceProduct;
                })
                .collect(Collectors.toList());
    }

}
