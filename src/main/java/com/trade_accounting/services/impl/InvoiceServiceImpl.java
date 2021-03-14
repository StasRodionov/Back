package com.trade_accounting.services.impl;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.InvoiceRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.InvoiceService;
import com.trade_accounting.utils.DtoMapper;
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
    private final DtoMapper dtoMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              CompanyRepository companyRepository,
                              ContractorRepository contractorRepository,
                              WarehouseRepository warehouseRepository,
                              DtoMapper dtoMapper) {
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.contractorRepository = contractorRepository;
        this.warehouseRepository = warehouseRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<InvoiceDto> search(Specification<Invoice> specification) {
        return invoiceRepository.findAll(specification).stream()
                .map(dtoMapper::invoiceToInvoiceDto).collect(Collectors.toList());
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
    public InvoiceDto create(InvoiceDto invoiceDto) {
        return update(invoiceDto);
    }

    @Override
    public InvoiceDto update(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceRepository.save(dtoMapper.invoiceDtoToInvoice(invoiceDto));
        return dtoMapper.invoiceToInvoiceDto(invoice);
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}
