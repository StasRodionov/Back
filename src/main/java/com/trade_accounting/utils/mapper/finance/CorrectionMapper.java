package com.trade_accounting.utils.mapper.finance;

import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.entity.finance.Correction;
import com.trade_accounting.models.dto.finance.CorrectionDto;
import com.trade_accounting.models.entity.finance.CorrectionProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CorrectionMapper {
    //Correction
    @Mapping(target = "date", ignore = true)
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "correctionProductIds", target = "correctionProducts")
    Correction toModel(CorrectionDto correctionDto);

    @Mapping(target = "date", ignore = true)
    @Mapping(target = "warehouseId", source = "warehouse.id")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "correctionProductIds", source = "correctionProducts")
    CorrectionDto toDto(Correction correction);

    default Long correctionProductToLong(CorrectionProduct correctionProduct) {
        return correctionProduct.getId();
    }

    default CorrectionProduct longToCorrectionProduct(Long id) {
        return CorrectionProduct.builder()
                .id(id)
                .build();
    }
}
