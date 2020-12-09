package com.trade_accounting.config;

import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.repositories.ProductGroupRepository;
import com.trade_accounting.services.interfaces.AttributeOfCalculationObjectService;
import com.trade_accounting.services.interfaces.ContractorGroupService;
import com.trade_accounting.services.interfaces.DepartmentService;
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
                           TypeOfContractorService typeOfContractorService) {
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
        ProductGroupDto productGroupDto1 = new ProductGroupDto("Товарная группа №1", "1" );
        ProductGroupDto productGroupDto2 = new ProductGroupDto("Товарная группа №2", "2" );
        ProductGroupDto productGroupDto3 = new ProductGroupDto("Товарная группа №3", "3" );
        ProductGroupDto productGroupDto4 = new ProductGroupDto("Товарная группа №4", "4" );
        ProductGroupDto productGroupDto5 = new ProductGroupDto("Товарная группа №5", "5" );

        productGroupService.create(productGroupDto1);
        productGroupService.create(productGroupDto2);
        productGroupService.create(productGroupDto3);
        productGroupService.create(productGroupDto4);
        productGroupService.create(productGroupDto5);

        productGroupDto1 = productGroupService.getByNameAndSortNumber(productGroupDto1.getName(), productGroupDto1.getSortNumber());
        productGroupDto2 = productGroupService.getByNameAndSortNumber(productGroupDto2.getName(), productGroupDto2.getSortNumber());
        productGroupDto3 = productGroupService.getByNameAndSortNumber(productGroupDto3.getName(), productGroupDto3.getSortNumber());
        productGroupDto4 = productGroupService.getByNameAndSortNumber(productGroupDto4.getName(), productGroupDto4.getSortNumber());
        productGroupDto5 = productGroupService.getByNameAndSortNumber(productGroupDto5.getName(), productGroupDto5.getSortNumber());


        ProductGroupDto productGroupDto6 = new ProductGroupDto("Товарная группа №6", "6", productGroupDto1.getId() );
        productGroupService.create(productGroupDto6);
        productGroupDto6 = productGroupService.getByNameAndSortNumber(productGroupDto6.getName(), productGroupDto6.getSortNumber());
        ProductGroupDto productGroupDto7 = new ProductGroupDto("Товарная группа №7", "7", productGroupDto6.getId() );
        productGroupService.create(productGroupDto7);
        productGroupDto7 = productGroupService.getByNameAndSortNumber(productGroupDto7.getName(), productGroupDto7.getSortNumber());
        ProductGroupDto productGroupDto8 = new ProductGroupDto("Товарная группа №8", "8", productGroupDto2.getId() );
        productGroupService.create(productGroupDto8);
        productGroupDto8 = productGroupService.getByNameAndSortNumber(productGroupDto8.getName(), productGroupDto8.getSortNumber());
        ProductGroupDto productGroupDto9 = new ProductGroupDto("Товарная группа №9", "9", productGroupDto7.getId() );
        productGroupService.create(productGroupDto9);
        productGroupDto9 = productGroupService.getByNameAndSortNumber(productGroupDto9.getName(), productGroupDto9.getSortNumber());
        ProductGroupDto productGroupDto10 = new ProductGroupDto("Товарная группа №10", "10", productGroupDto3.getId() );
        productGroupService.create(productGroupDto10);
        productGroupDto10 = productGroupService.getByNameAndSortNumber(productGroupDto10.getName(), productGroupDto10.getSortNumber());

        ProductGroupDto productGroupDto11 = new ProductGroupDto("Товарная группа №11", "11", productGroupDto8.getId() );
        ProductGroupDto productGroupDto12 = new ProductGroupDto("Товарная группа №12", "12", productGroupDto4.getId() );
        ProductGroupDto productGroupDto13 = new ProductGroupDto("Товарная группа №13", "13", productGroupDto9.getId() );
        ProductGroupDto productGroupDto14 = new ProductGroupDto("Товарная группа №14", "14", productGroupDto5.getId() );
        ProductGroupDto productGroupDto15 = new ProductGroupDto("Товарная группа №15", "15", productGroupDto10.getId() );

        productGroupService.create(productGroupDto11);
        productGroupService.create(productGroupDto12);
        productGroupService.create(productGroupDto13);
        productGroupService.create(productGroupDto14);
        productGroupService.create(productGroupDto15);

    }
}
