package com.trade_accounting.services.impl;

import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.repositories.AddressRepository;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.services.impl.Stubs.DtoStubs;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.SpecificationStubs;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private LegalDetailRepository legalDetailRepository;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private AddressRepository addressRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private CompanyServiceImpl companyService;


    @Test
    void getAll_shouldReturnListFilledCompanyDto() {
        when(companyService.getAll())
                .thenReturn(
                        Stream.of(
                                DtoStubs.getCompanyDto(1L),
                                DtoStubs.getCompanyDto(2L),
                                DtoStubs.getCompanyDto(3L)
                        )
                                .collect(Collectors.toList())
                );

        List<CompanyDto> companies = companyService.getAll();

        assertNotNull(companies, "Failure - expected that list of company not null");
        assertTrue(companies.size() > 0, "failure - expected that size of list of company greater than 0");

        for (CompanyDto companyDto : companies) {
            companyDtoIsCorrectlyInited(companyDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListCompanyDto() {
        when(companyService.getAll())
                .thenReturn(
                        new ArrayList<>()
                );

        List<CompanyDto> companies = companyService.getAll();

        assertNotNull(companies, "Failure - expected that list of company not null");
        assertEquals(0, companies.size(), "failure - expected that size of list of company equals 0");
    }

    @Test
    void search_shouldReturnListFilledCompanyDto() {
        when(companyRepository.findAll(Mockito.<Specification<Company>>any()))
                .thenReturn(
                        Stream.of(
                                ModelStubs.getCompany(1L),
                                ModelStubs.getCompany(2L),
                                ModelStubs.getCompany(3L)
                        )
                                .collect(Collectors.toList())
                );

        List<CompanyDto> companies = companyService
                .search(SpecificationStubs.getCompanySpecificationStub());

        assertNotNull(companies, "Failure - expected that list of company not null");
        assertTrue(companies.size() > 0, "failure - expected that size of list of company greater than 0");

        for (CompanyDto companyDto : companies) {
            companyDtoIsCorrectlyInited(companyDto);
        }
    }

    @Test
    void search_shouldReturnEmptyListCompanyDto() {
        when(companyRepository.findAll(Mockito.<Specification<Company>>any()))
                .thenReturn(
                        new ArrayList<>()
                );

        List<CompanyDto> companies = companyService
                .search(SpecificationStubs.getCompanySpecificationStub());

        assertNotNull(companies, "Failure - expected that list of company not null");
        assertEquals(0, companies.size(), "failure - expected that size of list of company greater than 0");
    }

    @Test
    void getById_shouldReturnFilledCompanyDto() {
        when(companyService.getById(1L))
                .thenReturn(DtoStubs.getCompanyDto(anyLong()));

        CompanyDto companyDto = companyService.getById(1L);

        companyDtoIsCorrectlyInited(companyDto);
    }

    @Test
    void getByEmail_shouldReturnFilledCompanyDto() {
        CompanyDto companyFromRepo = DtoStubs.getCompanyDto(1L);

        when(companyRepository.findCompanyByEmail(anyString()))
                .thenReturn(companyFromRepo);

        CompanyDto companyDto = companyService.getByEmail("email");

        companyDtoIsCorrectlyInited(companyDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        companyService.create(
                DtoStubs.getCompanyDto(1L)
        );

        verify(companyRepository).save(any(Company.class));
        verify(legalDetailRepository).save(any(LegalDetail.class)); //- этот тест валится здесь
        verify(bankAccountRepository, times(3)).save(any(BankAccount.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        companyService.update(
                DtoStubs.getCompanyDto(1L)
        );

        verify(companyRepository).save(any(Company.class));
        verify(addressRepository).getOne(null);
        verify(legalDetailRepository).getOne(null);
        verify(bankAccountRepository, times(3)).getOne(anyLong());
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        companyService.deleteById(1L);
        verify(companyRepository).deleteById(anyLong());
    }

    void companyDtoIsCorrectlyInited(CompanyDto companyDto) {
        assertNotNull(companyDto, "failure - fail in passed companyDto");
        assertNotNull(companyDto.getId(), "failure - fail in field 'id' into companyDto");
    }
}