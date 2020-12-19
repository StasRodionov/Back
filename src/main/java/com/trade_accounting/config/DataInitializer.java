package com.trade_accounting.config;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.ProductGroup;
import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.services.interfaces.AttributeOfCalculationObjectService;
import com.trade_accounting.services.interfaces.CompanyService;
import com.trade_accounting.services.interfaces.ContractorGroupService;
import com.trade_accounting.services.interfaces.DepartmentService;
import com.trade_accounting.services.interfaces.EmployeeService;
import com.trade_accounting.services.interfaces.ImageService;
import com.trade_accounting.services.interfaces.PositionService;
import com.trade_accounting.services.interfaces.ProductGroupService;
import com.trade_accounting.services.interfaces.RoleService;
import com.trade_accounting.services.interfaces.TypeOfContractorService;
import com.trade_accounting.services.interfaces.TaxSystemService;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import com.trade_accounting.services.interfaces.UnitService;
import com.trade_accounting.services.interfaces.WarehouseService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

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
    private final EmployeeService employeeService;
    private final ImageService imageService;

    public DataInitializer(TypeOfPriceService typeOfPriceService,
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
                           EmployeeService employeeService,
                           ImageService imageService) {
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
        this.employeeService = employeeService;
        this.imageService = imageService;
    }

    @PostConstruct
    public void init() {
        initTypeOfPrices();
        initRoles();
        initWarehouses();
        initUnits();
        initPositions();
        initAttributeOfCalculationObjects();
        initDepartments();
        initContractorGroups();
        initTypeOfContractors();
        initTaxSystems();
        initProductGroups();
        initEmployees();
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
                departmentService.getById(1L),
                positionService.getById(1L),
                (Set<RoleDto>) roleService.getById(1L),
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
                departmentService.getById(2L),
                positionService.getById(2L),
                (Set<RoleDto>) roleService.getById(2L),
                imageService.getById(2L)));
                ;
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
                departmentService.getById(3L),
                positionService.getById(3L),
                (Set<RoleDto>) roleService.getById(3L),
                imageService.getById(3L)));
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
                departmentService.getById(4L),
                positionService.getById(4L),
                (Set<RoleDto>) roleService.getById(4L),
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
                departmentService.getById(5L),
                positionService.getById(5L),
                (Set<RoleDto>) roleService.getById(5L),
                imageService.getById(5L)));
    }

    private void initTypeOfPrices() {
        typeOfPriceService.create(new TypeOfPriceDto("Оптовая цена", "1"));
        typeOfPriceService.create(new TypeOfPriceDto("Розничная цена", "2"));
    }

    private void initRoles() {
        roleService.create(new RoleDto("admin", "1"));
        roleService.create(new RoleDto("user", "2"));
    }

    private void initWarehouses(){
        warehouseService.create(new WarehouseDto("Основной склад", "1"));
    }

    private void initUnits(){
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

    private void initPositions(){
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

    private void initDepartments(){
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

    private void initContractorGroups() {
        contractorGroupService.create(new ContractorGroupDto("Покупатель", "1"));
        contractorGroupService.create(new ContractorGroupDto("Поставщик", "2"));
    }

    private void initTypeOfContractors(){
        typeOfContractorService.create(new TypeOfContractorDto("Юридическое лицо","1"));
        typeOfContractorService.create(new TypeOfContractorDto("Индивидуальный предприниматель","2"));
        typeOfContractorService.create(new TypeOfContractorDto("Физическое лицо","3"));
    }

    private void initTaxSystems(){
        taxSystemService.create(new TaxSystemDto("ОСН", "1"));
        taxSystemService.create(new TaxSystemDto("УСН.Доход", "2"));
        taxSystemService.create(new TaxSystemDto("УСН.Доход-Расход", "3"));
        taxSystemService.create(new TaxSystemDto("ЕСХН", "4"));
        taxSystemService.create(new TaxSystemDto("ЕНВД", "5"));
        taxSystemService.create(new TaxSystemDto("Патент", "6"));
    }

    private void initProductGroups(){

        ProductGroup productGroup1 = new ProductGroup("Товарная группа №1", "1" );
        ProductGroup productGroup2 = new ProductGroup("Товарная группа №2", "2" );
        ProductGroup productGroup3 = new ProductGroup("Товарная группа №3", "3" );
        ProductGroup productGroup4 = new ProductGroup("Товарная группа №4", "4" );
        ProductGroup productGroup5 = new ProductGroup("Товарная группа №5", "5" );

        productGroupService.create(productGroup1);
        productGroupService.create(productGroup2);
        productGroupService.create(productGroup3);
        productGroupService.create(productGroup4);
        productGroupService.create(productGroup5);

        ProductGroup productGroup6 = new ProductGroup("Товарная группа №6", "6", productGroup1 );
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

    private void initCompanies() {

        LegalDetail legalDetail1 = new LegalDetail("Иванов", "Михаил", "Сергеевич", "г. Воронеж,ул Карла Маркса,46",
                "comment to address", "3664069397", "79271669", "1053600591197",
                "236467", (LocalDate.of(2020, 6, 12)), typeOfContractorService.getByName("Индивидуальный предприниматель"));

        LegalDetail legalDetail2 = new LegalDetail("Гордон", "Андрей", "Анатольевич", "г. Москва, ул. Революции, д. 66",
                "comment to address", "3664069439", "79271647", "1053600591285",
                "432145", (LocalDate.of(2018, 2, 23)), typeOfContractorService.getByName("Юридическое лицо"));

        LegalDetail legalDetail3 = new LegalDetail("Сергеева", "Мария", "Дмитриевна", "г. Краснодар, ул. 40 Лет Октября, д. 16",
                "comment to address", "3664055588", "70713032", "1033600141277",
                "342145", (LocalDate.of(2022, 4, 5)), typeOfContractorService.getByName("Физическое лицо"));

        legalDetailService.create(legalDetail1);
        legalDetailService.create(legalDetail2);
        legalDetailService.create(legalDetail3);

        companyService.create(new Company("OOO \"Организация №1\"", "7712345678", "1", "749512345678", "810-41-1234567890", "organization1@mail.com",
                true, "123456, г. Москва, ул. Подвойского, д. 14, стр. 7", "something comment", "Петров Сергей Петрович", "Manager",
                "leader signature", "Сергеев Петр Сергеевич", "chief signature", "stamp", legalDetail1));

        companyService.create(new Company( "OOO \"Организация №2\"", "9543564455", "3", "733126789654", "920-12-2365723233", "organization2@mail.com",
                true, "123498, г. Москва, ул. Тверская, д. 20", "something comment", "Иванова Мария Сергеевна", "Executive director",
                "leader signature", "Соболев Николай Андреевич", "chief signature", "stamp", legalDetail2));

        companyService.create(new Company( "OOO \"Организация №3\"", "3453123465", "3", "799123786542", "543-23-1234543221", "organization3@mail.com",
                true, "432156, г. Самара, ул. Гагарина, д. 18", "something comment", "Сергеева Ксения Андреевна", "Project manager",
                "leader signature", "Стрелецкая Анастасия Михайловна", "chief signature", "stamp", legalDetail3));

    }

}
