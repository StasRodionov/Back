package com.trade_accounting.config;

import com.trade_accounting.models.Payment;
import com.trade_accounting.models.ProductGroup;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.TypeOfPayment;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.models.dto.ProjectDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.services.interfaces.AttributeOfCalculationObjectService;
import com.trade_accounting.services.interfaces.BankAccountService;
import com.trade_accounting.services.interfaces.CompanyService;
import com.trade_accounting.services.interfaces.ContractService;
import com.trade_accounting.services.interfaces.ContractorGroupService;
import com.trade_accounting.services.interfaces.ContractorService;
import com.trade_accounting.services.interfaces.CurrencyService;
import com.trade_accounting.services.interfaces.DepartmentService;
import com.trade_accounting.services.interfaces.EmployeeService;
import com.trade_accounting.services.interfaces.ImageService;
import com.trade_accounting.services.interfaces.InvoiceService;
import com.trade_accounting.services.interfaces.LegalDetailService;
import com.trade_accounting.services.interfaces.PaymentService;
import com.trade_accounting.services.interfaces.PositionService;
import com.trade_accounting.services.interfaces.ProductGroupService;
import com.trade_accounting.services.interfaces.ProductService;
import com.trade_accounting.services.interfaces.ProjectService;
import com.trade_accounting.services.interfaces.RoleService;
import com.trade_accounting.services.interfaces.TaxSystemService;
import com.trade_accounting.services.interfaces.TypeOfContractorService;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import com.trade_accounting.services.interfaces.UnitService;
import com.trade_accounting.services.interfaces.WarehouseService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
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
    private final ContractService contractService;
    private final ContractorService contractorService;
    private final BankAccountService bankAccountService;
    private final EmployeeService employeeService;
    private final ImageService imageService;
    private final ProductService productService;
    private final CurrencyService currencyService;
    private final InvoiceService invoiceService;
    private final ProjectService projectService;
    private final PaymentService paymentService;


    public DataInitializer(
            TypeOfPriceService typeOfPriceService,
            RoleService roleService,
            UnitService unitService,
            PositionService positionService,
            WarehouseService warehouseService,
            AttributeOfCalculationObjectService attributeOfCalculationObjectService,
            DepartmentService departmentService,
            ContractorGroupService contractorGroupService,
            TaxSystemService taxSystemService,
            ProductGroupService productGroupService,
            TypeOfContractorService typeOfContractorService,
            CompanyService companyService,
            LegalDetailService legalDetailService,
            ContractService contractService,
            ContractorService contractorService,
            BankAccountService bankAccountService,
            EmployeeService employeeService,
            ImageService imageService,
            ProductService productService,
            CurrencyService currencyService,
            InvoiceService invoiceService,
            ProjectService projectService,
            PaymentService paymentService) {
        this.typeOfPriceService = typeOfPriceService;
        this.roleService = roleService;
        this.warehouseService = warehouseService;
        this.unitService = unitService;
        this.positionService = positionService;
        this.attributeOfCalculationObjectService = attributeOfCalculationObjectService;
        this.departmentService = departmentService;
        this.contractorGroupService = contractorGroupService;
        this.typeOfContractorService = typeOfContractorService;
        this.taxSystemService = taxSystemService;
        this.productGroupService = productGroupService;
        this.companyService = companyService;
        this.legalDetailService = legalDetailService;
        this.contractService = contractService;
        this.contractorService = contractorService;
        this.bankAccountService = bankAccountService;
        this.employeeService = employeeService;
        this.imageService = imageService;
        this.productService = productService;
        this.currencyService = currencyService;
        this.invoiceService = invoiceService;
        this.projectService = projectService;
        this.paymentService = paymentService;
    }

    @PostConstruct
    public void init() {
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
        //initImage

        initLegalDetails();
        initCompanies();
        initEmployees();
        initContractors();
        initProducts();
        initContracts();
        initInvoices();
        initProject();
        initPayment();
    }

    public void initProject(){
        projectService.create(new ProjectDto(null, "name", "0000", "description"));
    }

    public void initPayment(){
        LocalDateTime localDateTime = LocalDateTime.now();
        List<CompanyDto> companyDtos = companyService.getAll().stream().limit(3).collect(Collectors.toList());
        List<ContractorDto> contractorDtos = contractorService.getAll().stream().limit(3).collect(Collectors.toList());
        List<ProjectDto> projectDtos = projectService.getAll().stream().limit(3).collect(Collectors.toList());
        List<ContractDto> contractDtos = contractService.getAll().stream().limit(3).collect(Collectors.toList());
        int count = 1;
        for (CompanyDto companyDto: companyDtos) {
            for (ContractorDto contractorDto: contractorDtos) {
                for (ContractDto contractDto: contractDtos) {
                    for (ProjectDto projectDto : projectDtos) {
                        paymentService.create(new PaymentDto(
                                null,
                                TypeOfPayment.INCOMING,
                                "0000" + count,
                                localDateTime,
                                companyDto.getId(),
                                contractorDto.getId(),
                                contractDto.getId(),
                                projectDto.getId(),
                                new BigDecimal("100.00")
                                ));
                        count ++;
                    }
                }
            }
        }
    }

    public void initInvoices(){
        List<CompanyDto> companyDtos = companyService.getAll().stream().limit(3).collect(Collectors.toList());
        List<ContractorDto> contractorDtos = contractorService.getAll().stream().limit(3).collect(Collectors.toList());
        List<WarehouseDto> warehouseDtos = warehouseService.getAll().stream().limit(3).collect(Collectors.toList());

        for (CompanyDto companyDto : companyDtos) {
            for (ContractorDto contractorDto : contractorDtos) {
                for (WarehouseDto warehouseDto : warehouseDtos) {
                    invoiceService.create(new InvoiceDto(
                            null,
                            LocalDateTime.now().toString(),
                            TypeOfInvoice.EXPENSE.name(),
                            companyDto,
                            contractorDto,
                            warehouseDto,
                            false));
                }
            }
        }
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
                "correspondent account",
                "account",
                true,
                "1"));
        bankAccountService.create(new BankAccountDto(
                null, "55320",
                "Газпромбанк",
                "Москва ул. Катина",
                "correspondent account",
                "account",
                true,
                "2"));
    }

    private void initRoles() {
        roleService.create(new RoleDto("admin", "1"));
        roleService.create(new RoleDto("user", "2"));
    }

    private void initWarehouses() {
        warehouseService.create(new WarehouseDto("Основной склад", "1"));
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

        ProductGroup productGroup1 = new ProductGroup("Товарная группа №1", "1");
        ProductGroup productGroup2 = new ProductGroup("Товарная группа №2", "2");
        ProductGroup productGroup3 = new ProductGroup("Товарная группа №3", "3");
        ProductGroup productGroup4 = new ProductGroup("Товарная группа №4", "4");
        ProductGroup productGroup5 = new ProductGroup("Товарная группа №5", "5");

        productGroupService.create(productGroup1);
        productGroupService.create(productGroup2);
        productGroupService.create(productGroup3);
        productGroupService.create(productGroup4);
        productGroupService.create(productGroup5);

        ProductGroup productGroup6 = new ProductGroup("Товарная группа №6", "6", productGroup1);
        productGroupService.create(productGroup6);
        ProductGroup productGroup7 = new ProductGroup("Товарная группа №7", "7", productGroup6);
        productGroupService.create(productGroup7);
        ProductGroup productGroup8 = new ProductGroup("Товарная группа №8", "8", productGroup2);
        productGroupService.create(productGroup8);
        ProductGroup productGroup9 = new ProductGroup("Товарная группа №9", "9", productGroup7);
        productGroupService.create(productGroup9);
        ProductGroup productGroup10 = new ProductGroup("Товарная группа №10", "10", productGroup3);
        productGroupService.create(productGroup10);

        ProductGroup productGroup11 = new ProductGroup("Товарная группа №11", "11", productGroup8);
        ProductGroup productGroup12 = new ProductGroup("Товарная группа №12", "12", productGroup4);
        ProductGroup productGroup13 = new ProductGroup("Товарная группа №13", "13", productGroup9);
        ProductGroup productGroup14 = new ProductGroup("Товарная группа №14", "14", productGroup5);
        ProductGroup productGroup15 = new ProductGroup("Товарная группа №15", "15", productGroup10);

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
                "г. Воронеж,ул Карла Маркса,46",
                "comment to address",
                "3664069397",
                "79271669",
                "1053600591197",
                "236467",
                LocalDate.of(2020, 6, 12).toString(),
                typeOfContractorService.getByName("Юридическое лицо")
        ));
        legalDetailService.create(new LegalDetailDto(
                null,
                "Гордон",
                "Андрей",
                "Анатольевич",
                "г. Москва, ул. Революции, д. 66",
                "comment to address",
                "3664069439",
                "79271647",
                "1053600591285",
                "432145",
                LocalDate.of(2018, 2, 23).toString(),
                typeOfContractorService.getByName("Индивидуальный предприниматель")
        ));
        legalDetailService.create(new LegalDetailDto(
                null,
                "Сергеева",
                "Мария",
                "Дмитриевна",
                "г. Краснодар, ул. 40 Лет Октября, д. 16",
                "comment to address",
                "3664055588",
                "70713032",
                "1033600141277",
                "342145",
                LocalDate.of(2022, 4, 5).toString(),
                typeOfContractorService.getByName("Физическое лицо")
        ));
    }

    private void initCompanies() {
        for (int i = 0; i < 110; i++) {
            companyService.create(new CompanyDto(
                    null,
                    "OOO \"Организация №1\"",
                    "7712345" + String.format("%03d" , i),
                    String.format("%05d" , 1 + 3 * i),
                    "749512345678",
                    "810-41-1234567890",
                    "organization1@mail.com",
                    true,
                    "123456, г. Москва, ул. Подвойского, д. 14, стр. 7",
                    "something comment",
                    "Петров Сергей Петрович",
                    "Manager",
                    "leader signature",
                    "Сергеев Петр Сергеевич",
                    "chief signature",
                    "stamp",
                    new LegalDetailDto(
                            null,
                            "Иванов",
                            "Михаил",
                            "Сергеевич",
                            "г. Воронеж,ул Карла Маркса,46",
                            "comment to address",
                            "3664069" + String.format("%03d" , i),
                            "79271669",
                            "1053600591197",
                            "236467",
                            LocalDate.of(2020, 6, 12).toString(),
                            typeOfContractorService.getByName("Юридическое лицо"))));

            companyService.create(new CompanyDto(
                    null,
                    "OOO \"Организация №2\"",
                    "9543564" + String.format("%03d" , i),
                    String.format("%05d" , 2 + 3 * i),
                    "733126789654",
                    "920-12-2365723233",
                    "organization2@mail.com",
                    true,
                    "123498, г. Москва, ул. Тверская, д. 20",
                    "something comment",
                    "Иванова Мария Сергеевна",
                    "Executive director",
                    "leader signature",
                    "Соболев Николай Андреевич",
                    "chief signature",
                    "stamp",
                    new LegalDetailDto(
                            null,
                            "Гордон",
                            "Андрей",
                            "Анатольевич",
                            "г. Москва, ул. Революции, д. 66",
                            "comment to address",
                            "3664068" + String.format("%03d" , i),
                            "79271647",
                            "1053600591285",
                            "432145",
                            LocalDate.of(2018, 2, 23).toString(),
                            typeOfContractorService.getByName("Индивидуальный предприниматель"))));

            companyService.create(new CompanyDto(
                    null,
                    "OOO \"Организация №3\"",
                    "3453123" + String.format("%03d" , i),
                    String.format("%05d" , 3 + 3 * i),
                    "799123786542",
                    "543-23-1234543221",
                    "organization3@mail.com",
                    true,
                    "432156, г. Самара, ул. Гагарина, д. 18",
                    "something comment",
                    "Сергеева Ксения Андреевна",
                    "Project manager",
                    "leader signature",
                    "Стрелецкая Анастасия Михайловна",
                    "chief signature",
                    "stamp",
                    new LegalDetailDto(
                            null,
                            "Сергеева",
                            "Мария",
                            "Дмитриевна",
                            "г. Краснодар, ул. 40 Лет Октября, д. 16",
                            "comment to address",
                            "3664055" + String.format("%03d" , i),
                            "70713032",
                            "1033600141277",
                            "342145",
                            LocalDate.of(2022, 4, 5).toString(),
                            typeOfContractorService.getByName("Физическое лицо"))));
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
                imageService.getById(1L)));
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
                imageService.getById(2L)));
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
                imageService.getById(5L)));
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
                imageService.getById(4L)));
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
                departmentService.getByName("Складской комплекс"),
                positionService.getByName("Технический директор"),
                Collections.singleton(roleService.getByName("user")),
                imageService.getById(5L)));
    }

    private void initContractors() {
        contractorService.create(new ContractorDto(
                null,
                "Торговый Дом \"Перекресток\", ЗАО", "7728029110",
                "1",
                "8 (495) 232-59-24",
                "8 (495) 232-59-24",
                "alena.pechnikova@x5.ru",
                "109029, г. Москва, ул. Средняя Калитниковская, д. 28, стр. 4",
                "comment ot address",
                "comment",
                contractorGroupService.getById(1L),
                typeOfContractorService.getById(1L),
                typeOfPriceService.getById(1L),
                null,
                legalDetailService.getById(1L)));
        contractorService.create(new ContractorDto(
                null,
                "Агроаспект, ООО",
                "7715277300", "2",
                "8 (800) 555-55-05",
                "8 (800) 555-55-05",
                "inbox@5ka.ru",
                "127549, г. Москва, Алтуфьевское ш., д. 60",
                "",
                "",
                contractorGroupService.getById(1L),
                typeOfContractorService.getById(1L),
                typeOfPriceService.getById(1L),
                null,
                legalDetailService.getById(1L)));
        contractorService.create(new ContractorDto(
                null,
                "Вкусвилл, ООО",
                "7734675810",
                "3",
                "8 (495) 981-13-45",
                "8 (495) 981-13-45",
                "info@izbenka.msk.ru",
                "123592, г. Москва, ул. Кулакова, д. 20, к. 1, пом. V, ком. 1, эт. 10",
                "",
                "",
                contractorGroupService.getById(1L),
                typeOfContractorService.getById(1L),
                typeOfPriceService.getById(1L),
                null,
                legalDetailService.getById(1L)));
        contractorService.create(new ContractorDto(
                null,
                "Альфа-М, ООО", "7743931676",
                "4",
                "8 (495) 981-31-85",
                "8 (495) 981-31-85",
                "zholudeva.ksyusha@mail.ru",
                "125475, г. Москва, ул. Клинская, д. 12, ПОМЕЩЕНИЕ II (КОМНАТЫ 9-13)",
                "",
                "",
                contractorGroupService.getById(1L),
                typeOfContractorService.getById(1L),
                typeOfPriceService.getById(1L),
                null,
                legalDetailService.getById(1L)));
        contractorService.create(new ContractorDto(
                null,
                "Отдохни - 77, ООО", "7737531091",
                "5",
                "8 (495) 326-30-00",
                "8 (495) 326-30-00",
                "nina.chehovich@msk.nfretail.ru",
                "115372, г. Москва, ул. Бирюлёвская, д. 38",
                "",
                "",
                contractorGroupService.getById(1L),
                typeOfContractorService.getById(1L),
                typeOfPriceService.getById(1L),
                null,
                legalDetailService.getById(1L)));
        contractorService.create(new ContractorDto(
                null,
                "Продмир, ООО",
                "5009074197",
                "6",
                "8 (495) 651-92-52",
                "8 (495) 651-92-52",
                "d.gorobtsova@etpgpb.ru",
                "115516, г. Москва, Кавказский б-р, д. 57",
                "",
                "",
                contractorGroupService.getById(1L),
                typeOfContractorService.getById(1L),
                typeOfPriceService.getById(1L),
                null,
                legalDetailService.getById(1L)));
        contractorService.create(new ContractorDto(
                null,
                "Зельгрос, ООО", "5050058510",
                "7",
                "8 (495) 741-45-56",
                "8 (495) 741-45-56",
                "tatiana.onishchenko@selgros.ru",
                "117546, г. Москва, ул. Подольских Курсантов, 26, 1",
                "",
                "",
                contractorGroupService.getById(1L),
                typeOfContractorService.getById(1L),
                typeOfPriceService.getById(1L),
                null,
                legalDetailService.getById(1L)));
        contractorService.create(new ContractorDto(
                null,
                "Лабиринт-М, ООО", "7727777402",
                "8",
                "8 (495) 155-51-56",
                "8 (495) 155-51-56",
                "info@krasnoeibeloe.ru",
                "117042, г. Москва, ул. Южнобутовская, д. 69",
                "",
                "",
                contractorGroupService.getById(1L),
                typeOfContractorService.getById(1L),
                typeOfPriceService.getById(1L),
                null,
                legalDetailService.getById(1L)));
        contractorService.create(new ContractorDto(
                null,
                "Эскорт Сервис, ООО", "7705603716",
                "9",
                "8 (495) 755-11-16",
                "8 (495) 755-11-16",
                "harlanov.aleksandr@escort-servis.ru",
                "115404, г. Москва, ул. Бирюлёвская, д. 1, к. 3",
                "",
                "",
                contractorGroupService.getById(1L),
                typeOfContractorService.getById(1L),
                typeOfPriceService.getById(1L),
                null,
                legalDetailService.getById(1L)));
        contractorService.create(new ContractorDto(
                null,
                "Арома Маркет, ООО",
                "7710161911",
                "10",
                "8 (495) 777-51-95",
                "8 (495) 777-51-95",
                "sales11_am@aroma.ru",
                "121087, г. Москва, Береговой пр-д, д. 5, к. 1",
                "",
                "",
                contractorGroupService.getById(1L),
                typeOfContractorService.getById(1L),
                typeOfPriceService.getById(1L),
                null,
                legalDetailService.getById(1L)));
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
                    "Яблоки",
                    new BigDecimal("1.0"),
                    new BigDecimal("1.0"),
                    new BigDecimal("11.111"),
                    "Красные яблоки голден",
                    unitDtoList.get(0),
                    false,
                    contractorDtoList.get(0),
                    null,
                    taxSystemDtoList.get(0),
                    null,
                    productGroupDtoList.get(0),
                    attributeOfCalculationObjectDtoList.get(0)
            ));
            productService.create(new ProductDto(
                    null,
                    "Бананы",
                    new BigDecimal("1.0"),
                    new BigDecimal("1.0"),
                    new BigDecimal("22.222"),
                    "Красные Бананы голден",
                    unitDtoList.get(1),
                    false,
                    contractorDtoList.get(1),
                    null,
                    taxSystemDtoList.get(1),
                    null,
                    productGroupDtoList.get(1),
                    attributeOfCalculationObjectDtoList.get(1)
            ));
            productService.create(new ProductDto(
                    null,
                    "Мандарины",
                    new BigDecimal("1.0"),
                    new BigDecimal("1.0"),
                    new BigDecimal("33.333"),
                    "Красные Мандарины голден",
                    unitDtoList.get(2),
                    false,
                    contractorDtoList.get(1),
                    null,
                    taxSystemDtoList.get(2),
                    null,
                    productGroupDtoList.get(2),
                    attributeOfCalculationObjectDtoList.get(2)
            ));
        }
    }

    private void initContracts() {
        contractService.create(new ContractDto(
                null,
                "1",
                LocalDate.now(),
                1L,
                1L,
                1L,
                BigDecimal.valueOf(200),
                false,
                "no comments",
                1L));
    }
}
