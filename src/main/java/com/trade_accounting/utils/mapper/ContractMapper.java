package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Contract;
import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.models.dto.InternalOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    //Contract
    default Contract toModel(ContractDto contractDto) {
        if (contractDto == null) {
            return null;
        }

        return Contract.builder()
                .id(contractDto.getId())
                .number(contractDto.getNumber())
                .amount(contractDto.getAmount())
                .archive(contractDto.getArchive())
                .comment(contractDto.getComment())
                .build();
    }

    default ContractDto toDto(Contract contract) {
        ContractDto contractDto = new ContractDto();
        if (contract == null || contract.getBankAccount().getId() == null || contract.getCompany().getId() == null
                || contract.getContractor().getId() == null || contract.getLegalDetail().getId() == null) {
            return null;
        } else {
            contractDto.setId(contract.getId());
            contractDto.setNumber(contract.getNumber());
            contractDto.setContractDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(contract.getContractDate()));
            contractDto.setCompanyId(contract.getCompany().getId());
            contractDto.setBankAccountId(contract.getBankAccount().getId());
            contractDto.setContractorId(contract.getContractor().getId());
            contractDto.setAmount(contract.getAmount());
            contractDto.setArchive(contract.getArchive());
            contractDto.setComment(contract.getComment());
            contractDto.setLegalDetailId(contract.getLegalDetail().getId());
            return contractDto;
        }
    }
    List<ContractDto> toListDto(List<Contract> contracts);
}
//    @Mappings({
//            @Mapping(source = "company.id", target = "companyId"),
//            @Mapping(source = "bankAccount.id", target = "bankAccountId"),
//            @Mapping(source = "contractor.id", target = "contractorId"),
//            @Mapping(source = "legalDetail.id", target = "legalDetailId")
//    })
//     ContractDto toDto(Contract contract);

//    @Mappings({
//            @Mapping(source = "companyId", target = "company.id"),
//            @Mapping(source = "bankAccountId", target = "bankAccount.id"),
//            @Mapping(source = "contractorId", target = "contractor.id"),
//            @Mapping(source = "legalDetailId", target = "legalDetail.id"),
//            @Mapping(target = "contractDate", ignore = true)
//    })
//     Contract toModel(ContractDto contractDto);

//    }

