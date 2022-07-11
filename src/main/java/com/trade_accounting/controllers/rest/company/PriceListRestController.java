package com.trade_accounting.controllers.rest.company;

import com.trade_accounting.models.dto.company.PriceListDto;
import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.repositories.company.PriceListRepository;
import com.trade_accounting.services.interfaces.company.PriceListService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "PriceList Rest Controller", description = "CRUD операции с прайс-листами")
@Api(tags = "PriceList Rest Controller")
@RequestMapping("/api/priceList")
@RequiredArgsConstructor
public class PriceListRestController {

    private final PriceListService priceListService;

    private final CheckEntityService checkEntityService;

    private final PriceListRepository priceListRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех прайс-листов")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех прайс-листов"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<PriceListDto>> getAll() {
        List<PriceListDto> priceListDtoList = priceListService.getAll();
        return ResponseEntity.ok(priceListDtoList);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный прайс-лист по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Прайс-лист найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<PriceListDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти прайс-лист") @PathVariable(name = "id") Long id) {
        PriceListDto priceListDto = priceListService.getById(id);
        return ResponseEntity.ok(priceListDto);
    }

    @ApiOperation(value = "create", notes = "Создает прайс-лист на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Прайс-лист успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<PriceListDto> create(@ApiParam(name = "priceListDto", value = "DTO прайс-листа, которое необходимо создать")
                                               @RequestBody PriceListDto priceListDto) {
        return ResponseEntity.ok().body(priceListService.create(priceListDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет прайс-лист на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Прайс-лист успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<PriceListDto> update(@ApiParam(name = "priceListDto", value = "DTO прайс-листа, с обновлёнными данными")
                                               @RequestBody PriceListDto priceListDto) {
        return ResponseEntity.ok().body(priceListService.update(priceListDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет прайс-лист на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Прайс-лист успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<PriceListDto> geleteById(@ApiParam(name = "id",
            value = "ID прайс-листа, который необходимо удалить")
                                                   @PathVariable(name = "id") Long id) {
        priceListService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchByFilter")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка прайс-листов по фильтру")
    public ResponseEntity<List<PriceListDto>> getAllByFilter(
            @Join(path = "products", alias = "p")
            @And(
                    {
                            @Spec(path = "p.products.name", params = "productName", spec = Like.class),
                            @Spec(path = "date",
                                    params = "date",
                                    spec = GreaterThanOrEqual.class),
                            @Spec(path = "date",
                                    params = "dateBefore",
                                    spec = LessThanOrEqual.class),
                            @Spec(path = "number", params = "number", spec = EqualIgnoreCase.class),
                            @Spec(path = "comment", params = "comment", spec = Like.class),
                            @Spec(path = "company.name", params = "company", spec = Like.class),
                            @Spec(path = "isSent", params = "sent", spec = Equal.class),
                            @Spec(path = "isPrint", params = "print", spec = Equal.class),
                            @Spec(path = "typeOfPrice.name", params = "typeOfPrice", spec = Equal.class)
                    }) Specification<PriceList> spec) {
        return ResponseEntity.ok(priceListService.search(spec));
    }

    @GetMapping("/searchByBetweenDataFilter")
    @ApiOperation(value = "searchByBetweenDataFilter", notes = "Получение списка прайс-листов по фильтру")
    public ResponseEntity<List<PriceListDto>> getAllBetweenDateFilter(
            @Join(path = "products", alias = "p")
            @And({
                    @Spec(path = "p.products.name", params = "productName", spec = Like.class),
                    @Spec(
                            path = "date",
                            params = {"date", "dateBefore"},
                            spec = Between.class
                    ),
                    @Spec(path = "number", params = "number", spec = EqualIgnoreCase.class),
                    @Spec(path = "comment", params = "comment", spec = Like.class),
                    @Spec(path = "company.name", params = "company", spec = Like.class),
                    @Spec(path = "isSent", params = "sent", spec = Equal.class),
                    @Spec(path = "isPrint", params = "print", spec = Equal.class),
                    @Spec(path = "typeOfPrice.name", params = "typeOfPrice", spec = Equal.class)
            }) Specification<PriceList> spec) {
        return ResponseEntity.ok(priceListService.search(spec));
    }

    @ApiOperation(value = "quickSearch", notes = "Получение списка прайс-листов по заданным параметрам - название и комментарий")
    @GetMapping("/quickSearch")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка прайс-листов по заданным параметрам"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PriceListDto>> quickSearch(@RequestParam("search") String search) {
        return ResponseEntity.ok(priceListService.quickSearch(search));
    }

    @GetMapping("/filter/{search}")
    @ApiOperation(value = "search", notes = "Получение списка некоторых прайс-листов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение отф. прайс-листа"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PriceListDto>> getAllForFilter(@ApiParam(name = "search", value = "")
                                                              @PathVariable(name = "search") String search) {
        List<PriceListDto> priceListDtoList = priceListService.getAllForFilter(search);
        return ResponseEntity.ok(priceListDtoList);
    }

    @PutMapping("/moveToIsRecyclebin/{id}")
    @ApiOperation(value = "moveToIsRecyclebin", notes = "Перенос в корзину прайс-листа по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Прайс-лист перенесен в корзину"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PriceListDto> moveToIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо переместить счет")
                                                           @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) priceListRepository, id);
        priceListService.moveToRecyclebin(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/restoreFromIsRecyclebin/{id}")
    @ApiOperation(value = "restoreFromIsRecyclebin", notes = "Восстановление прайс-листа по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Прайс-лист восстановлен"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PriceListDto> restoreFromIsRecyclebin(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо восстановить счет")
                                                                @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) priceListRepository, id);
        priceListService.restoreFromRecyclebin(id);
        return ResponseEntity.ok().build();
    }

}
