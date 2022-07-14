package com.trade_accounting.utils.mapper.invoice;

import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import com.trade_accounting.models.entity.util.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    //Invoice
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "invoicesStatusId", target = "invoicesStatus.id")
    @Mapping(source = "invoiceProductsIds", target = "invoiceProducts")
    @Mapping(source = "invoiceDto", target = "project", qualifiedByName = "projectConverter")
    @Mapping(source = "salesChannelId", target = "salesChannel.id")
    Invoice toModel(InvoiceDto invoiceDto);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "contractorId", source = "contractor.id")
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "invoicesStatusId", source = "invoicesStatus.id")
    @Mapping(target = "invoiceProductsIds", source = "invoiceProducts")
    @Mapping(target = "projectId", source = "project.id")
    @Mapping(target = "salesChannelId", source = "salesChannel.id")
    InvoiceDto toDto(Invoice invoice);

    @Named("projectConverter")
    default Project projectFieldFromDtoToModel(InvoiceDto invoiceDto) {
        return invoiceDto.getProjectId() == null ? null :
                new Project(invoiceDto.getProjectId(), null, null, null);
    }

    default Long invoiceProductToLong(InvoiceProduct invoiceProduct) {
        return invoiceProduct.getId();
    }

    default InvoiceProduct longToInvoiceProduct(Long id) {
        return InvoiceProduct.builder()
                .id(id)
                .build();
    }
}

