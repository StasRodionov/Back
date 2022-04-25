package com.trade_accounting.utils.mapper.retail;

import com.trade_accounting.models.entity.retail.RetailOperationWithPoints;
import com.trade_accounting.models.dto.retail.RetailOperationWithPointsDto;
import com.trade_accounting.models.entity.util.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetailOperationWithPointsMapper {
    //RetailOperationWithPoints
    @Mapping(source = "currentTime", target = "currentTime", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(source = "accrualDate", target = "accrualDate", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "bonusProgramId", target = "bonusProgram.id")
    @Mapping(source = "contractorId", target = "contractor.id")
    RetailOperationWithPoints toModel(RetailOperationWithPointsDto dto);

    @Mapping(source = "currentTime", target = "currentTime", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(source = "accrualDate", target = "accrualDate", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "bonusProgram.id", target = "bonusProgramId")
    @Mapping(source = "contractor.id", target = "contractorId")
    @Mapping(target = "taskId", source = "task.id")
    @Mapping(target = "fileIds", source = "files")
    RetailOperationWithPointsDto toDto(RetailOperationWithPoints model);

    default Long fileToLong(File file) {
        return file.getId();
    }
}
