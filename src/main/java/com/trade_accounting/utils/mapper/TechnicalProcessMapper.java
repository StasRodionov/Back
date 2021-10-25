package com.trade_accounting.utils.mapper;


import com.trade_accounting.models.StagesProduction;
import com.trade_accounting.models.TechnicalOperations;
import com.trade_accounting.models.TechnicalProcess;
import com.trade_accounting.models.dto.TechnicalOperationsDto;
import com.trade_accounting.models.dto.TechnicalProcessDto;
import com.trade_accounting.repositories.StagesProductionRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TechnicalProcessMapper {

    /**
     * Convert Model (entity based) from Dto
     * @param dto - Dto representation of Domain Entity
     * @return TechnicalProcess model from income TechnicalProcessDto
     */
    TechnicalProcess toModel(TechnicalProcessDto dto);

    /**
     * Convert Dto from Model (Domain Entity)
     * @param process - TechnicalProcess Entity from a DB
     * @return TechnicalProcessDto based on existing TechnicalProcess
     */
    TechnicalProcessDto toDto(TechnicalProcess process);


    /**
     * Update model by income dto
     * @param dto - income dto with new (or not) fields
     * @param process - model which gonna be automatically find and updating
     */
    void updateModelFromDto(TechnicalProcessDto dto, @MappingTarget TechnicalProcess process);
}
