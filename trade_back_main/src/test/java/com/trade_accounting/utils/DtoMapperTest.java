package com.trade_accounting.utils;

import com.trade_accounting.models.dto.company.SupplierAccountDto;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.company.SupplierAccount;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import com.trade_accounting.models.entity.warehouse.ProductGroup;
import com.trade_accounting.models.entity.util.Task;
import com.trade_accounting.models.entity.util.TaskComment;
import com.trade_accounting.models.dto.warehouse.ProductGroupDto;
import com.trade_accounting.models.dto.util.TaskCommentDto;
import com.trade_accounting.models.dto.util.TaskDto;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.utils.mapper.company.SupplierAccountMapperImpl;
import com.trade_accounting.utils.mapper.util.TaskCommentMapperImpl;
import com.trade_accounting.utils.mapper.util.TaskMapperImpl;
import com.trade_accounting.utils.mapper.warehouse.ProductGroupMapperImpl;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@Slf4j
class DtoMapperTest {

    @Spy
    private ProductGroupMapperImpl productGroupMapper;

    @Spy
    private SupplierAccountMapperImpl supplierAccountMapper;

    @Spy
    private TaskMapperImpl taskMapper;

    @Spy
    private TaskCommentMapperImpl taskCommentMapper;

    @Test
    void productGroupToProductGroupDto() {
        ProductGroupDto productGroupDto = new ProductGroupDto();
        productGroupDto.setParentId(1L);

        ProductGroup productGroup = productGroupMapper.toModel(productGroupDto);
        assertEquals(productGroupDto.getParentId(), productGroup.getParent().getId());
    }

    @Test
    void supplierAccountToSupplierAccountDtoTest() {
        Company company = new Company();
        company.setId(88L);

        Contract contract = new Contract();
        contract.setId(66L);

        Contractor contractor = new Contractor();
        contractor.setId(77L);

        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);

        SupplierAccount supplierAccount = SupplierAccount.builder()
                .id(1L)
                .company(company)
                .comment("COMMENT")
                .contract(contract)
                .contractor(contractor)
                .date(LocalDateTime.of(2022, Month.APRIL, 6, 20, 35, 5))
                .isSpend(false)
                .isRecyclebin(false)
                .warehouse(warehouse)
                .typeOfInvoice(TypeOfInvoice.EXPENSE)
                .plannedDatePayment(LocalDateTime.of(1914, Month.MAY, 4, 23, 0, 0))
                .build();

        SupplierAccountDto supplierAccountDto = supplierAccountMapper.toDto(supplierAccount);

        assertEquals(supplierAccountDto.getTypeOfInvoice(), supplierAccount.getTypeOfInvoice().name());
        assertEquals(supplierAccountDto.getId(), supplierAccount.getId());
        assertEquals(supplierAccountDto.getCompanyId(), supplierAccount.getCompany().getId());
        assertEquals(supplierAccountDto.getContractId(), supplierAccount.getContract().getId());
        assertEquals(supplierAccountDto.getContractorId(), supplierAccount.getContractor().getId());
        assertEquals(supplierAccountDto.getWarehouseId(), supplierAccount.getWarehouse().getId());
        assertEquals(supplierAccountDto.getPlannedDatePayment(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(supplierAccount.getPlannedDatePayment()));
        assertEquals(supplierAccountDto.getDate(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(supplierAccount.getDate()));
        assertEquals(supplierAccountDto.getComment(), supplierAccount.getComment());
        assertEquals(supplierAccountDto.getIsRecyclebin(), supplierAccount.getIsRecyclebin());
        assertEquals(supplierAccountDto.getIsSpend(), supplierAccount.getIsSpend());
    }

    @Test
    void supplierAccountDtoToSupplierAccountTest() {
        SupplierAccountDto supplierAccountDto = SupplierAccountDto.builder()
                .id(1L)
                .companyId(2L)
                .comment("COMMENT")
                .contractId(22L)
                .contractorId(33L)
                .date("1234-12-12T12:12:12")
                .isSpend(false)
                .isRecyclebin(false)
                .warehouseId(77L)
                .typeOfInvoice(TypeOfInvoice.EXPENSE.name())
                .plannedDatePayment("1234-10-10T10:10:10")
                .build();

        SupplierAccount supplierAccount = supplierAccountMapper.toModel(supplierAccountDto);

        assertEquals(supplierAccountDto.getTypeOfInvoice(), supplierAccount.getTypeOfInvoice().name());
        assertEquals(supplierAccountDto.getId(), supplierAccount.getId());
        assertEquals(supplierAccountDto.getCompanyId(), supplierAccount.getCompany().getId());
        assertEquals(supplierAccountDto.getContractId(), supplierAccount.getContract().getId());
        assertEquals(supplierAccountDto.getContractorId(), supplierAccount.getContractor().getId());
        assertEquals(supplierAccountDto.getWarehouseId(), supplierAccount.getWarehouse().getId());
        assertEquals(supplierAccountDto.getPlannedDatePayment(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(supplierAccount.getPlannedDatePayment()));
        assertEquals(supplierAccountDto.getDate(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(supplierAccount.getDate()));
        assertEquals(supplierAccountDto.getComment(), supplierAccount.getComment());
        assertEquals(supplierAccountDto.getIsRecyclebin(), supplierAccount.getIsRecyclebin());
        assertEquals(supplierAccountDto.getIsSpend(), supplierAccount.getIsSpend());
    }

    @Test
    void taskToTaskDto() {
        TaskDto taskDto = new TaskDto();
        taskDto.setEmployeeId(1L);
        taskDto.setTaskAuthorId(1L);

        Task taskEmployee = taskMapper.taskDtoToTask(taskDto);
        assertEquals(taskDto.getEmployeeId(), taskEmployee.getTaskEmployee().getId());

        Task taskAuthor = taskMapper.taskDtoToTask(taskDto);
        assertEquals(taskDto.getTaskAuthorId(), taskAuthor.getTaskAuthor().getId());
    }

    @Test
    void taskCommentToTaskCommentDto() {
        TaskCommentDto taskCommentDto = new TaskCommentDto();
        taskCommentDto.setPublisherId(1L);
        taskCommentDto.setTaskId(1L);

        TaskComment taskCommentPublisher = taskCommentMapper.toModel(taskCommentDto);
        assertEquals(taskCommentDto.getPublisherId(), taskCommentPublisher.getPublisher().getId());

        TaskComment taskCommentTask = taskCommentMapper.toModel(taskCommentDto);
        assertEquals(taskCommentDto.getTaskId(), taskCommentTask.getTask().getId());

    }
}
