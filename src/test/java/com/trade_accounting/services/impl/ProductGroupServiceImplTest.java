package com.trade_accounting.services.impl;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Image;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.ProductGroup;
import com.trade_accounting.models.Role;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.repositories.ProductGroupRepository;
import com.trade_accounting.repositories.RoleRepository;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductGroupServiceImplTest {

    @Mock
    private ProductGroupRepository productGroupRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private ProductGroupServiceImpl productGroupService;

    //Tests
    @Test
    void getAll_shouldReturnListFilledProductDto() {
        when(productGroupRepository.findAll())
                .thenReturn(getListProductGroup());

        List<ProductGroupDto> productGroupDtoList = productGroupService.getAll();

        assertNotNull(productGroupDtoList, "failure - expected that a list of productDto not null: " + productGroupDtoList);
        assertTrue(productGroupDtoList.size() > 0, "failure - expected that a size of list of productDto greater than 0: " + productGroupDtoList);

        for(ProductGroupDto productGroupDto : productGroupDtoList) {
            productGroupDtoIsCorrectlyInited(productGroupDto);
        }
    }

    @Test
    void getById_shouldReturnFilledEmployeeDto() {
        Optional<ProductGroup> productGroupFromRepo = Optional.of(getProductGroup(1L));

        when(productGroupRepository.findById(anyLong()))
                .thenReturn(productGroupFromRepo);

        ProductGroupDto productGroup = productGroupService.getById(1L);

        assertNotNull(productGroup, "failure - expected that employee not null.");

        productGroupDtoIsCorrectlyInited(productGroup);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        ProductGroupDto productGroup = productGroupService.create(
            getProductGroupDto(1L)
        );

        verify(productGroupRepository).save(any(ProductGroup.class));
        verify(productGroupRepository).findById(anyLong());

        productGroupDtoIsCorrectlyInited(productGroup);
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        ProductGroupDto productGroup = productGroupService.update(
                getProductGroupDto(1L)
        );

        verify(productGroupRepository).save(any(ProductGroup.class));
        verify(productGroupRepository).findById(anyLong());

        productGroupDtoIsCorrectlyInited(productGroup);
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        productGroupService.deleteById(1L);
        verify(productGroupRepository).deleteById(1L);
    }

    void productGroupDtoIsCorrectlyInited(ProductGroupDto productGroupDto) {
        assertNotNull(productGroupDto, "Fail in passed employee");
        assertNotNull(productGroupDto.getId(), "Fail in field 'id' of employee");
    }


    //Util methods
    EmployeeDto getEmployeeDtoFromRepo(Long id) {
        return new EmployeeDto(id,
                        "LastName",
                        "FirstName",
                        "MiddleName",
                        String.valueOf(id),
                        String.valueOf(id).repeat(11),
                        String.valueOf(id).repeat(12),
                        "Description",
                        "email@email.com",
                        "password");
    }

    ProductGroupDto getProductGroupDto(Long id) {
        return new ProductGroupDto(
                id, "product group dto",
                "00001", id + 1
        );
    }

    ProductGroup getProductGroup(Long id) {
        if(id == 0) return null;

        ProductGroup parentProductGroup = getProductGroup(id - 1);

        return new ProductGroup(id, "Product group", "00001", parentProductGroup);
    }

    List<ProductGroup> getListProductGroup() {
        return Stream.of(
                getProductGroup(1L),
                getProductGroup(2L),
                getProductGroup(3L)
        ).collect(Collectors.toList());
    }
}