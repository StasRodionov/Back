package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.Movement;
import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.MovementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    //Invoice
    @Mappings({
            @Mapping(source = "company.id", target = "companyId"),
            @Mapping(source = "contractor.id", target = "contractorId"),
            @Mapping(source = "warehouse.id", target = "warehouseId"),
    })
    InvoiceDto toDto(Invoice invoice);

    @Mappings({
            @Mapping(source = "companyId", target = "company.id"),
            @Mapping(source = "contractorId", target = "contractor.id"),
            @Mapping(source = "warehouseId", target = "warehouse.id"),
    })
    Invoice toModel(InvoiceDto emp);
}

//    default Invoice toModel(InvoiceDto invoiceDto) {
//        if (invoiceDto == null) {
//            return null;
//        }
//
//        return Invoice.builder()
//                .id(invoiceDto.getId())
//                .date(invoiceDto.getDate())
//                .company(invoiceDto.getCompanyId())
//                .contractor(invoiceDto.getContractorId())
//                .warehouse(invoiceDto.getWarehouseId())
//                .isSpend(invoiceDto.getIsSpend())
//                .comment(invoiceDto.getComment())
//                .build();
//    }
//
//
//    /**
//     * @return InvoiceDto
//     */
//    default InvoiceDto toDto(Invoice invoice) {
//        InvoiceDto invoiceDto = new InvoiceDto();
//        if (invoice == null) {
//            return null;
//        } else {
//            invoiceDto.setId(invoice.getId());
//            invoiceDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(invoice.getDate()));
//            invoiceDto.setTypeOfInvoice(invoice.getTypeOfInvoice().toString());
//            invoiceDto.setCompanyId(invoice.getCompany().getId());
//            invoiceDto.setContractorId(invoice.getContractor().getId());
//            invoiceDto.setWarehouseId(invoice.getWarehouse().getId());
//            invoiceDto.setIsSpend(invoice.isSpend());
//            invoiceDto.setComment(invoice.getComment());
//
//            if (invoice.getCompany() == null) {
//                return null;
//            } else {
//                invoiceDto.setCompanyId(invoice.getCompany().getId());
//                if (invoice.getContractor() == null) {
//                    return null;
//                } else {
//                    invoiceDto.setContractorId(invoice.getContractor().getId());
//                    if(invoice.getWarehouse() == null){
//                        return null;
//                    } else {
//
//                    invoiceDto.setWarehouseId(invoice.getWarehouse().getId());
//                    return invoiceDto;
//                    }
//                }
//            }
//        }
//    }
//}
