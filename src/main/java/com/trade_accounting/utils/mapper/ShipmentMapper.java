package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Shipment;
import com.trade_accounting.models.ShipmentProduct;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.ShipmentDto;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    default ShipmentDto toDto(Shipment shipment){
        if( shipment == null){
            return null;
        }
        ShipmentDto.ShipmentDtoBuilder shipmentDto = ShipmentDto.builder();

        shipmentDto.id( shipment.getId() );
        if ( shipment.getDate() != null ) {
            shipmentDto.date( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( shipment.getDate() ) );
        }
        shipmentDto.companyId( shipmentCompanyId( shipment ) );
        shipmentDto.contractorId( shipmentContractorId( shipment ) );
        shipmentDto.warehouseId( shipmentWarehouseId( shipment ) );
        shipmentDto.paid(shipment.getPaid());
        shipmentDto.isSpend(shipment.getIsSpend());
        shipmentDto.isSend(shipment.getIsSent());
        shipmentDto.isPrint(shipment.getIsPrint());
        shipmentDto.isRecyclebin(shipment.getIsRecyclebin());
        shipmentDto.comment( shipment.getComment() );
        shipmentDto.shipmentProductsIds(
                shipment.getShipmentProducts().stream()
                        .map(ShipmentProduct::getId)
                        .collect(Collectors.toList()));

        shipmentDto.sum(shipment.getShipmentProducts().stream()
                .map((x -> x.getAmount().multiply(x.getPrice())))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO));

        return shipmentDto.build();
    }

    default Shipment toModel(ShipmentDto emp){
        if( emp == null){
            return null;
        }
        Shipment.ShipmentBuilder shipment = Shipment.builder();

        shipment.id(emp.getId());
        if (emp.getDate() != null ) {
            shipment.date(LocalDateTime.parse(emp.getDate()));
        }
        shipment.company(shipmentDtoToCompany(emp));
        shipment.contractor(shipmentDtoToContractor(emp));
        shipment.warehouse(shipmentDtoToWarehouse(emp));
        shipment.paid(emp.getPaid());
        shipment.isSpend(emp.getIsSpend());
        shipment.isSent(emp.getIsSend());
        shipment.isPrint(emp.getIsPrint());
        shipment.isRecyclebin(emp.getIsRecyclebin());
        shipment.comment(emp.getComment());

        return shipment.build();
    }



    private Long shipmentCompanyId(Shipment shipment) {
        if ( shipment == null ) {
            return null;
        }
        Company company = shipment.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long shipmentContractorId(Shipment shipment) {
        if ( shipment == null ) {
            return null;
        }
        Contractor contractor = shipment.getContractor();
        if ( contractor == null ) {
            return null;
        }
        Long id = contractor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long shipmentWarehouseId(Shipment shipment) {
        if ( shipment == null ) {
            return null;
        }
        Warehouse warehouse = shipment.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        Long id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    default Company shipmentDtoToCompany(ShipmentDto shipmentDto) {
        if ( shipmentDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( shipmentDto.getCompanyId() );

        return company;
    }

    default Contractor shipmentDtoToContractor(ShipmentDto shipmentDto) {
        if ( shipmentDto == null ) {
            return null;
        }

        Contractor contractor = new Contractor();

        contractor.setId( shipmentDto.getContractorId() );

        return contractor;
    }

    default Warehouse shipmentDtoToWarehouse(ShipmentDto shipmentDto) {
        if ( shipmentDto == null ) {
            return null;
        }

        Warehouse.WarehouseBuilder warehouse = Warehouse.builder();

        warehouse.id( shipmentDto.getWarehouseId() );

        return warehouse.build();
    }

}
