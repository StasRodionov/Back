package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.dto.util.DiscountDto;
import com.trade_accounting.models.entity.util.Discount;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DiscountMapper {
    @Mapping(source = "bonusProgramId", target = "bonusProgram.id")
    Discount discountDtoToDiscount(DiscountDto discountDto);

    @Mapping(source = "bonusProgram.id", target = "bonusProgramId")
    DiscountDto discountToDiscountDto(Discount discount);

    @Mapping(source = "bonusProgramId", target = "bonusProgram.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Discount updateDiscountFromDiscountDto(DiscountDto discountDto, @MappingTarget Discount discount);
}
