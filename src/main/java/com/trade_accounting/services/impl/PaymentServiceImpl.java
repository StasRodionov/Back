package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.Payment;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.PaymentRepository;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.services.interfaces.PaymentService;
import com.trade_accounting.utils.DtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final ContractRepository contractRepository;
    private final ProjectRepository projectRepository;

    private final DtoMapper dtoMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              CompanyRepository companyRepository,
                              ContractorRepository contractorRepository,
                              ContractRepository contractRepository,
                              ProjectRepository projectRepository, DtoMapper dtoMapper) {
        this.paymentRepository = paymentRepository;
        this.companyRepository = companyRepository;
        this.contractorRepository = contractorRepository;
        this.contractRepository = contractRepository;
        this.projectRepository = projectRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<PaymentDto> getAll() {
        return paymentRepository.findAll().stream()
                .map(dtoMapper::paymentToPaymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto getById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if(payment.isEmpty()){
            throw new NotFoundEntityException("No payment ");
        }
        return dtoMapper.paymentToPaymentDto(payment.get());

    }

    @Override
    public PaymentDto create(PaymentDto paymentDto) {
        Payment payment = dtoMapper.paymentDtoToPayment(paymentDto);

        payment.setCompany(
                companyRepository.findById(
                        paymentDto.getCompanyDto().getId()
                ).orElse(null)
        );

        payment.setContractor(
                contractorRepository.findById(
                        paymentDto.getContractorDto().getId()
                ).orElse(null)
        );

        payment.setContract(
                contractRepository.findById(
                        paymentDto.getContractDto().getId()
                ).orElse(null)
        );

        payment.setProject(
                projectRepository.findById(
                        paymentDto.getProjectDto().getId()
                ).orElse(null)
        );


        return dtoMapper.paymentToPaymentDto(paymentRepository.save(payment));
    }

    @Override
    public PaymentDto update(PaymentDto paymentDto) {
        return create(paymentDto);
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentDto> search(String search) {
        return paymentRepository.search(search).stream()
                .map(dtoMapper::paymentToPaymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> filter(Specification<Payment> specification) {
        return paymentRepository.findAll(specification).stream().
                map(dtoMapper::paymentToPaymentDto).collect(Collectors.toList());
    }
}
