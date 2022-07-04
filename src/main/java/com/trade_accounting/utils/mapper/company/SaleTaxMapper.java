package com.trade_accounting.utils.mapper.company;


import com.trade_accounting.models.dto.company.SaleTaxDto;
import com.trade_accounting.models.entity.company.SaleTax;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleTaxMapper {

    SaleTax toModel(SaleTaxDto saleTaxDto);

    SaleTaxDto toDto(SaleTax saleTax);

}
