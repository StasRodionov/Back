package com.trade_accounting.services.impl;

import com.trade_accounting.models.Payment;
import com.trade_accounting.models.TypeOfPayment;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.PaymentRepository;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.services.interfaces.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final ContractRepository contractRepository;
    private final ProjectRepository projectRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              CompanyRepository companyRepository,
                              ContractorRepository contractorRepository,
                              ContractRepository contractRepository,
                              ProjectRepository projectRepository) {
        this.paymentRepository = paymentRepository;
        this.companyRepository = companyRepository;
        this.contractorRepository = contractorRepository;
        this.contractRepository = contractRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<PaymentDto> getAll() {
        List<PaymentDto> paymentDtoList = paymentRepository.getAll();
        for (PaymentDto paymentDto: paymentDtoList){
            paymentDto.setCompanyDto(companyRepository.getById(paymentDto.getCompanyDto().getId()));
            paymentDto.setContractorDto(contractorRepository.getContractorById(paymentDto.getContractorDto().getId()));
            paymentDto.setContractDto(contractRepository.getById(paymentDto.getContractDto().getId()));
            paymentDto.setProjectDto(projectRepository.getById(paymentDto.getProjectDto().getId()));
        }
        return paymentDtoList;
    }

    @Override
    public PaymentDto getById(Long id) {
        PaymentDto paymentDto = paymentRepository.getById(id);
        paymentDto.setCompanyDto(companyRepository.getById(paymentDto.getCompanyDto().getId()));
        paymentDto.setContractorDto(contractorRepository.getContractorById(paymentDto.getContractorDto().getId()));
        paymentDto.setContractDto(contractRepository.getById(paymentDto.getContractDto().getId()));
        paymentDto.setProjectDto(projectRepository.getById(paymentDto.getProjectDto().getId()));

        return paymentDto;
    }

    @Override
    public void create(PaymentDto paymentDto) {
        paymentRepository.save(
                new Payment(
                        null,
                        TypeOfPayment.valueOf(paymentDto.getTypeOfPayment()),
                        paymentDto.getNumber(),
                        paymentDto.getTime(),
                        companyRepository.getOne(paymentDto.getCompanyDto().getId()),
                        contractorRepository.getOne(paymentDto.getContractorDto().getId()),
                        contractRepository.getOne(paymentDto.getContractDto().getId()),
                        projectRepository.getOne(paymentDto.getContractDto().getId()),
                        paymentDto.getSum()
                )
        );
    }

    @Override
    public void update(PaymentDto paymentDto) {
        paymentRepository.save(
                new Payment(
                        paymentDto.getId(),
                        TypeOfPayment.valueOf(paymentDto.getTypeOfPayment()),
                        paymentDto.getNumber(),
                        paymentDto.getTime(),
                        companyRepository.getOne(paymentDto.getCompanyDto().getId()),
                        contractorRepository.getOne(paymentDto.getContractorDto().getId()),
                        contractRepository.getOne(paymentDto.getContractDto().getId()),
                        projectRepository.getOne(paymentDto.getContractDto().getId()),
                        paymentDto.getSum()
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }
}
