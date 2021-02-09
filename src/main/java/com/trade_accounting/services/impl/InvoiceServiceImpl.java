package com.trade_accounting.services.impl;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.ContractorGroup;
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
    public void create(InvoiceDto invoiceDto) {

        update(invoiceDto);

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
    public void update(InvoiceDto invoiceDto) {
        /*invoiceRepository.save(
                new Invoice(
                        invoiceDto.getId(),
                        invoiceDto.getDate(),
                        TypeOfInvoice.valueOf(invoiceDto.getTypeOfInvoice()),
                        companyRepository.getOne(invoiceDto.getCompanyDto().getId()),
                        contractorRepository.getOne(invoiceDto.getContractorDto().getId()),
                        warehouseRepository.getOne(invoiceDto.getWarehouseDto().getId()),
                        invoiceDto.isSpend()));*/
      //  List<BankAccount> bankAccountList = bankAccountRepository.getBankAccountByContractorId(invoiceDto.getContractorDto().getId());
       // invoiceDto.getContractorDto().setBankAccountDto(bankAccountList.stream().map(bankAccount -> bankAccountRepository.getById(bankAccount.getId())).collect(Collectors.toList()));

        invoiceRepository.save(ModelDtoConverter.convertToInvoice(invoiceDto,
                TypeOfInvoice.valueOf(invoiceDto.getTypeOfInvoice(),
                companyRepository.save(ModelDtoConverter.convertToCompany(invoiceDto.getCompanyDto(),
                        legalDetailRepository.save(ModelDtoConverter.convertToLegalDetail(invoiceDto.getCompanyDto().getLegalDetailDto(),
                                typeOfContractorRepository.save(invoiceDto.getCompanyDto().getLegalDetailDto().getTypeOfContractorDto()))))),
                        contractorRepository.save(ModelDtoConverter.convertToContractor(invoiceDto.getContractorDto(),
                                contractorGroupRepository.save(ModelDtoConverter.convertToContractorGroup(invoiceDto.getContractorDto().getContractorGroupDto())),
                                typeOfPriceRepository.save(ModelDtoConverter.convertToTypeOfPrice(invoiceDto.getContractorDto().getTypeOfPriceDto(),
                                      bankAccountRepository.save(ModelDtoConverter.convertToBankAccount(invoiceDto.getContractorDto().getBankAccountDto()),
                                              legalDetailRepository.save(ModelDtoConverter.convertToLegalDetail(invoiceDto.getContractorDto().getLegalDetailDto(),
                                                      ModelDtoConverter.convertToTypeOfContractor(invoiceDto.getContractorDto().getLegalDetailDto().getTypeOfContractorDto())))))))),
                        warehouseRepository.save(ModelDtoConverter.convertToWarehouse(invoiceDto.getWarehouseDto())))));



    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}
