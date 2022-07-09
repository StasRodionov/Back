package com.trade_accounting.utils.mapper.fias;

import com.trade_accounting.models.dto.fias.RegionDto;
import com.trade_accounting.models.entity.fias.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    //Region

    @Mapping(source = "districtDtos", target = "districts")
    Region toModel(RegionDto regionDto);


    @Mapping(source = "districts", target = "districtDtos")
    RegionDto toDto(Region region);
}
