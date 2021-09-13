package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Shipment;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.ShipmentDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        shipmentDto.sum(shipment.getSum());
        shipmentDto.paid(shipment.getPaid());
        shipmentDto.isSpend(shipment.getIsSpend());
        shipmentDto.isSend(shipment.getIsSend());
        shipmentDto.isPrint(shipment.getIsPrint());
        shipmentDto.comment( shipment.getComment() );

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
        shipment.sum(emp.getSum());
        shipment.paid(emp.getPaid());
        shipment.isSpend(emp.getIsSpend());
        shipment.isSend(emp.getIsSend());
        shipment.isPrint(emp.getIsPrint());
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
