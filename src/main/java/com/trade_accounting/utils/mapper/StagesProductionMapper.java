package com.trade_accounting.utils.mapper;


import com.trade_accounting.models.StagesProduction;
import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.dto.StagesProductionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StagesProductionMapper {

     default StagesProductionDto toDto(StagesProduction stagesProduction) {
          StagesProductionDto stagesProductionDto = new StagesProductionDto();

          if(stagesProduction == null) {
               return null;
          } else {
               stagesProductionDto.setId(stagesProduction.getId());
               stagesProductionDto.setName(stagesProduction.getName());
               stagesProductionDto.setDescription(stagesProduction.getDescription());
               stagesProductionDto.setDepartment(stagesProduction.getDepartment());
               stagesProductionDto.setEmployee(stagesProduction.getEmployee());
               return stagesProductionDto;
          }
     }

     default StagesProduction toModel(StagesProductionDto stagesProductionDto){
          if (stagesProductionDto == null) {
               return null;
          }

          StagesProduction.StagesProductionBuilder stagesProduction = StagesProduction.builder();

          stagesProduction.id(stagesProductionDto.getId() );
          stagesProduction.name(stagesProductionDto.getName() );
          stagesProduction.description(stagesProduction.build().getDescription() );
          stagesProduction.department(stagesProduction.build().getDepartment());
          stagesProduction.employee(stagesProduction.build().getEmployee());
          return stagesProduction.build();

     }
}
// Этапы