package com.trade_accounting.config;

import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import com.trade_accounting.services.interfaces.UnitService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final TypeOfPriceService typeOfPriceService;
    private final UnitService unitService;

    public DataInitializer(TypeOfPriceService typeOfPriceService, UnitService unitService) {
        this.typeOfPriceService = typeOfPriceService;
        this.unitService = unitService;
    }

    @PostConstruct
    public void init() {
        initTypeOfPrices();
        initUnits();
    }

    private void initTypeOfPrices() {
        typeOfPriceService.create(new TypeOfPriceDto("Оптовая цена", "1"));
        typeOfPriceService.create(new TypeOfPriceDto("Розничная цена", "2"));
    }

    private void initUnits(){
        unitService.create(new UnitDto(null,  "мм", "Миллиметр", "1"));
        unitService.create(new UnitDto(null,  "см", "Сантиметр", "2"));
        unitService.create(new UnitDto(null,  "дм", "Дециметр", "3"));
        unitService.create(new UnitDto(null,  "м", "Метр", "4"));
        unitService.create(new UnitDto(null,  "км; 10^3 м", "Километр; тысяча метров", "5"));
        unitService.create(new UnitDto(null,  "Мм; 10^6 м", "Мегаметр; миллион метров", "6"));
        unitService.create(new UnitDto(null,  "пог. м", "Погонный метр", "7"));
        unitService.create(new UnitDto(null,  "дюйм", "Дюйм (25,4 мм)", "8"));
        unitService.create(new UnitDto(null,  "фут", "Фут (0,3048 м)", "9"));
        unitService.create(new UnitDto(null,  "ярд", "Ярд (0,9144 м)", "10"));
        unitService.create(new UnitDto(null,  "миля", "Морская миля (1852 м)", "11"));
        unitService.create(new UnitDto(null,  "мм2", "Квадратный миллиметр", "12"));
        unitService.create(new UnitDto(null,  "см2", "Квадратный сантиметр", "13"));
        unitService.create(new UnitDto(null,  "дм2", "Квадратный дециметр", "14"));
        unitService.create(new UnitDto(null,  "м2", "Квадратный метр", "15"));
        unitService.create(new UnitDto(null,  "10^3 м^2", "Тысяча квадратных метров", "16"));
        unitService.create(new UnitDto(null,  "га", "Гектар", "17"));
        unitService.create(new UnitDto(null,  "км2", "Квадратный километр", "18"));
        unitService.create(new UnitDto(null,  "дюйм2", "Квадратный дюйм (645,16 мм2)", "19"));
        unitService.create(new UnitDto(null,  "фут2", "Квадратный фут (0,092903 м2)", "20"));
        unitService.create(new UnitDto(null,  "ярд2", "Квадратный ярд (0,8361274 м2)", "21"));
        unitService.create(new UnitDto(null,  "а", "Ар (100 м2)", "22"));
        unitService.create(new UnitDto(null,  "мм3", "Кубический миллиметр", "23"));
        unitService.create(new UnitDto(null,  "см3; мл", "Кубический сантиметр; миллилитр", "24"));
        unitService.create(new UnitDto(null,  "л; дм3", "Литр; кубический дециметр", "25"));
        unitService.create(new UnitDto(null,  "м3", "Кубический метр", "26"));
        unitService.create(new UnitDto(null,  "дл", "Децилитр", "27"));
        unitService.create(new UnitDto(null,  "гл", "Гектолитр", "28"));
        unitService.create(new UnitDto(null,  "Мл", "Мегалитр", "29"));
        unitService.create(new UnitDto(null,  "дюйм3", "Кубический дюйм (16387,1 мм3)", "30"));
        unitService.create(new UnitDto(null,  "фут3", "Кубический фут (0,02831685 м3)", "31"));
        unitService.create(new UnitDto(null,  "ярд3", "Кубический ярд (0,764555 м3)", "32"));
        unitService.create(new UnitDto(null,  "10^6 м3", "Миллион кубических метров", "33"));
        unitService.create(new UnitDto(null,  "гг", "Гектограмм", "34"));
        unitService.create(new UnitDto(null,  "мг", "Миллиграмм", "35"));
        unitService.create(new UnitDto(null,  "кар", "Метрический карат", "36"));
        unitService.create(new UnitDto(null,  "г", "Грамм", "37"));
        unitService.create(new UnitDto(null,  "кг", "Килограмм", "38"));
        unitService.create(new UnitDto(null,  "т", "Тонна; метрическая тонна (1000 кг)", "39"));
        unitService.create(new UnitDto(null,  "10^3 т", "Килотонна", "40"));
        unitService.create(new UnitDto(null,  "сг", "Сантиграмм", "41"));
        unitService.create(new UnitDto(null,  "БРТ", "Брутто-регистровая тонна (2,8316 м3)", "42"));
        unitService.create(new UnitDto(null,  "т грп", "Грузоподъемность в метрических тоннах", "43"));
        unitService.create(new UnitDto(null,  "ц", "Центнер (метрический) (100 кг); гектокилограмм; квинтал1 (метрический); децитонна", "44"));
        unitService.create(new UnitDto(null,  "с", "Секунда", "45"));
        unitService.create(new UnitDto(null,  "мин", "Минута", "46"));
        unitService.create(new UnitDto(null,  "ч", "Час", "47"));
        unitService.create(new UnitDto(null,  "сут; дн", "Сутки", "48"));
        unitService.create(new UnitDto(null,  "нед", "Неделя", "49"));
        unitService.create(new UnitDto(null,  "дек", "Декада", "50"));
        unitService.create(new UnitDto(null,  "мес", "Месяц", "51"));
        unitService.create(new UnitDto(null,  "кварт", "Квартал", "52"));
        unitService.create(new UnitDto(null,  "полгода", "Полугодие", "53"));
        unitService.create(new UnitDto(null,  "г; лет", "Год", "54"));
        unitService.create(new UnitDto(null,  "деслет", "Десятилетие", "55"));
        unitService.create(new UnitDto(null,  "рулон", "Рулон", "56"));
        unitService.create(new UnitDto(null,  "шт", "Штука", "57"));
        unitService.create(new UnitDto(null,  "ящ", "Ящик", "58"));
        unitService.create(new UnitDto(null,  "блок", "Блок сигарет", "59"));
    }
}
