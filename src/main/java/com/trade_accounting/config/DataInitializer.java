package com.trade_accounting.config;

import com.trade_accounting.models.ProductGroup;
import com.trade_accounting.models.dto.*;
import com.trade_accounting.services.interfaces.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final TypeOfPriceService typeOfPriceService;
    private final RoleService roleService;
    private final UnitService unitService;
    private final PositionService positionService;
    private final WarehouseService warehouseService;
    private final AttributeOfCalculationObjectService attributeOfCalculationObjectService;
    private final DepartmentService departmentService;
    private final ProductGroupService productGroupService;

    public DataInitializer(TypeOfPriceService typeOfPriceService,
                           RoleService roleService,
                           UnitService unitService,
                           PositionService positionService,
                           WarehouseService warehouseService,
                           AttributeOfCalculationObjectService attributeOfCalculationObjectService,
                           DepartmentService departmentService, ProductGroupService productGroupService) {
        this.typeOfPriceService = typeOfPriceService;
        this.roleService = roleService;
        this.warehouseService = warehouseService;
        this.unitService = unitService;
        this.positionService = positionService;
        this.attributeOfCalculationObjectService = attributeOfCalculationObjectService;
        this.departmentService = departmentService;
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
        initDeparments();
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

    private void initDeparments(){
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

    private void initProductGroups(){
        productGroupService.create(new ProductGroupDto("Товарная группа №1", "1"));
        productGroupService.create(new ProductGroupDto("Товарная группа №2", "2"));
        productGroupService.create(new ProductGroupDto("Товарная группа №3", "3"));
        productGroupService.create(new ProductGroupDto("Товарная группа №4", "4"));
        productGroupService.create(new ProductGroupDto("Товарная группа №5", "5"));
        productGroupService.create(new ProductGroupDto("Товарная группа №6", "6", (long) 1));
        productGroupService.create(new ProductGroupDto("Товарная группа №7", "7", (long) 6));
        productGroupService.create(new ProductGroupDto("Товарная группа №8", "8", (long) 2));
        productGroupService.create(new ProductGroupDto("Товарная группа №9", "9", (long) 7));
        productGroupService.create(new ProductGroupDto("Товарная группа №10", "10", (long) 3));
        productGroupService.create(new ProductGroupDto("Товарная группа №11", "11", (long) 8));
        productGroupService.create(new ProductGroupDto("Товарная группа №12", "12", (long) 4));
        productGroupService.create(new ProductGroupDto("Товарная группа №13", "13", (long) 8));
        productGroupService.create(new ProductGroupDto("Товарная группа №14", "14", (long) 5));
        productGroupService.create(new ProductGroupDto("Товарная группа №15", "15", (long) 10));
    }

}
