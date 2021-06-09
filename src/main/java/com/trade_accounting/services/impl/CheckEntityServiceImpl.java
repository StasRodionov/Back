package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.BadRequestException;
import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.repositories.AttributeOfCalculationObjectRepository;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractRepository;
import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.CurrencyRepository;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.InvoiceProductRepository;
import com.trade_accounting.repositories.InvoiceRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.PaymentRepository;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.repositories.PostingProductRepository;
import com.trade_accounting.repositories.ProductGroupRepository;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.repositories.SupplierAccountsRepository;
import com.trade_accounting.repositories.TaskCommentRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.repositories.TaxSystemRepository;
import com.trade_accounting.repositories.TechnicalCardGroupRepository;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.repositories.TypeOfContractorRepository;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.repositories.UnitRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.PostingProductService;
import com.trade_accounting.services.interfaces.TechnicalCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CheckEntityServiceImpl implements CheckEntityService {
    private final UnitRepository unitRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final ImageRepository imageRepository;
    private final RoleRepository roleRepository;
    private final WarehouseRepository warehouseRepository;
    private final TaskRepository taskRepository;
    private final TaskCommentRepository taskCommentRepository;
    private final ContractorGroupRepository contractorGroupRepository;
    private final CurrencyRepository currencyRepository;
    private final CompanyRepository companyRepository;
    private final LegalDetailRepository legalDetailRepository;
    private final BankAccountRepository bankAccountRepository;
    private final RetailStoreRepository retailStoreRepository;
    private final TypeOfPriceRepository typeOfPriceRepository;
    private final ProjectRepository projectRepository;
    private final ProductGroupRepository productGroupRepository;
    private final TaxSystemRepository taxSystemRepository;
    private final TypeOfContractorRepository typeOfContractorRepository;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceProductRepository invoiceProductRepository;
    private final ContractRepository contractRepository;
    private final ContractorRepository contractorRepository;
    private final PaymentRepository paymentRepository;
    private final AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository;
    private final SupplierAccountsRepository supplierAccountsRepository;
    private final TechnicalCardGroupRepository technicalCardGroupRepository;
    private  final TechnicalCardRepository technicalCardRepository;
    private final PostingProductRepository postingProductRepository;


    public CheckEntityServiceImpl(UnitRepository unitRepository,
                                  EmployeeRepository employeeRepository,
                                  DepartmentRepository departmentRepository,
                                  PositionRepository positionRepository,
                                  ImageRepository imageRepository,
                                  RoleRepository roleRepository,
                                  WarehouseRepository warehouseRepository,
                                  TaskRepository taskRepository,
                                  RetailStoreRepository retailStoreRepository,
                                  TaskCommentRepository taskCommentRepository,
                                  ContractorGroupRepository contractorGroupRepository,
                                  CurrencyRepository currencyRepository,
                                  CompanyRepository companyRepository,
                                  LegalDetailRepository legalDetailRepository,
                                  BankAccountRepository bankAccountRepository,
                                  TypeOfPriceRepository typeOfPriceRepository,
                                  TypeOfContractorRepository typeOfContractorRepository,
                                  ProjectRepository projectRepository,
                                  ProductGroupRepository productGroupRepository,
                                  TaxSystemRepository taxSystemRepository,
                                  InvoiceRepository invoiceRepository,
                                  InvoiceProductRepository invoiceProductRepository,
                                  ContractRepository contractRepository,
                                  ContractorRepository contractorRepository,
                                  PaymentRepository paymentRepository,
                                  AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository,
                                  SupplierAccountsRepository supplierAccountsRepository,
                                  TechnicalCardGroupRepository technicalCardGroupRepository,
                                  TechnicalCardRepository technicalCardRepository,
                                  PostingProductRepository postingProductRepository) {
        this.unitRepository = unitRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.imageRepository = imageRepository;
        this.roleRepository = roleRepository;
        this.warehouseRepository = warehouseRepository;
        this.taskRepository = taskRepository;
        this.taskCommentRepository = taskCommentRepository;
        this.contractorGroupRepository = contractorGroupRepository;
        this.currencyRepository = currencyRepository;
        this.retailStoreRepository = retailStoreRepository;
        this.companyRepository = companyRepository;
        this.legalDetailRepository = legalDetailRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.typeOfPriceRepository = typeOfPriceRepository;
        this.projectRepository = projectRepository;
        this.productGroupRepository = productGroupRepository;
        this.taxSystemRepository = taxSystemRepository;
        this.typeOfContractorRepository = typeOfContractorRepository;
        this.invoiceRepository = invoiceRepository;
        this.invoiceProductRepository = invoiceProductRepository;
        this.contractRepository = contractRepository;
        this.contractorRepository = contractorRepository;
        this.paymentRepository = paymentRepository;
        this.attributeOfCalculationObjectRepository = attributeOfCalculationObjectRepository;
        this.supplierAccountsRepository = supplierAccountsRepository;
        this.technicalCardGroupRepository = technicalCardGroupRepository;
        this.technicalCardRepository = technicalCardRepository;
        this.postingProductRepository = postingProductRepository;
    }


    @Override
    public void checkExistsUnitById(Long unitId) {
        if (!unitRepository.existsById(unitId)) {
            throw new NotFoundEntityException("Ед. измерения с id=" + unitId + ", не найдена");
        }
    }

    @Override
    public void checkExistsEmployeeById(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new NotFoundEntityException("Сотрудника с id=" + employeeId + ", не найдено");
        }
    }

    @Override
    public void checkExistsTypeOfContractorById(Long typeOfContractorId) {
        if (!typeOfContractorRepository.existsById(typeOfContractorId)) {
            throw new NotFoundEntityException("Контрагента с id=" + typeOfContractorId + ", не найдено");
        }
    }

    @Override
    public void checkExistsProductById(Long productGroupId) {
        if (!productGroupRepository.existsById(productGroupId)) {
            throw new NotFoundEntityException("Продукта с id=" + productGroupId + ", не найдено");
        }
    }

    @Override
    public void checkExistsProjectById(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new NotFoundEntityException("Проекта с id=" + projectId + ", не найдено");
        }
    }

    @Override
    public void checkExistsWarehouseById(Long warehouseId) {
        if (!warehouseRepository.existsById(warehouseId)) {
            throw new NotFoundEntityException("Склада с id=" + warehouseId + ", не найдено");
        }
    }

    @Override
    public void checkExistsAttributeOfCalculationObjectByID(Long attributeOfCalculationObjectId) {
        if (!attributeOfCalculationObjectRepository.existsById(attributeOfCalculationObjectId)) {
            throw new NotFoundEntityException("Склада с id=" + attributeOfCalculationObjectId + ", не найдено");
        }
    }

    @Override
    public void checkForBadEmployee(EmployeeDto employee) {
        DepartmentDto department = employee.getDepartmentDto();
        PositionDto position = employee.getPositionDto();
        ImageDto image = employee.getImageDto();
        Set<RoleDto> roles = employee.getRoleDto();

        boolean isDepartmentFilled = department != null && department.getId() != null;
        boolean isPositionFilled = position != null && position.getId() != null;
        boolean isImageFilled = image != null && image.getId() != null;
        boolean rolesFilled = roles != null && !roles.isEmpty();

        if (isDepartmentFilled && !departmentRepository.existsById(department.getId())) {
            throw new BadRequestException(
                    String.format("Подразделения с id %d не существует", department.getId())
            );
        }

        if (isPositionFilled && !positionRepository.existsById(position.getId())) {
            throw new BadRequestException(
                    String.format("Должности с id %d не существует", position.getId())
            );
        }

        if (isImageFilled && !imageRepository.existsById(image.getId())) {
            throw new BadRequestException(
                    String.format("Изображения с id %d не существует", image.getId())
            );
        }

        if (rolesFilled) {
            for (RoleDto role : roles) {
                boolean isRoleFilled = role != null && role.getId() != null;

                if (isRoleFilled && !roleRepository.existsById(role.getId())) {
                    throw new BadRequestException(
                            String.format("Роли с id %d не существует.", role.getId())
                    );
                }
            }
        }
    }

    @Override
    public void checkExistsTaskById(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new NotFoundEntityException("Задача с id=" + taskId + ", не найдена");
        }
    }

    @Override
    public void checkExistsTaskCommentById(Long taskCommentId) {
        if (!taskCommentRepository.existsById(taskCommentId)) {
            throw new NotFoundEntityException("Комментарий с id=" + taskCommentId + ", не найден");
        }
    }

    @Override
    public void checkExistsContractorGroupById(Long contractorGroupId) {
        if (!contractorGroupRepository.existsById(contractorGroupId)) {
            throw new NotFoundEntityException("Группа контрагентов с id=" + contractorGroupId + " не найдена");
        }
    }

    @Override
    public void checkExistsCurrencyById(Long currencyId) {
        if (!currencyRepository.existsById(currencyId)) {
            throw new NotFoundEntityException("Валюта с id=" + currencyId + ", не найдена");
        }
    }

    @Override
    public void checkExistCompanyById(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new NotFoundEntityException("Компания с id=" + companyId + " не найдена");
        }
    }

    @Override
    public void checkForBadCompany(CompanyDto company) {
        LegalDetailDto legalDetail = company.getLegalDetailDto();
        List<BankAccountDto> bankAccounts = company.getBankAccountDto();

        boolean isLegalDetailFilled = legalDetail != null && legalDetail.getId() != null;
        boolean bankAccountListFilled = bankAccounts != null && !bankAccounts.isEmpty();

        if (isLegalDetailFilled && !legalDetailRepository.existsById(legalDetail.getId())) {
            throw new BadRequestException(
                    String.format("Юридических деталей с id %d не существует", legalDetail.getId())
            );
        }

        if (bankAccountListFilled) {
            for (BankAccountDto bankAccount : bankAccounts) {
                boolean isBankAccountFilled = bankAccount != null && bankAccount.getId() != null;

                if (isBankAccountFilled && !bankAccountRepository.existsById(bankAccount.getId())) {
                    throw new BadRequestException(
                            String.format("Банковского счёта с id %d не существует.", bankAccount.getId())
                    );
                }
            }
        }
    }

    @Override
    public void checkExistsRetailStoreById(Long retailStoreId) {
        if (!retailStoreRepository.existsById(retailStoreId)) {
            throw new NotFoundEntityException("Точка продаж с id=" + retailStoreId + ", не найдена");
        }
    }

    @Override
    public void checkExistsProductGroupById(Long productGrouprId) {
        if (!productGroupRepository.existsById(productGrouprId)) {
            throw new NotFoundEntityException("Группа товаров с id=" + productGrouprId + ", не найдена");
        }
    }


    @Override
    public void checkExistsTypeofPriceByID(Long typeOfPriceId) {
        if (!typeOfPriceRepository.existsById(typeOfPriceId)) {
            throw new NotFoundEntityException("Тип цен с id=" + typeOfPriceId + ", не найдены");
        }
    }

    @Override
    public void checkExistsPositionById(Long positionId) {
        if (!positionRepository.existsById(positionId)) {
            throw new NotFoundEntityException("Список видов цен с id=" + positionId + ", не найден");
        }
    }

    @Override
    public void checkExistsTaxSystemById(Long taxSystemId) {
        if (!taxSystemRepository.existsById(taxSystemId)) {
            throw new NotFoundEntityException("Налоговая система с id=" + taxSystemId + ", не найдена");
        }
    }

    @Override
    public void checkExistsRoleById(Long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new NotFoundEntityException("Роли с id=" + roleId + ", не найдены");
        }
    }

    @Override
    public void checkExistsPaymenById(Long paymenId) {
    }


    @Override
    public void checkExistsPaymentById(Long paymentId) {
        if (!paymentRepository.existsById(paymentId)) {
            throw new NotFoundEntityException("Оплата с id=" + paymentId + ", не найдена");
        }
    }

    @Override
    public void checkExistsInvoiceById(Long invoiceId) {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new NotFoundEntityException("Счет с id=" + invoiceId + ", не найден");
        }
    }

    @Override
    public void checkExistsInvoiceProductById(Long invoiceProductId) {
        if (!invoiceProductRepository.existsById(invoiceProductId)) {
            throw new NotFoundEntityException("Счета-фактура с id=" + invoiceProductId + ", не найдена");
        }
    }

    @Override
    public void checkExistsLegalDetailById(Long legalDetailId) {
        if (!legalDetailRepository.existsById(legalDetailId)) {
            throw new NotFoundEntityException("LegalDetail с id=" + legalDetailId + ", не найдена");
        }
    }

    @Override
    public void checkExistsImageById(Long imageId) {
        if (!imageRepository.existsById(imageId)) {
            throw new NotFoundEntityException("Картинка с id=" + imageId + ", не найдена");
        }
    }

    @Override
    public void checkExistsDepartmentById(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new NotFoundEntityException("Отдел с id=" + departmentId + ", не найден");
        }
    }

    @Override
    public void checkExistsContractById(Long contractId) {
        if (!contractRepository.existsById(contractId)) {
            throw new NotFoundEntityException("Контракт с id=" + contractId + ", не найден");
        }
    }

    @Override
    public void checkExistsContractorById(Long contractorId) {
        if (!contractorRepository.existsById(contractorId)) {
            throw new NotFoundEntityException("Подрядчик с id=" + contractorId + ", не найден");
        }
    }

    @Override
    public void checkExistsBankAccountById(Long bankAccountId) {
        if (!bankAccountRepository.existsById(bankAccountId)) {
            throw new NotFoundEntityException("Банковский счет с id=" + bankAccountId + ", не найден");
        }
    }

    @Override
    public void checkExistsSupplierAccountsById(Long supplierAccountsId) {
        if(!supplierAccountsRepository.existsById(supplierAccountsId)) {
            throw new NotFoundEntityException("Счет поставщика с id=" + supplierAccountsId + ", не найден");
        }
    }

    @Override
    public void checkExistsTechnicalCardById(Long id) {
        if(!technicalCardRepository.existsById(id)) {
            throw new NotFoundEntityException("Тех. карта с id=" + id + ", не найдена");
        }
    }

    @Override
    public void checkExistsTechnicalCardGroupById(Long id) {
        if(!technicalCardGroupRepository.existsById(id)) {
            throw new NotFoundEntityException("Тех. карта с id=" + id + ", не найдена");
        }
    }

    @Override
    public void checkExistsPostingProductById(Long id) {
        if(!postingProductRepository.existsById(id)) {
            throw new NotFoundEntityException("Оприходование  с id=" + id + ", не найдено");
        }
    }
}
