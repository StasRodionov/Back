package com.trade_accounting.services.impl;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.InvoiceRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.InvoiceService;
import com.trade_accounting.utils.ModelDtoConverter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final WarehouseRepository warehouseRepository;
    private final LegalDetailRepository legalDetailRepository;
    private final TypeOfContractorRepository typeOfContractorRepository;
    private final ContractorGroupRepository contractorGroupRepository;
    private final TypeOfPriceRepository typeOfPriceRepository;
    private final BankAccountRepository bankAccountRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              CompanyRepository companyRepository,
                              ContractorRepository contractorRepository,
                              WarehouseRepository warehouseRepository,
                              LegalDetailRepository legalDetailRepository,
                              TypeOfContractorRepository typeOfContractorRepository,
                              ContractorGroupRepository contractorGroupRepository,
                              TypeOfPriceRepository typeOfPriceRepository,
                              BankAccountRepository bankAccountRepository) {
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.contractorRepository = contractorRepository;
        this.warehouseRepository = warehouseRepository;
        this.legalDetailRepository = legalDetailRepository;
        this.typeOfContractorRepository = typeOfContractorRepository;
        this.contractorGroupRepository = contractorGroupRepository;
        this.typeOfPriceRepository = typeOfPriceRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<InvoiceDto> search(Specification<Invoice> specification) {
        return invoiceRepository.findAll(specification).stream()
                .map(ModelDtoConverter::convertToInvoiceDto).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDto> getAll() {
        List<InvoiceDto> listInvoiceDto = invoiceRepository.getAll();
        for (InvoiceDto invoiceDto : listInvoiceDto) {
            invoiceDto.setCompanyDto(companyRepository.getById(invoiceDto.getCompanyDto().getId()));
            invoiceDto.setContractorDto(contractorRepository.getById(invoiceDto.getContractorDto().getId()));
            invoiceDto.setWarehouseDto(warehouseRepository.getById(invoiceDto.getWarehouseDto().getId()));
        }
        return listInvoiceDto;
    }

    @Override
    public InvoiceDto getById(Long id) {
        InvoiceDto invoiceDto = invoiceRepository.getById(id);
        invoiceDto.setCompanyDto(companyRepository.getById(invoiceDto.getCompanyDto().getId()));
        invoiceDto.setContractorDto(contractorRepository.getById(invoiceDto.getContractorDto().getId()));
        invoiceDto.setWarehouseDto(warehouseRepository.getById(invoiceDto.getWarehouseDto().getId()));
        return invoiceDto;
    }

    @Override
    public Invoice create(InvoiceDto invoiceDto) {

        return update(invoiceDto);

        /*invoiceRepository.save(
                new Invoice(
                        null,
                        invoiceDto.getDate(),
                        TypeOfInvoice.valueOf(invoiceDto.getTypeOfInvoice()),
                        companyRepository.getOne(invoiceDto.getCompanyDto().getId()),
                        contractorRepository.getOne(invoiceDto.getContractorDto().getId()),
                        warehouseRepository.getOne(invoiceDto.getWarehouseDto().getId()),
                        invoiceDto.isSpend()));*/
    }

    @Override
    public Invoice update(InvoiceDto invoiceDto) {
        /*invoiceRepository.save(
                new Invoice(
                        invoiceDto.getId(),
                        invoiceDto.getDate(),
                        TypeOfInvoice.valueOf(invoiceDto.getTypeOfInvoice()),
                        companyRepository.getOne(invoiceDto.getCompanyDto().getId()),
                        contractorRepository.getOne(invoiceDto.getContractorDto().getId()),
                        warehouseRepository.getOne(invoiceDto.getWarehouseDto().getId()),
                        invoiceDto.isSpend()));*/

        Invoice invoice = invoiceRepository.save(ModelDtoConverter.convertToInvoice(invoiceDto,

                (invoiceDto.getTypeOfInvoice() == null) ? null : TypeOfInvoice.valueOf(invoiceDto.getTypeOfInvoice()),
                (invoiceDto.getCompanyDto() == null) ? null : companyRepository.save(ModelDtoConverter.convertToCompany(invoiceDto.getCompanyDto(),
                        (invoiceDto.getCompanyDto().getLegalDetailDto() == null) ? null : legalDetailRepository.save(ModelDtoConverter.convertToLegalDetail(invoiceDto.getCompanyDto().getLegalDetailDto(),
                                (invoiceDto.getCompanyDto().getLegalDetailDto().getTypeOfContractorDto() == null) ? null : typeOfContractorRepository.save(ModelDtoConverter.convertToTypeOfContractor(
                                        invoiceDto.getCompanyDto().getLegalDetailDto().getTypeOfContractorDto())))))),
                (invoiceDto.getContractorDto() == null) ? null : contractorRepository.save(ModelDtoConverter.convertToContractor(invoiceDto.getContractorDto(), (invoiceDto.getContractorDto().getContractorGroupDto() == null) ? null : contractorGroupRepository.save(ModelDtoConverter.convertToContractorGroup(invoiceDto.getContractorDto().getContractorGroupDto())), (invoiceDto.getContractorDto().getTypeOfContractorDto() == null) ? null : typeOfContractorRepository.save(ModelDtoConverter.convertToTypeOfContractor(invoiceDto.getContractorDto().getTypeOfContractorDto())), (invoiceDto.getContractorDto().getTypeOfPriceDto() == null) ? null : typeOfPriceRepository.save(ModelDtoConverter.convertToTypeOfPrice(invoiceDto.getContractorDto().getTypeOfPriceDto())), (invoiceDto.getContractorDto().getBankAccountDto() == null) ? null : bankAccountRepository.saveAll(ModelDtoConverter.convertToListOfBankAccount(invoiceDto.getContractorDto().getBankAccountDto())), (invoiceDto.getContractorDto().getLegalDetailDto() == null) ? null : legalDetailRepository.save(ModelDtoConverter.convertToLegalDetail(invoiceDto.getContractorDto().getLegalDetailDto(),
                        (invoiceDto.getContractorDto().getLegalDetailDto().getTypeOfContractorDto() == null) ? null : typeOfContractorRepository.save(ModelDtoConverter.convertToTypeOfContractor(invoiceDto.getContractorDto().getLegalDetailDto().getTypeOfContractorDto())))))),
                (invoiceDto.getWarehouseDto() == null) ? null : warehouseRepository.save(ModelDtoConverter.convertToWarehouse(invoiceDto.getWarehouseDto()))));
        return  invoice;
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}
