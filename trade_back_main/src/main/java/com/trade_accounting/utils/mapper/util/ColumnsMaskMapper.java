package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.models.dto.util.ColumnsMaskDto;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.util.ColumnsMask;
import com.trade_accounting.models.entity.util.Grid;
import com.trade_accounting.models.entity.util.GridEmployeeKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ColumnsMaskMapper {

    @Mapping(source = "gridDtoId", target = "grid.id")
    ColumnsMask toModel(ColumnsMaskDto columnsMaskDto);

    @Mapping(target = "gridDtoId", source = "grid.id")
    @Mapping(target = "employeeDtoId", source = "employee.id")
    ColumnsMaskDto toDto(ColumnsMask columnsMask);

//    @Named("columnMaskConverter")
//    default ColumnsMask customColumnMaskDtoToEntityMapper(ColumnsMaskDto columnsMaskDto, Employee employee) {
//
//        GridEmployeeKey gridEmployeeKey = new GridEmployeeKey(columnsMaskDto.getGridDtoId(), employee.getId());
//
//        ColumnsMask columnMask = new ColumnsMask(gridEmployeeKey, new Grid(columnsMaskDto.getGridDtoId()),
//                employee,  columnsMaskDto.getMask());
//
//
//        return columnMask;
//    }
}
