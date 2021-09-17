package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.BonusProgram;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.File;
import com.trade_accounting.models.RetailOperationWithPoints;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.RetailOperationWithPointsDto;
import com.trade_accounting.models.dto.RetailStoreDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RetailOperationWithPointsMapper {

    /**
     * @return RetailOperationWithPoints
     */

    default RetailOperationWithPoints toModel(RetailOperationWithPointsDto dto) {
        if (dto == null) {
            return null;
        }

        return RetailOperationWithPoints.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .typeOperation(dto.getTypeOperation())
                .numberOfPoints(dto.getNumberOfPoints())
                .build();
    }

    /**
     * @return RetailOperationWithPointsDto
     */

    default RetailOperationWithPointsDto toDto(RetailOperationWithPoints model) {
        RetailOperationWithPointsDto dto = new RetailOperationWithPointsDto();
        if (model == null) {
            return null;
        } else {
            dto.setId(model.getId());
            dto.setNumber(model.getNumber());
            dto.setCurrentTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(model.getCurrentTime()));
            dto.setTypeOperation(model.getTypeOperation());
            dto.setNumberOfPoints(model.getNumberOfPoints());
            dto.setAccrualDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(model.getAccrualDate()));

            BonusProgram bonusProgram = model.getBonusProgram();
            if (bonusProgram == null) {
                return null;
            } else {
                dto.setBonusProgramId(bonusProgram.getId());

                Contractor contractor = model.getContractor();
                if (contractor == null) {
                    return null;
                } else {
                    dto.setContractorId(contractor.getId());

                    dto.setTaskId(model.getTask().getId());

                    List<Long> fileIds = model.getFiles().stream()
                            .map(File::getId)
                            .collect(Collectors.toList());

                    dto.setFileIds(fileIds);
                    return dto;
                }
            }
        }
    }
}
