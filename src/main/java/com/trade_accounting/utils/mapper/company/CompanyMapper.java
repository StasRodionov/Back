package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.dto.company.CompanyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    //Company
    @Mappings({
            @Mapping(source = "addressId", target = "address.id"),
            @Mapping(source = "legalDetailDtoId", target = "legalDetail.id")
    })
    Company toModel(CompanyDto companyDto);

    @Mappings({
            @Mapping(source = "address.id", target = "addressId"),
            @Mapping(source = "legalDetail.id", target = "legalDetailDtoId")
    })
    CompanyDto toDto(Company company);
}
