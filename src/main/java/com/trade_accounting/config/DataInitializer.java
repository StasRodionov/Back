package com.trade_accounting.config;

import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.dto.AccessParametersDto;
import com.trade_accounting.models.dto.AddressDto;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.models.dto.BalanceAdjustmentDto;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContactDto;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.models.dto.ContractorStatusDto;
import com.trade_accounting.models.dto.CorrectionDto;
import com.trade_accounting.models.dto.CorrectionProductDto;
import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.InventarizationDto;
import com.trade_accounting.models.dto.InventarizationProductDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.models.dto.MovementDto;
import com.trade_accounting.models.dto.MovementProductDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.models.dto.ProductPriceDto;
import com.trade_accounting.models.dto.ProjectDto;
import com.trade_accounting.models.dto.RemainDto;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.models.dto.ReturnToSupplierDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.models.dto.TaskCommentDto;
import com.trade_accounting.models.dto.TaskDto;
import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.models.dto.TechnicalCardDto;
import com.trade_accounting.models.dto.TechnicalCardGroupDto;
import com.trade_accounting.models.dto.TechnicalCardProductionDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.services.impl.AddressServiceImpl;
import com.trade_accounting.services.interfaces.AccessParametersService;
import com.trade_accounting.services.interfaces.AttributeOfCalculationObjectService;
import com.trade_accounting.services.interfaces.BalanceAdjustmentService;
import com.trade_accounting.services.interfaces.BankAccountService;
import com.trade_accounting.services.interfaces.CompanyService;
import com.trade_accounting.services.interfaces.ContactService;
import com.trade_accounting.services.interfaces.ContractService;
import com.trade_accounting.services.interfaces.ContractorGroupService;
import com.trade_accounting.services.interfaces.ContractorService;
import com.trade_accounting.services.interfaces.ContractorStatusService;
import com.trade_accounting.services.interfaces.CorrectionProductService;
import com.trade_accounting.services.interfaces.CorrectionService;
import com.trade_accounting.services.interfaces.CurrencyService;
import com.trade_accounting.services.interfaces.DepartmentService;
import com.trade_accounting.services.interfaces.EmployeeService;
import com.trade_accounting.services.interfaces.InventarizationProductService;
import com.trade_accounting.services.interfaces.InventarizationService;
import com.trade_accounting.services.interfaces.InvoiceProductService;
import com.trade_accounting.services.interfaces.InvoiceService;
import com.trade_accounting.services.interfaces.LegalDetailService;
import com.trade_accounting.services.interfaces.MovementProductService;
import com.trade_accounting.services.interfaces.MovementService;
import com.trade_accounting.services.interfaces.PaymentService;
import com.trade_accounting.services.interfaces.PositionService;
import com.trade_accounting.services.interfaces.ProductGroupService;
import com.trade_accounting.services.interfaces.ProductService;
import com.trade_accounting.services.interfaces.ProjectService;
import com.trade_accounting.services.interfaces.RemainService;
import com.trade_accounting.services.interfaces.RetailStoreService;
import com.trade_accounting.services.interfaces.ReturnToSupplierService;
import com.trade_accounting.services.interfaces.RoleService;
import com.trade_accounting.services.interfaces.SupplierAccountService;
import com.trade_accounting.services.interfaces.TaskCommentService;
import com.trade_accounting.services.interfaces.TaskService;
import com.trade_accounting.services.interfaces.TaxSystemService;
import com.trade_accounting.services.interfaces.TechnicalCardGroupService;
import com.trade_accounting.services.interfaces.TechnicalCardService;
import com.trade_accounting.services.interfaces.TypeOfContractorService;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import com.trade_accounting.services.interfaces.UnitService;
import com.trade_accounting.services.interfaces.WarehouseService;
import com.trade_accounting.services.interfaces.fias.FiasDbService;
import com.trade_accounting.utils.fias.ExcelParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final TypeOfPriceService typeOfPriceService;
    private final RoleService roleService;
    private final UnitService unitService;
    private final PositionService positionService;
    private final WarehouseService warehouseService;
    private final AttributeOfCalculationObjectService attributeOfCalculationObjectService;
    private final DepartmentService departmentService;
    private final ContractorGroupService contractorGroupService;
    private final TypeOfContractorService typeOfContractorService;
    private final TaxSystemService taxSystemService;
    private final ProductGroupService productGroupService;
    private final CompanyService companyService;
    private final LegalDetailService legalDetailService;
    private final ContactService contactService;
    private final ContractService contractService;
    private final ContractorService contractorService;
    private final BankAccountService bankAccountService;
    private final EmployeeService employeeService;
    private final ProductService productService;
    private final CurrencyService currencyService;
    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final ProjectService projectService;
    private final PaymentService paymentService;
    private final TaskService taskService;
    private final TaskCommentService commentService;
    private final AddressServiceImpl addressService;
    private final FiasDbService fiasDbService;
    private final RetailStoreService retailStoreService;
    private final ContractorStatusService contractorStatusService;
    private final AccessParametersService accessParametersService;
    private final TechnicalCardGroupService technicalCardGroupService;
    private final TechnicalCardService technicalCardService;
    private final CorrectionProductService correctionProductService;
    private final CorrectionService correctionService;
    private final ReturnToSupplierService returnToSupplierService;
    private final InventarizationService inventarizationService;
    private final InventarizationProductService inventarizationProductService;
    private final BalanceAdjustmentService balanceAdjustmentService;
    private final SupplierAccountService supplierAccountService;
    private final MovementService movementService;
    private final MovementProductService movementProductService;
    private final RemainService remainService;

    @PostConstruct
    public void init() {
        fiasDbService.createAll(ExcelParser.getModelsForDb());
        initAddresses();
        initTypeOfPrices();
        initContractorGroups();
        initTypeOfContractors();
        initBankAccounts();
        initRoles();
        initWarehouses();
        initUnits();
        initPositions();
        initAttributeOfCalculationObjects();
        initDepartments();
        initTaxSystems();
        initProductGroups();
        initCurrency();
        initLegalDetails();
        initCompanies();
        initContacts();
        initEmployees();
        initContractorStatuses();
        initAccessParameters();
        initContractors();
        initProducts();
        initContracts();
        initInvoices();
        initInvoiceProducts();
        initProject();
//        initPayment();
        initTasks();
        initTaskComments();
        initRetailStores();
        initTechnicalCardGroups();
//        initTechnicalCards();
        initCorrectionProduct();
        initCorrection();
        initReturnToSuppliers();
        initInventarizationProduct();
        initInventarization();
        initBalanceAdjustment();
        initSupplierAccount();
        initMovementProduct();
        initMovement();
        initRemain();
    }

    private void initMovementProduct() {
        for (Long i = 1L; i <= 12; i++) {
            movementProductService.create(
                    new MovementProductDto(null, i, BigDecimal.valueOf(randomInt(50, 100)),
                            BigDecimal.valueOf(randomInt(50, 100)))
            );
        }
    }

    private void initMovement() {
        movementService.create(
                new MovementDto(
                        null, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        1L, 2L,
                        1L, false, false,
                        "Перемещение 1",
                        List.of(1L, 2L, 3L)
                )
        );
        movementService.create(
                new MovementDto(
                        null, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        1L, 3L,
                        2L, false, false,
                        "Перемещение 2",
                        List.of(4L, 5L, 6L)
                )
        );
        movementService.create(
                new MovementDto(
                        null, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        2L, 1L,
                        1L, true, false,
                        "Перемещение 3",
                        List.of(7L, 8L, 9L)
                )
        );
    }

    private void initAccessParameters() {
        accessParametersService.create(new AccessParametersDto(null, false, 1L, 1L));
        accessParametersService.create(new AccessParametersDto(null, false, 2L, 2L));
        accessParametersService.create(new AccessParametersDto(null, false, 3L, 3L));
        accessParametersService.create(new AccessParametersDto(null, false, 4L, 4L));
        accessParametersService.create(new AccessParametersDto(null, false, 5L, 5L));
        accessParametersService.create(new AccessParametersDto(null, false, 1L, 1L));
        accessParametersService.create(new AccessParametersDto(null, false, 2L, 2L));
        accessParametersService.create(new AccessParametersDto(null, false, 3L, 3L));
        accessParametersService.create(new AccessParametersDto(null, false, 4L, 4L));
        accessParametersService.create(new AccessParametersDto(null, false, 5L, 5L));
    }

    public void initRetailStores() {
        retailStoreService.create(
                new RetailStoreDto(
                        null, "Магазин 1", true,
                        "Онлайн", BigDecimal.valueOf(10000),
                        1L, "SI", "ОСН",
                        "ОСН", List.of(1L, 2L, 3L)
                )
        );
        retailStoreService.create(
                new RetailStoreDto(
                        null, "Магазин 2", true,
                        "Был в сети вчера", BigDecimal.valueOf(20000),
                        2L, "SI", "ОСН2",
                        "УСН. Доход", List.of(1L, 2L, 3L)
                )
        );
        retailStoreService.create(
                new RetailStoreDto(
                        null, "Магазин 3", true,
                        "Был в сети 2 часа назад", BigDecimal.valueOf(15700),
                        3L, "SI", "ОСН2",
                        "ЕСХН", List.of(1L, 2L, 3L)
                )
        );
    }

    public void initProject() {

        projectService.create(new ProjectDto(null, "name", "0000", "description"));
        projectService.create(new ProjectDto(null, "name2", "0000", "description"));
        projectService.create(new ProjectDto(null, "name3", "0000", "description"));
        projectService.create(new ProjectDto(null, "name4", "0000", "description"));
    }

//    public void initPayment() {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        List<CompanyDto> companyDtos = companyService.getAll().stream().limit(3).collect(Collectors.toList());
//        List<ContractorDto> contractorDtos = contractorService.getAll().stream().limit(3).collect(Collectors.toList());
//        List<ProjectDto> projectDtos = projectService.getAll().stream().limit(3).collect(Collectors.toList());
//        List<ContractDto> contractDtos = contractService.getAll().stream().limit(3).collect(Collectors.toList());
//        int count = 1;
//        for (CompanyDto companyDto : companyDtos) {
//            for (ContractorDto contractorDto : contractorDtos) {
//                for (ContractDto contractDto : contractDtos) {
//                    for (ProjectDto projectDto : projectDtos) {
//                        paymentService.create(new PaymentDto(
//                                null,
//                                TypeOfPayment.INCOMING,
//                                PaymentMethods.CASH,
//                                "0000" + count,
//                                localDateTime,
//                                companyDto.getId(),
//                                contractorDto.getId(),
//                                contractDto.getId(),
//                                projectDto.getId(),
//                                new BigDecimal("100.00")
//                        ));
//                        count++;
//                        paymentService.create(new PaymentDto(
//                                null,
//                                TypeOfPayment.OUTGOING,
//                                PaymentMethods.BANK,
//                                "0000" + count,
//                                localDateTime,
//                                companyDto.getId(),
//                                contractorDto.getId(),
//                                contractDto.getId(),
//                                projectDto.getId(),
//                                new BigDecimal("50.00")
//                        ));
//                        count++;
//                    }
//                }
//            }
//        }
//    }

    public void initInvoices() {
        List<CompanyDto> companyDtos = companyService.getAll().stream().limit(3).collect(Collectors.toList());
        List<ContractorDto> contractorDtos = contractorService.getAll().stream().limit(3).collect(Collectors.toList());
        List<WarehouseDto> warehouseDtos = warehouseService.getAll().stream().limit(3).collect(Collectors.toList());
        List<String> typeOfInvoices = List.of(TypeOfInvoice.EXPENSE.name(), TypeOfInvoice.RECEIPT.name());

        int i = 0;
        for (CompanyDto companyDto : companyDtos) {
            for (ContractorDto contractorDto : contractorDtos) {
                for (WarehouseDto warehouseDto : warehouseDtos) {
                    invoiceService.create(new InvoiceDto(
                            null,
                            "Комментарий " + i++,
                            LocalDateTime.now().toString(),
                            typeOfInvoices.get(randomInt(0, 1)),
                            companyDto,
                            contractorDto,
                            warehouseDto,
                            false));
                }
            }
        }
    }

    private void initInvoiceProducts() {
        List<InvoiceDto> invoices = invoiceService.getAll();

        for (InvoiceDto invoice : invoices) {
            for (int i = 0; i < randomInt(1, 10); i++) {
                invoiceProductService.create(new InvoiceProductDto(
                        null,
                        invoice.getId(),
                        Long.valueOf(randomInt(1, 1000)),
                        BigDecimal.valueOf(randomInt(20, 100)),
                        BigDecimal.valueOf(randomInt(30, 150))
                ));
            }
        }
    }

    public int randomInt(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    private void initTypeOfPrices() {
        typeOfPriceService.create(new TypeOfPriceDto(null, "Оптовая цена", "1"));
        typeOfPriceService.create(new TypeOfPriceDto(null, "Розничная цена", "2"));
    }

    private void initContractorGroups() {
        contractorGroupService.create(new ContractorGroupDto(null, "Покупатель", "1"));
        contractorGroupService.create(new ContractorGroupDto(null, "Поставщик", "2"));
    }

    private void initTypeOfContractors() {
        typeOfContractorService.create(new TypeOfContractorDto(null, "Юридическое лицо", "1"));
        typeOfContractorService.create(new TypeOfContractorDto(null, "Индивидуальный предприниматель", "2"));
        typeOfContractorService.create(new TypeOfContractorDto(null, "Физическое лицо", "3"));
    }

    private void initBankAccounts() {
        bankAccountService.create(new BankAccountDto(
                null,
                "14593",
                "Сбербанк",
                "Москва ул. Ленина",
                "30101643600000000957",
                "42605998100001234567",
                true,
                "1"));
        bankAccountService.create(new BankAccountDto(
                null, "55320",
                "Газпромбанк",
                "Москва ул. Катина",
                "30101643600000000123",
                "40702643100007654321",
                true,
                "2"));
        bankAccountService.create(new BankAccountDto(
                null, "54354",
                "Промсвязьбанк",
                "Москва ул. Петровка",
                "30101643600000000456",
                "4070264310000876543",
                true,
                "3"));
        bankAccountService.create(new BankAccountDto(
                null, "86758",
                "ВТБ",
                "Москва ул. Охотный ряд",
                "30101643600000000789",
                "40702643100008876543",
                true,
                "4"));
        bankAccountService.create(new BankAccountDto(
                null, "28394",
                "Совкомбанк",
                "Москва ул. Ильинка",
                "30101643600000000012",
                "40702643100008210987",
                true,
                "5"));
        bankAccountService.create(new BankAccountDto(
                null, "80209",
                "Россельхозбанк",
                "Москва ул. Маросейка",
                "30101643600000000345",
                "40702643100008654321",
                true,
                "6"));
        bankAccountService.create(new BankAccountDto(
                null, "90624",
                "ВТБ",
                "Москва ул. Воздвиженка",
                "30101643600000000678",
                "40702643100008098765",
                true,
                "7"));
        bankAccountService.create(new BankAccountDto(
                null, "27895",
                "Россельхозбанк",
                "Москва ул. Покровка",
                "30101643600000901234",
                "40702643100008432109",
                true,
                "8"));
        bankAccountService.create(new BankAccountDto(
                null, "80972",
                "Газпромбанк",
                "Москва ул. Старая Басманная",
                "30101643600000567890",
                "40702643100008876543",
                true,
                "9"));
        bankAccountService.create(new BankAccountDto(
                null, "897323",
                "Сбербанк",
                "Москва ул. Ильинка",
                "30101643600000123456",
                "40702643100008210987",
                true,
                "10"));
        bankAccountService.create(new BankAccountDto(
                null, "0938343",
                "Газпромбанк",
                "Москва ул. Воздвиженка",
                "30101643600000789012",
                "4070264310000654321",
                true,
                "11"));
        bankAccountService.create(new BankAccountDto(
                null, "8936234",
                "Совкомбанк",
                "Москва ул. Петровка",
                "30101643600000345678",
                "4070264310000098765",
                true,
                "12"));
        bankAccountService.create(new BankAccountDto(
                null, "90843",
                "Альфа-Банк",
                "Москва ул. Петровка",
                "30101643600000901234",
                "4070264310000432109",
                true,
                "13"));
        bankAccountService.create(new BankAccountDto(
                null, "19872",
                "Сбербанк",
                "Москва ул. Ильинка",
                "30101643600000123456",
                "40702643100008987654",
                true,
                "14"));
        bankAccountService.create(new BankAccountDto(
                null, "09836",
                "Газпромбанк",
                "Москва ул. Воздвиженка",
                "30101643600000567890",
                "4070264310000321098",
                true,
                "15"));
        bankAccountService.create(new BankAccountDto(
                null, "12098",
                "Совкомбанк",
                "Москва ул. Петровка",
                "30101643600000123456",
                "4070264310000765432",
                true,
                "16"));
        bankAccountService.create(new BankAccountDto(
                null, "09863",
                "Альфа-Банк",
                "Москва ул. Петровка",
                "30101643600000789012",
                "4070264310000109876",
                true,
                "17"));
        bankAccountService.create(new BankAccountDto(
                null, "19872",
                "Сбербанк",
                "Москва ул. Ильинка",
                "30101643600000345678",
                "40702643100008543210",
                true,
                "18"));
        bankAccountService.create(new BankAccountDto(
                null, "93094",
                "Газпромбанк",
                "Москва ул. Воздвиженка",
                "30101643600000901234",
                "4070264310000987654",
                true,
                "19"));
        bankAccountService.create(new BankAccountDto(
                null, "21039",
                "Совкомбанк",
                "Москва ул. Петровка",
                "30101643600000567890",
                "4070264310000321098",
                true,
                "20"));
        bankAccountService.create(new BankAccountDto(
                null, "89743",
                "ВТБ",
                "Москва ул. Петровка",
                "3010164360000123456",
                "4070264310000765432",
                true,
                "21"));
    }

    private void initRoles() {
        roleService.create(RoleDto.builder()
                .name("admin")
                .sortNumber("1")
                .build()
        );
        roleService.create(RoleDto.builder()
                .name("user")
                .sortNumber("2")
                .build()
        );
    }

    private void initWarehouses() {
        warehouseService.create(new WarehouseDto("Основной склад", "1"));
        warehouseService.create(new WarehouseDto("Основной склад1", "1"));
        warehouseService.create(new WarehouseDto("Основной склад2", "1"));
        warehouseService.create(new WarehouseDto("Основной склад3", "1"));
    }

    private void initUnits() {
        unitService.create(new UnitDto("мм", "Миллиметр", "1"));
        unitService.create(new UnitDto("см", "Сантиметр", "2"));
        unitService.create(new UnitDto("дм", "Дециметр", "3"));
        unitService.create(new UnitDto("м", "Метр", "4"));
        unitService.create(new UnitDto("км; 10^3 м", "Километр; тысяча метров", "5"));
        unitService.create(new UnitDto("Мм; 10^6 м", "Мегаметр; миллион метров", "6"));
        unitService.create(new UnitDto("пог. м", "Погонный метр", "7"));
        unitService.create(new UnitDto("дюйм", "Дюйм (25,4 мм)", "8"));
        unitService.create(new UnitDto("фут", "Фут (0,3048 м)", "9"));
        unitService.create(new UnitDto("ярд", "Ярд (0,9144 м)", "10"));
        unitService.create(new UnitDto("миля", "Морская миля (1852 м)", "11"));
        unitService.create(new UnitDto("мм2", "Квадратный миллиметр", "12"));
        unitService.create(new UnitDto("см2", "Квадратный сантиметр", "13"));
        unitService.create(new UnitDto("дм2", "Квадратный дециметр", "14"));
        unitService.create(new UnitDto("м2", "Квадратный метр", "15"));
        unitService.create(new UnitDto("10^3 м^2", "Тысяча квадратных метров", "16"));
        unitService.create(new UnitDto("га", "Гектар", "17"));
        unitService.create(new UnitDto("км2", "Квадратный километр", "18"));
        unitService.create(new UnitDto("дюйм2", "Квадратный дюйм (645,16 мм2)", "19"));
        unitService.create(new UnitDto("фут2", "Квадратный фут (0,092903 м2)", "20"));
        unitService.create(new UnitDto("ярд2", "Квадратный ярд (0,8361274 м2)", "21"));
        unitService.create(new UnitDto("а", "Ар (100 м2)", "22"));
        unitService.create(new UnitDto("мм3", "Кубический миллиметр", "23"));
        unitService.create(new UnitDto("см3; мл", "Кубический сантиметр; миллилитр", "24"));
        unitService.create(new UnitDto("л; дм3", "Литр; кубический дециметр", "25"));
        unitService.create(new UnitDto("м3", "Кубический метр", "26"));
        unitService.create(new UnitDto("дл", "Децилитр", "27"));
        unitService.create(new UnitDto("гл", "Гектолитр", "28"));
        unitService.create(new UnitDto("Мл", "Мегалитр", "29"));
        unitService.create(new UnitDto("дюйм3", "Кубический дюйм (16387,1 мм3)", "30"));
        unitService.create(new UnitDto("фут3", "Кубический фут (0,02831685 м3)", "31"));
        unitService.create(new UnitDto("ярд3", "Кубический ярд (0,764555 м3)", "32"));
        unitService.create(new UnitDto("10^6 м3", "Миллион кубических метров", "33"));
        unitService.create(new UnitDto("гг", "Гектограмм", "34"));
        unitService.create(new UnitDto("мг", "Миллиграмм", "35"));
        unitService.create(new UnitDto("кар", "Метрический карат", "36"));
        unitService.create(new UnitDto("г", "Грамм", "37"));
        unitService.create(new UnitDto("кг", "Килограмм", "38"));
        unitService.create(new UnitDto("т", "Тонна; метрическая тонна (1000 кг)", "39"));
        unitService.create(new UnitDto("10^3 т", "Килотонна", "40"));
        unitService.create(new UnitDto("сг", "Сантиграмм", "41"));
        unitService.create(new UnitDto("БРТ", "Брутто-регистровая тонна (2,8316 м3)", "42"));
        unitService.create(new UnitDto("т грп", "Грузоподъемность в метрических тоннах", "43"));
        unitService.create(new UnitDto("ц", "Центнер (метрический) (100 кг); гектокилограмм; квинтал1 (метрический); децитонна", "44"));
        unitService.create(new UnitDto("с", "Секунда", "45"));
        unitService.create(new UnitDto("мин", "Минута", "46"));
        unitService.create(new UnitDto("ч", "Час", "47"));
        unitService.create(new UnitDto("сут; дн", "Сутки", "48"));
        unitService.create(new UnitDto("нед", "Неделя", "49"));
        unitService.create(new UnitDto("дек", "Декада", "50"));
        unitService.create(new UnitDto("мес", "Месяц", "51"));
        unitService.create(new UnitDto("кварт", "Квартал", "52"));
        unitService.create(new UnitDto("полгода", "Полугодие", "53"));
        unitService.create(new UnitDto("г; лет", "Год", "54"));
        unitService.create(new UnitDto("деслет", "Десятилетие", "55"));
        unitService.create(new UnitDto("рулон", "Рулон", "56"));
        unitService.create(new UnitDto("шт", "Штука", "57"));
        unitService.create(new UnitDto("ящ", "Ящик", "58"));
        unitService.create(new UnitDto("блок", "Блок сигарет", "59"));
    }

    private void initPositions() {
        positionService.create(new PositionDto("Генеральный директор", "1"));
        positionService.create(new PositionDto("Коммерческий директор", "2"));
        positionService.create(new PositionDto("Финансовый директор", "3"));
        positionService.create(new PositionDto("Директор по продажам", "4"));
        positionService.create(new PositionDto("Технический директор", "5"));
        positionService.create(new PositionDto("Главный бухгалтер", "6"));
        positionService.create(new PositionDto("Начальник отдела снабжения", "7"));
        positionService.create(new PositionDto("Начальник отдела продаж", "8"));
        positionService.create(new PositionDto("Начальник транспортного отдела", "9"));
        positionService.create(new PositionDto("Начальник отдела маркетинга", "10"));
        positionService.create(new PositionDto("Начальник складского комплекса", "11"));
        positionService.create(new PositionDto("Бухгалтер", "12"));
        positionService.create(new PositionDto("Менеджер по закупкам", "13"));
        positionService.create(new PositionDto("Менеджер по продажам", "14"));
        positionService.create(new PositionDto("Кладовщик", "15"));
        positionService.create(new PositionDto("Грузчик", "16"));
        positionService.create(new PositionDto("Маркетолог", "17"));
        positionService.create(new PositionDto("Водитель", "18"));
        positionService.create(new PositionDto("Кассир", "19"));
        positionService.create(new PositionDto("Уборщица", "20"));
    }

    private void initAttributeOfCalculationObjects() {
        attributeOfCalculationObjectService.create(new AttributeOfCalculationObjectDto("Услуга", "1", true));
        attributeOfCalculationObjectService.create(new AttributeOfCalculationObjectDto("Работа", "2", true));
        attributeOfCalculationObjectService.create(new AttributeOfCalculationObjectDto("Предоставление РИД", "3", true));
        attributeOfCalculationObjectService.create(new AttributeOfCalculationObjectDto("Составной предмет расчета", "4", true));
        attributeOfCalculationObjectService.create(new AttributeOfCalculationObjectDto("Иной предмет расчета", "5", true));
        attributeOfCalculationObjectService.create(new AttributeOfCalculationObjectDto("Товар", "6", false));
        attributeOfCalculationObjectService.create(new AttributeOfCalculationObjectDto("Подакцизный товар", "7", false));
        attributeOfCalculationObjectService.create(new AttributeOfCalculationObjectDto("Составной предмет расчета", "8", false));
        attributeOfCalculationObjectService.create(new AttributeOfCalculationObjectDto("Иной предмет расчета", "9", false));
    }

    private void initDepartments() {
        departmentService.create(new DepartmentDto("Руководство", "1"));
        departmentService.create(new DepartmentDto("Отдел бухгалтерии", "2"));
        departmentService.create(new DepartmentDto("Отдел закупок", "3"));
        departmentService.create(new DepartmentDto("Отдел продаж", "4"));
        departmentService.create(new DepartmentDto("Складской комплекс", "5"));
        departmentService.create(new DepartmentDto("Транспортный отдел", "6"));
        departmentService.create(new DepartmentDto("Финансовый отдел", "7"));
        departmentService.create(new DepartmentDto("Отдел маркетинга", "8"));
        departmentService.create(new DepartmentDto("HR отдел", "9"));
    }

    private void initTaxSystems() {
        taxSystemService.create(new TaxSystemDto("ОСН", "1"));
        taxSystemService.create(new TaxSystemDto("УСН.Доход", "2"));
        taxSystemService.create(new TaxSystemDto("УСН.Доход-Расход", "3"));
        taxSystemService.create(new TaxSystemDto("ЕСХН", "4"));
        taxSystemService.create(new TaxSystemDto("ЕНВД", "5"));
        taxSystemService.create(new TaxSystemDto("Патент", "6"));
    }

    private void initProductGroups() {

        ProductGroupDto productGroup1 = new ProductGroupDto("Товарная группа №1", "1");
        ProductGroupDto productGroup2 = new ProductGroupDto("Товарная группа №2", "2");
        ProductGroupDto productGroup3 = new ProductGroupDto("Товарная группа №3", "3");
        ProductGroupDto productGroup4 = new ProductGroupDto("Товарная группа №4", "4");
        ProductGroupDto productGroup5 = new ProductGroupDto("Товарная группа №5", "5");

        productGroupService.create(productGroup1);
        productGroupService.create(productGroup2);
        productGroupService.create(productGroup3);
        productGroupService.create(productGroup4);
        productGroupService.create(productGroup5);

        ProductGroupDto productGroup6 = new ProductGroupDto("Товарная группа №6", "6", productGroup1.getId());
        ProductGroupDto productGroup7 = new ProductGroupDto("Товарная группа №7", "7", productGroup6.getId());
        ProductGroupDto productGroup8 = new ProductGroupDto("Товарная группа №8", "8", productGroup2.getId());
        ProductGroupDto productGroup9 = new ProductGroupDto("Товарная группа №9", "9", productGroup7.getId());
        ProductGroupDto productGroup10 = new ProductGroupDto("Товарная группа №10", "10", productGroup3.getId());

        productGroupService.create(productGroup6);
        productGroupService.create(productGroup7);
        productGroupService.create(productGroup8);
        productGroupService.create(productGroup9);
        productGroupService.create(productGroup10);

        ProductGroupDto productGroup11 = new ProductGroupDto("Товарная группа №11", "11", productGroup8.getId());
        ProductGroupDto productGroup12 = new ProductGroupDto("Товарная группа №12", "12", productGroup4.getId());
        ProductGroupDto productGroup13 = new ProductGroupDto("Товарная группа №13", "13", productGroup9.getId());
        ProductGroupDto productGroup14 = new ProductGroupDto("Товарная группа №14", "14", productGroup5.getId());
        ProductGroupDto productGroup15 = new ProductGroupDto("Товарная группа №15", "15", productGroup10.getId());

        productGroupService.create(productGroup11);
        productGroupService.create(productGroup12);
        productGroupService.create(productGroup13);
        productGroupService.create(productGroup14);
        productGroupService.create(productGroup15);

    }

    private void initCurrency() {
        currencyService.create(new CurrencyDto(null, "rubles", "Russian Rubles", "25", "rub", "1"));
        currencyService.create(new CurrencyDto(null, "bel rubles", "Bellarusian Rubles", "25", "belrub", "2"));
        currencyService.create(new CurrencyDto(null, "eng dollar", "USA Dollars ", "25", "dol", "3"));
    }

    private void initLegalDetails() {
        legalDetailService.create(new LegalDetailDto(
                null,
                "Иванов",
                "Михаил",
                "Сергеевич",
                addressService.getById(3L).getId(),
                "comment to address",
                "3664069397",
                "79271669",
                "1053600591197",
                "236467", "432145",
                LocalDate.of(2020, 10, 10).toString(),
                typeOfContractorService.getByName("Юридическое лицо").getId()
        ));
        legalDetailService.create(new LegalDetailDto(
                null,
                "Гордон",
                "Андрей",
                "Анатольевич",
                addressService.getById(2L).getId(),
                "comment to address",
                "3664069439", "34271669",
                "79271647",
                "1053600591285",
                "432145",
                LocalDate.of(2018, 2, 23).toString(),
                typeOfContractorService.getByName("Индивидуальный предприниматель").getId()
        ));
        legalDetailService.create(new LegalDetailDto(
                null,
                "Сергеева",
                "Мария",
                "Дмитриевна",
                addressService.getById(3L).getId(),
                "comment to address",
                "3664055588",
                "35259831",
                "70713032",
                "1033600141277",
                "342145",
                LocalDate.of(2022, 4, 5).toString(),
                typeOfContractorService.getByName("Физическое лицо").getId()
        ));
    }

    private void initCompanies() {
        for (int i = 0; i < 110; i++) {
            companyService.create(new CompanyDto(
                    null,
                    "OOO \"Организация №1\"",
                    "7712345" + String.format("%03d", i),
                    String.format("%05d", 1 + 3 * i),
                    "749512345678",
                    "810-41-1234567890",
                    "organization" + 1 * (i + 1) + "@mail.com",
                    true,
                    addressService.create(new AddressDto("123456", "г.Москва", "ул. Подвойского", "д.14, стр.7")).getId(),
                    "something comment",
                    "Петров Сергей Петрович",
                    "Manager",
                    "leader signature",
                    "Сергеев Петр Сергеевич",
                    "chief signature",
                    "stamp",
                    legalDetailService.create(new LegalDetailDto(
                            null,
                            "Иванов",
                            "Михаил",
                            "Сергеевич",
                            3L,
                            "comment to address",
                            "861234" + String.format("%04d", i),
                            "79271669",
                            "1053600591197",
                            "236467", null, null,
                            typeOfContractorService.getByName("Юридическое лицо").getId()
                    )).getId(),
                    List.of(bankAccountService.create(new BankAccountDto(
                                    null,
                                    "14593",
                                    "Сбербанк",
                                    "Москва ул. Ленина",
                                    "30101643600000000957",
                                    "42605998100001234567",
                                    true,
                                    "1")).getId(),
                            bankAccountService.create(new BankAccountDto(
                                    null, "55320",
                                    "Газпромбанк",
                                    "Москва ул. Катина",
                                    "30101643600000000123",
                                    "40702643100007654321",
                                    true,
                                    "2")).getId())
            ));
            companyService.create(new CompanyDto(
                    null,
                    "OOO \"Организация №2\"",
                    "9543564" + String.format("%03d", i),
                    String.format("%05d", 2 + 3 * i),
                    "733126789654",
                    "920-12-2365723233",
                    "organization" + 2 * (i + 1) + "@mail.com",
                    true,
                    addressService.create(new AddressDto("123498", "г. Москва", "ул. Тверская", "д. 20")).getId(),
                    "something comment",
                    "Иванова Мария Сергеевна",
                    "Executive director",
                    "leader signature",
                    "Соболев Николай Андреевич",
                    "chief signature",
                    "stamp",
                    legalDetailService.create(new LegalDetailDto(
                            null,
                            "Гордон",
                            "Андрей",
                            "Анатольевич",
                            addressService.getById(2L).getId(),
                            "comment to address",
                            "7712347" + String.format("%03d", i), null,
                            "79271647",
                            "1053600591285",
                            "432145",
                            LocalDate.of(2018, 2, 23).toString(),
                            typeOfContractorService.getByName("Индивидуальный предприниматель").getId()
                    )).getId(),

                    List.of(bankAccountService.create(new BankAccountDto(
                                    null,
                                    "14593",
                                    "Сбербанк",
                                    "Москва ул. Ленина",
                                    "30101643600000000957",
                                    "42605998100001234567",
                                    true,
                                    "1")).getId(),
                            bankAccountService.create(new BankAccountDto(
                                    null, "55320",
                                    "Газпромбанк",
                                    "Москва ул. Катина",
                                    "30101643600000000123",
                                    "40702643100007654321",
                                    true,
                                    "2")).getId())
            ));

            companyService.create(new CompanyDto(
                    null,
                    "OOO \"Организация №3\"",
                    "3453123" + String.format("%03d", i),
                    String.format("%05d", 3 + 3 * i),
                    "799123786542",
                    "543-23-1234543221",
                    "organization" + 3 * (i + 1) + "@mail.com",
                    true,
                    addressService.create(new AddressDto("432156", "г. Самара", "ул. Гагарина", "д. 18")).getId(),
                    "something comment",
                    "Сергеева Ксения Андреевна",
                    "Project manager",
                    "leader signature",
                    "Стрелецкая Анастасия Михайловна",
                    "chief signature",
                    "stamp",
                    legalDetailService.create(new LegalDetailDto(
                            null,
                            "Сергеева",
                            "Мария",
                            "Дмитриевна",
                            addressService.getById(1L).getId(),
                            "comment to address",
                            "7712348" + String.format("%03d", i),
                            null,
                            "70713032",
                            "1033600141277",
                            "342145",
                            LocalDate.of(2022, 4, 5).toString(),
                            typeOfContractorService.getByName("Индивидуальный предприниматель").getId()
                    )).getId(),
                    List.of(bankAccountService.create(new BankAccountDto(
                                    null,
                                    "14593",
                                    "Сбербанк",
                                    "Москва ул. Ленина",
                                    "30101643600000000957",
                                    "40702643100007654321",
                                    true,
                                    "1")).getId(),
                            bankAccountService.create(new BankAccountDto(
                                    null, "55320",
                                    "Газпромбанк",
                                    "Москва ул. Катина",
                                    "30101643600000000123",
                                    "42605998100001234567",
                                    true,
                                    "2")).getId())));
        }
    }

    private void initEmployees() {
        employeeService.create(new EmployeeDto(null,
                "Vasiliev",
                "Vasya",
                "Vasilievich",
                "1",
                "+7(999)111-22-33",
                "526317984689",
                "Some special text about Vasya",
                "vasyaogon@mail.ru",
                "12345",
                departmentService.getByName("Руководство"),
                positionService.getByName("Генеральный директор"),
                Collections.singleton(roleService.getByName("admin")),
                null));
        employeeService.create(new EmployeeDto(null,
                "Simonova",
                "Sima",
                "Semenovna",
                "2",
                "+7(999)222-11-33",
                "526317984678",
                "Some special text about Sima",
                "simaogon@mail.ru",
                "54321",
                departmentService.getByName("Отдел бухгалтерии"),
                positionService.getByName("Коммерческий директор"),
                Collections.singleton(roleService.getByName("user")),
                null));
        employeeService.create(new EmployeeDto(null,
                "Belive",
                "Vera",
                "Henrichovna",
                "3",
                "+7(999)777-11-33",
                "526317555678",
                "Some special text about Vera",
                "veraogon@mail.ru",
                "76543",
                departmentService.getByName("Складской комплекс"),
                positionService.getByName("Технический директор"),
                Collections.singleton(roleService.getByName("user")),
                null));
        employeeService.create(new EmployeeDto(null,
                "Islentiev",
                "Karim",
                "Dmitrievich",
                "4",
                "+7(999)222-77-00",
                "526316666678",
                "Some special text about Karim",
                "karimogon@mail.ru",
                "qwerty",
                departmentService.getByName("Отдел продаж"),
                positionService.getByName("Директор по продажам"),
                Collections.singleton(roleService.getByName("admin")),
                null));
        employeeService.create(new EmployeeDto(null,
                "Petko",
                "Sasha",
                "",
                "5",
                "+7(999)222-00-33",
                "526317984600",
                "Some special text about Sasha",
                "sashaogon@mail.ru",
                "asdfg",
                departmentService.getByName("Отдел продаж"),
                positionService.getByName("Кассир"),
                Collections.singleton(roleService.getByName("user")),
                null));
    }

    private void initAddresses() {
        addressService.create(
                AddressDto.builder()
                        .index("123456")
                        .country("Россия")
                        .region("Московская")
                        .city("Москва")
                        .street("ул. Ленина")
                        .house("11")
                        .apartment("15")
                        .build()
        );
        addressService.create(
                AddressDto.builder()
                        .index("188678")
                        .country("USA")
                        .region("Колумбия")
                        .city("Вашингтон")
                        .street("ул. 5я Авеню")
                        .house("6")
                        .apartment("22")
                        .build()
        );
        addressService.create(
                AddressDto.builder()
                        .index("123456")
                        .country("Panama")
                        .region("Область")
                        .city("Столица Панамы")
                        .street("ул. Индейцев")
                        .house("2")
                        .apartment("1")
                        .build()
        );
    }

    private void initContractorStatuses() {
        contractorStatusService.create(new ContractorStatusDto(1L, "Новый"));
        contractorStatusService.create(new ContractorStatusDto(2L, "Выслано предложение"));
        contractorStatusService.create(new ContractorStatusDto(3L, "Переговоры"));
        contractorStatusService.create(new ContractorStatusDto(4L, "Сделка заключена"));
        contractorStatusService.create(new ContractorStatusDto(5L, "Сделка не заключена"));
    }

    private void initContractors() {
        contractorService.create(new ContractorDto(
                null,
                "Торговый Дом \"Перекресток\", ЗАО",
                "1",
                "8 (495) 232-59-24",
                "8 (495) 232-59-24",
                "alena.pechnikova@x5.ru",
                addressService.getById(1L).getId(),
                "1 comment to address",
                "comment",
                "1234-5678-9012-3456",
                List.of(1L, 2L),
                contractorGroupService.getById(1L).getId(),
                typeOfPriceService.getById(1L).getId(),
                List.of(1L, 2L),
                legalDetailService.getById(1L).getId(),
                contractorStatusService.getById(1L).getId(),
                accessParametersService.getById(1L).getId())
        );

        contractorService.create(new ContractorDto(
                null,
                "Агроаспект, ООО",
                "2",
                "8 (800) 555-55-05",
                "8 (800) 555-55-05",
                "inbox@5ka.ru",
                addressService.getById(2L).getId(),
                "2comment to address",
                "2comment",
                "7890-1234-5678-9012",
                new ArrayList<>(),
                contractorGroupService.getById(1L).getId(),
                typeOfPriceService.getById(2L).getId(),
                List.of(3L, 4L),
                legalDetailService.getById(1L).getId(),
                contractorStatusService.getById(2L).getId(),
                accessParametersService.getById(2L).getId())
        );
        contractorService.create(new ContractorDto(
                null,
                "Вкусвилл, ООО",
                "3",
                "8 (495) 981-13-45",
                "8 (495) 981-13-45",
                "info@izbenka.msk.ru",
                addressService.getById(3L).getId(),
                "3comment to address",
                "3comment",
                "3456-7890-1234-5678",
                new ArrayList<>(),
                contractorGroupService.getById(1L).getId(),
                typeOfPriceService.getById(1L).getId(),
                List.of(5L, 6L),
                legalDetailService.getById(1L).getId(),
                contractorStatusService.getById(3L).getId(),
                accessParametersService.getById(3L).getId())
        );
        contractorService.create(new ContractorDto(
                null,
                "Альфа-М, ООО",
                "4",
                "8 (495) 981-31-85",
                "8 (495) 981-31-85",
                "zholudeva.ksyusha@mail.ru",
                addressService.getById(1L).getId(),
                "4comment to address",
                "4comment",
                "9012-3456-7890-1234",
                new ArrayList<>(),
                contractorGroupService.getById(1L).getId(),
                typeOfPriceService.getById(2L).getId(),
                List.of(7L, 8L),
                legalDetailService.getById(1L).getId(),
                contractorStatusService.getById(4L).getId(),
                accessParametersService.getById(4L).getId())
        );
        contractorService.create(new ContractorDto(
                null,
                "Отдохни - 77, ООО",
                "5",
                "8 (495) 326-30-00",
                "8 (495) 326-30-00",
                "nina.chehovich@msk.nfretail.ru",
                addressService.getById(2L).getId(),
                "5comment to address",
                "5comment",
                "5678-9012-3456-7890",
                new ArrayList<>(),
                contractorGroupService.getById(1L).getId(),
                typeOfPriceService.getById(1L).getId(),
                List.of(9L, 10L),
                legalDetailService.getById(1L).getId(),
                contractorStatusService.getById(5L).getId(),
                accessParametersService.getById(5L).getId())
        );
        contractorService.create(new ContractorDto(
                null,
                "Продмир, ООО",
                "6",
                "8 (495) 651-92-52",
                "8 (495) 651-92-52",
                "d.gorobtsova@etpgpb.ru",
                addressService.getById(3L).getId(),
                "6comment to address",
                "6comment",
                "1234-5678-9012-3456",
                new ArrayList<>(),
                contractorGroupService.getById(1L).getId(),
                typeOfPriceService.getById(2L).getId(),
                List.of(11L, 12L),
                legalDetailService.getById(1L).getId(),
                contractorStatusService.getById(1L).getId(),
                accessParametersService.getById(6L).getId())
        );
        contractorService.create(new ContractorDto(
                null,
                "Зельгрос, ООО",
                "7",
                "8 (495) 741-45-56",
                "8 (495) 741-45-56",
                "tatiana.onishchenko@selgros.ru",
                addressService.getById(1L).getId(),
                "7comment to address",
                "7comment",
                "7890-1234-5678-9012",
                new ArrayList<>(),
                contractorGroupService.getById(1L).getId(),
                typeOfPriceService.getById(1L).getId(),
                List.of(13L, 14L),
                legalDetailService.getById(1L).getId(),
                contractorStatusService.getById(2L).getId(),
                accessParametersService.getById(7L).getId())
        );
        contractorService.create(new ContractorDto(
                null,
                "Лабиринт-М, ООО",
                "8",
                "8 (495) 155-51-56",
                "8 (495) 155-51-56",
                "info@krasnoeibeloe.ru",
                addressService.getById(2L).getId(),
                "8comment to address",
                "8comment",
                "3456-7890-1234-5678",
                new ArrayList<>(),
                contractorGroupService.getById(1L).getId(),
                typeOfPriceService.getById(2L).getId(),
                List.of(15L, 16L),
                legalDetailService.getById(1L).getId(),
                contractorStatusService.getById(3L).getId(),
                accessParametersService.getById(8L).getId())
        );
        contractorService.create(new ContractorDto(
                null,
                "Эскорт Сервис, ООО",
                "9",
                "8 (495) 755-11-16",
                "8 (495) 755-11-16",
                "harlanov.aleksandr@escort-servis.ru",
                addressService.getById(3L).getId(),
                "9comment to address",
                "9comment",
                "9012-3456-7890-1234",
                new ArrayList<>(),
                contractorGroupService.getById(1L).getId(),
                typeOfPriceService.getById(1L).getId(),
                List.of(17L, 18L),
                legalDetailService.getById(1L).getId(),
                contractorStatusService.getById(4L).getId(),
                accessParametersService.getById(9L).getId())
        );
        contractorService.create(new ContractorDto(
                null,
                "Арома Маркет, ООО",
                "10",
                "8 (495) 777-51-95",
                "8 (495) 777-51-95",
                "sales11_am@aroma.ru",
                addressService.getById(1L).getId(),
                "10comment to address",
                "10comment",
                "5678-9012-3456-7890",
                new ArrayList<>(),
                contractorGroupService.getById(1L).getId(),
                typeOfPriceService.getById(2L).getId(),
                List.of(19L, 20L),
                legalDetailService.getById(1L).getId(),
                contractorStatusService.getById(5L).getId(),
                accessParametersService.getById(10L).getId())
        );
    }

    private void initProducts() {

        List<UnitDto> unitDtoList = new ArrayList<>(unitService.getAll());

        List<TaxSystemDto> taxSystemDtoList = new ArrayList<>(taxSystemService.getAll());

        List<ContractorDto> contractorDtoList = new ArrayList<>(contractorService.getAll());

        List<ProductGroupDto> productGroupDtoList = new ArrayList<>(productGroupService.getAll());

        List<AttributeOfCalculationObjectDto> attributeOfCalculationObjectDtoList = new ArrayList<>(attributeOfCalculationObjectService.getAll());

        for (int i = 0; i < 350; i++) {

            productService.create(new ProductDto(
                    null,
                    "Яблоки" + i,
                    new BigDecimal("1.0"),
                    new BigDecimal("1.0"),
                    new BigDecimal("11.111"),
                    "Красные яблоки голден" + i,
                    unitDtoList.get(0),
                    false,
                    contractorDtoList.get(0),
                    List.of(new ProductPriceDto(null, 0L, BigDecimal.valueOf(randomInt(50, 70))),
                            new ProductPriceDto(null, 1L, BigDecimal.valueOf(randomInt(71, 100)))),
                    taxSystemDtoList.get(0),
                    null,
                    productGroupDtoList.get(0),
                    attributeOfCalculationObjectDtoList.get(0)
            ));
            productService.create(new ProductDto(
                    null,
                    "Бананы" + i,
                    new BigDecimal("1.0"),
                    new BigDecimal("1.0"),
                    new BigDecimal("22.222"),
                    "Красные Бананы голден" + i,
                    unitDtoList.get(1),
                    false,
                    contractorDtoList.get(1),
                    List.of(new ProductPriceDto(null, 0L, BigDecimal.valueOf(randomInt(70, 90))),
                            new ProductPriceDto(null, 1L, BigDecimal.valueOf(randomInt(91, 115)))),
                    taxSystemDtoList.get(1),
                    null,
                    productGroupDtoList.get(1),
                    attributeOfCalculationObjectDtoList.get(1)
            ));
            productService.create(new ProductDto(
                    null,
                    "Мандарины" + i,
                    new BigDecimal("1.0"),
                    new BigDecimal("1.0"),
                    new BigDecimal("33.333"),
                    "Красные Мандарины голден" + i,
                    unitDtoList.get(2),
                    false,
                    contractorDtoList.get(1),
                    List.of(
                            new ProductPriceDto(null, 0L, BigDecimal.valueOf(randomInt(80, 100))),
                            new ProductPriceDto(null, 1L, BigDecimal.valueOf(randomInt(101, 121)))),
                    taxSystemDtoList.get(2),
                    null,
                    productGroupDtoList.get(2),
                    attributeOfCalculationObjectDtoList.get(2)
            ));
        }
    }

    private void initContacts() {
        contactService.create(
                ContactDto.builder()
                        .fullName("Иванов Иван Иванович")
                        .position("Контактное лицо 1")
                        .phone("+777-777-77-77")
                        .email("email1@mail.ru")
                        .comment("Коментарий 1")
                        .build()
        );
        contactService.create(
                ContactDto.builder()
                        .fullName("Алексеев Алексей Алексеевич")
                        .position("Контактное лицо 2")
                        .phone("+888-888-88-88")
                        .email("email2@mail.ru")
                        .comment("Коментарий 2")
                        .build()
        );
        contactService.create(
                ContactDto.builder()
                        .fullName("Гэри Стивен Возняк")
                        .position("Контактное лицо 3")
                        .phone("+999-999-99-99")
                        .email("email3@mail.ru")
                        .comment("Коментарий 3")
                        .build()
        );
    }

    private void initContracts() {
        contractService.create(new ContractDto(
                null,
                "1",
                LocalDate.now(),
                1L,
                3L,
                1L,
                BigDecimal.valueOf(200),
                false,
                "no comments",
                1L));

        contractService.create(new ContractDto(
                null,
                "2",
                LocalDate.now(),
                1L,
                3L,
                1L,
                BigDecimal.valueOf(200),
                false,
                "no comments",
                1L));

        contractService.create(new ContractDto(
                null,
                "3",
                LocalDate.now(),
                1L,
                3L,
                1L,
                BigDecimal.valueOf(200),
                false,
                "no comments",
                1L));

        contractService.create(new ContractDto(
                null,
                "4",
                LocalDate.now(),
                1L,
                3L,
                1L,
                BigDecimal.valueOf(200),
                false,
                "no comments",
                1L));

    }

    private void initTasks() {
        Random rnd = new Random();
        String descriptionFormat = "Описание задачи номер %d.";

        var employeeIds = employeeService.getAll().stream().mapToLong(EmployeeDto::getId).toArray();
        var idCount = employeeIds.length;

        var tasks = new ArrayList<TaskDto>();
        TaskDto dto;
        for (int i = 0; i < 20; i++) {
            dto = new TaskDto();

            dto.setDescription(String.format(descriptionFormat, i));
            dto.setEmployeeId(employeeIds[rnd.nextInt(idCount)]);
            dto.setTaskAuthorId(employeeIds[rnd.nextInt(idCount)]);
            dto.setCreationDateTime(LocalDateTime.now().minusDays(rnd.nextInt(100)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            dto.setDeadlineDateTime(LocalDateTime.now().plusDays(rnd.nextInt(100)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            dto.setCompleted(rnd.nextBoolean());

            tasks.add(dto);
        }

        taskService.createAll(tasks);
    }

    private void initTaskComments() {
        Random rnd = new Random();
        String commentFormat = "Комментарий к задаче %d. Комментарий номер %d";

        var tasks = taskService.getAll()
                .stream()
                .filter(task -> rnd.nextBoolean())
                .collect(Collectors.toList());

        var employeeIds = employeeService.getAll()
                .stream()
                .mapToLong(EmployeeDto::getId)
                .toArray();

        var commentDTOs = new ArrayList<TaskCommentDto>();

        for (TaskDto dto : tasks) {
            var commentCount = rnd.nextInt(5);
            for (int i = 0; i < commentCount; i++) {
                var commentDTO = new TaskCommentDto();
                commentDTO.setCommentContent(String.format(commentFormat, dto.getId(), i));
                commentDTO.setPublisherId(employeeIds[rnd.nextInt(employeeIds.length)]);
                commentDTO.setPublishedDateTime(LocalDateTime.
                        parse(dto.getCreationDateTime())
                        .plusHours(rnd.nextInt(25)).toString());
                commentDTO.setTaskId(dto.getId());
                commentDTOs.add(commentDTO);
            }
        }

        commentService.createAll(commentDTOs);

    }

    public void initTechnicalCardGroups() {
        technicalCardGroupService.create(new TechnicalCardGroupDto(null, "Группа технических карт №1", "Комментарий1", "1"));
        technicalCardGroupService.create(new TechnicalCardGroupDto(null, "Группа технических карт №2", "Комментарий2", "2"));
    }

//    public void initTechnicalCards() {
//        technicalCardService.create(new TechnicalCardDto(
//                null,
//                "Техническая карта №1",
//                "Комментарий1",
//                "1000",
//                technicalCardGroupService.getById(1L),
//                List.of(new TechnicalCardProductionDto(null, 2L, 1L),
//                        new TechnicalCardProductionDto(null, 2L, 2L)),
//                List.of(new TechnicalCardProductionDto(null, 2L, 3L),
//                        new TechnicalCardProductionDto(null, 2L, 4L))));
//        technicalCardService.create(new TechnicalCardDto(
//                null,
//                "Техническая карта №2",
//                "Комментарий2",
//                "1100",
//                technicalCardGroupService.getById(1L),
//                List.of(new TechnicalCardProductionDto(null, 2L, 5L),
//                        new TechnicalCardProductionDto(null, 2L, 6L)),
//                List.of(new TechnicalCardProductionDto(null, 2L, 7L),
//                        new TechnicalCardProductionDto(null, 2L, 8L))));
//        technicalCardService.create(new TechnicalCardDto(
//                null,
//                "Техническая карта №3",
//                "Комментарий3",
//                "1200",
//                technicalCardGroupService.getById(2L),
//                List.of(new TechnicalCardProductionDto(null, 2L, 9L),
//                        new TechnicalCardProductionDto(null, 2L, 10L)),
//                List.of(new TechnicalCardProductionDto(null, 2L, 11L),
//                        new TechnicalCardProductionDto(null, 2L, 12L))));
//        technicalCardService.create(new TechnicalCardDto(
//                null,
//                "Техническая карта №4",
//                "Комментарий4",
//                "1300",
//                technicalCardGroupService.getById(2L),
//                List.of(new TechnicalCardProductionDto(null, 2L, 13L),
//                        new TechnicalCardProductionDto(null, 2L, 14L)),
//                List.of(new TechnicalCardProductionDto(null, 2L, 15L),
//                        new TechnicalCardProductionDto(null, 2L, 16L))));
//    }

    public void initCorrectionProduct() {
        for (Long i = 1L; i <= 12; i++) {
            correctionProductService.create(
                    new CorrectionProductDto(null, i, BigDecimal.valueOf(randomInt(50, 100)),
                            BigDecimal.valueOf(randomInt(50, 100)))
            );
        }
    }

    public void initCorrection() {
        correctionService.create(
                new CorrectionDto(
                        null, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        1L, 1L,
                        true, false, false,
                        "Оприходование 1",
                        List.of(1L, 2L, 3L)
                )
        );
        correctionService.create(
                new CorrectionDto(
                        null, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        1L, 5L,
                        false, false, false,
                        "Оприходование 2",
                        List.of(4L, 5L, 6L)
                )
        );
        correctionService.create(
                new CorrectionDto(
                        null, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        1L, 10L,
                        false, true, false,
                        "Оприходование 3",
                        List.of(7L, 8L, 9L)
                )
        );
    }

    public void initReturnToSuppliers() {
        returnToSupplierService.create(ReturnToSupplierDto.builder()
                .id(1L)
                .date(LocalDateTime.now().toString())
                .comment("Комментарий 1")
                .contractorId(1L)
                .contractId(1L)
                .warehouseId(1L)
                .companyId(1L)
                .isPrint(false)
                .isSend(false)
                .build());
        returnToSupplierService.create(ReturnToSupplierDto.builder()
                .id(2L)
                .date(LocalDateTime.now().toString())
                .comment("Комментарий 2")
                .contractorId(2L)
                .contractId(1L)
                .warehouseId(1L)
                .companyId(2L)
                .isPrint(false)
                .isSend(false)
                .build());
        returnToSupplierService.create(ReturnToSupplierDto.builder()
                .id(3L)
                .date(LocalDateTime.now().toString())
                .comment("Комментарий 3")
                .contractorId(3L)
                .contractId(1L)
                .warehouseId(1L)
                .companyId(3L)
                .isPrint(false)
                .isSend(false)
                .build());
        returnToSupplierService.create(ReturnToSupplierDto.builder()
                .id(4L)
                .date(LocalDateTime.now().toString())
                .comment("Комментарий 4")
                .contractorId(4L)
                .contractId(1L)
                .warehouseId(1L)
                .companyId(4L)
                .isPrint(false)
                .isSend(false)
                .build());
        returnToSupplierService.create(ReturnToSupplierDto.builder()
                .id(5L)
                .date(LocalDateTime.now().toString())
                .comment("Комментарий 5")
                .contractorId(5L)
                .contractId(1L)
                .warehouseId(1L)
                .companyId(5L)
                .isPrint(false)
                .isSend(false)
                .build());
    }

    public void initInventarizationProduct() {
        for (Long i = 1L; i <= 12; i++) {
            inventarizationProductService.create(
                    new InventarizationProductDto(null, i, BigDecimal.valueOf(randomInt(50, 100)),
                            BigDecimal.valueOf(randomInt(50, 100)))
            );
        }
    }

    public void initInventarization() {
        inventarizationService.create(
                new InventarizationDto(
                        null, "2021-06-29 14:14",
                        1L, 1L,
                        false, "Инвентаризация 1",
                        List.of(1L, 2L, 3L)
                )
        );
        inventarizationService.create(
                new InventarizationDto(
                        null, "2021-06-29 14:14",
                        1L, 1L,
                        false, "Инвентаризация 2",
                        List.of(4L, 5L, 6L)
                )
        );
        inventarizationService.create(
                new InventarizationDto(
                        null, "2021-06-29 14:14",
                        1L, 1L,
                        false, "Инвентаризация 3",
                        List.of(7L, 8L, 9L)
                )
        );
    }

    public void initBalanceAdjustment() {
        balanceAdjustmentService.create(BalanceAdjustmentDto.builder()
                .id(1L)
                .date(LocalDateTime.now().toString())
                .companyId(1L)
                .contractorId(1L)
                .account("Расчетный счет 1")
                .cashOffice("Касса организации 1")
                .comment("Комментарий 1")
                .dateChanged(LocalDateTime.now().toString())
                .whoChanged("Иванов")
                .build());
        balanceAdjustmentService.create(BalanceAdjustmentDto.builder()
                .id(2L)
                .date(LocalDateTime.now().toString())
                .companyId(2L)
                .contractorId(2L)
                .account("Расчетный счет 2")
                .cashOffice("Касса организации 2")
                .comment("Комментарий 2")
                .dateChanged(LocalDateTime.now().toString())
                .whoChanged("Петров")
                .build());
        balanceAdjustmentService.create(BalanceAdjustmentDto.builder()
                .id(3L)
                .date(LocalDateTime.now().toString())
                .companyId(3L)
                .contractorId(3L)
                .account("Расчетный счет 3")
                .cashOffice("Касса организации 3")
                .comment("Комментарий 3")
                .dateChanged(LocalDateTime.now().toString())
                .whoChanged("Сидоров")
                .build());
        balanceAdjustmentService.create(BalanceAdjustmentDto.builder()
                .id(4L)
                .date(LocalDateTime.now().toString())
                .companyId(4L)
                .contractorId(4L)
                .account("Расчетный счет 4")
                .cashOffice("Касса организации 4")
                .comment("Комментарий 4")
                .dateChanged(LocalDateTime.now().toString())
                .whoChanged("Кузнецов")
                .build());
        balanceAdjustmentService.create(BalanceAdjustmentDto.builder()
                .id(5L)
                .date(LocalDateTime.now().toString())
                .companyId(5L)
                .contractorId(5L)
                .account("Расчетный счет 5")
                .cashOffice("Касса организации 5")
                .comment("Комментарий 5")
                .dateChanged(LocalDateTime.now().toString())
                .whoChanged("Попов")
                .build());
    }

    private void initSupplierAccount() {
        supplierAccountService.create(SupplierAccountDto.builder()
                .id(1L)
                .date(LocalDateTime.now().toString())
                .comment("Комментарий 1")
                .isSpend(false)
                .companyId(1L)
                .warehouseId(1L)
                .contractId(1L)
                .contractorId(1L).build());
        supplierAccountService.create(SupplierAccountDto.builder()
                .id(2L)
                .date(LocalDateTime.now().toString())
                .comment("Комментарий 2")
                .isSpend(false)
                .companyId(2L)
                .warehouseId(1L)
                .contractId(1L)
                .contractorId(2L).build());
        supplierAccountService.create(SupplierAccountDto.builder()
                .id(3L)
                .date(LocalDateTime.now().toString())
                .comment("Комментарий 3")
                .isSpend(false)
                .companyId(3L)
                .warehouseId(1L)
                .contractId(1L)
                .contractorId(3L).build());
        supplierAccountService.create(SupplierAccountDto.builder()
                .id(4L)
                .date(LocalDateTime.now().toString())
                .comment("Комментарий 4")
                .isSpend(false)
                .companyId(4L)
                .warehouseId(1L)
                .contractId(1L)
                .contractorId(4L).build());
        supplierAccountService.create(SupplierAccountDto.builder()
                .id(5L)
                .date(LocalDateTime.now().toString())
                .comment("Комментарий 5")
                .isSpend(false)
                .companyId(5L)
                .warehouseId(1L)
                .contractId(1L)
                .contractorId(5L).build());
    }


    private void initRemain() {
        remainService.create(RemainDto.builder()
                .id(1L)
                .name("Remain one")
                .vendorCode("234789")
                .balance(randomInt(20000, 100000))
                .irreducibleBalance(randomInt(20000, 100000))
                .reserve(randomInt(20000, 100000))
                .expectation(randomInt(20000, 100000))
                .available(randomInt(20000, 100000))
                .unitId(38L)
                .daysOnWarehouse(randomInt(5, 20))
                .costPrice(randomInt(20000, 100000))
                .sumOfCostPrice(randomInt(20000, 100000))
                .salesCost(randomInt(20000, 100000))
                .salesSum(randomInt(20000, 100000))
                .build());

        remainService.create(RemainDto.builder()
                .id(2L)
                .name("Remain two")
                .vendorCode("451238")
                .balance(randomInt(20000, 100000))
                .irreducibleBalance(randomInt(20000, 100000))
                .reserve(randomInt(20000, 100000))
                .expectation(randomInt(20000, 100000))
                .available(randomInt(20000, 100000))
                .unitId(38L)
                .daysOnWarehouse(randomInt(5, 20))
                .costPrice(randomInt(20000, 100000))
                .sumOfCostPrice(randomInt(20000, 100000))
                .salesCost(randomInt(20000, 100000))
                .salesSum(randomInt(20000, 100000))
                .build());

        remainService.create(RemainDto.builder()
                .id(3L)
                .name("Remain three")
                .vendorCode("624380")
                .balance(randomInt(20000, 100000))
                .irreducibleBalance(randomInt(20000, 100000))
                .reserve(randomInt(20000, 100000))
                .expectation(randomInt(20000, 100000))
                .available(randomInt(20000, 100000))
                .unitId(38L)
                .daysOnWarehouse(randomInt(5, 20))
                .costPrice(randomInt(20000, 100000))
                .sumOfCostPrice(randomInt(20000, 100000))
                .salesCost(randomInt(20000, 100000))
                .salesSum(randomInt(20000, 100000))
                .build());
    }
}
