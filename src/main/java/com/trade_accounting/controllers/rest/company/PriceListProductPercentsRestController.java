package com.trade_accounting.controllers.rest.company;

import com.trade_accounting.models.dto.company.PriceListProductPercentsDto;
import com.trade_accounting.repositories.company.PriceListProductPercentsRepository;
import com.trade_accounting.services.interfaces.company.PriceListProductPercentsService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "PriceList Product Rest Controller", description = "CRUD операции с ценами прайс-листа")
@Api(tags = "PriceList Product Rest Controller")
@RequestMapping("/api/priceList/product-percent")
@RequiredArgsConstructor
public class PriceListProductPercentsRestController {
    private final PriceListProductPercentsRepository priceListProductPercentsRepository;
    private final PriceListProductPercentsService priceListProductPercentsService;
    private final CheckEntityService checkEntityService;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех цен прайс-листа")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех цен прайс-листа"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PriceListProductPercentsDto>> getAll() {
        return ResponseEntity.ok(priceListProductPercentsService.getAll());
    }

    @ApiOperation(value = "getById", notes = "Возвращает товар в прайс-листе по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Цена в прайс-листе найдена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PriceListProductPercentsDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти прайс-лист",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) priceListProductPercentsRepository,id);
        return ResponseEntity.ok(priceListProductPercentsService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Добавляет цену в прайс-лист на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Цена в прайс-лист успешно добавлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "priceListProductDto",
            value = "DTO PriceListProductDto, который необходимо создать") @RequestBody PriceListProductPercentsDto priceListProductPercentsDto) {
        return ResponseEntity.ok().body(priceListProductPercentsService.create(priceListProductPercentsDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет цену в прайс-листе на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Цена в прайс-листе успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "priceListProductDto",
            value = "DTO PriceListProductDto, который необходимо обновить") @RequestBody PriceListProductPercentsDto priceListProductPercentsDto) {
        return ResponseEntity.ok().body(priceListProductPercentsService.update(priceListProductPercentsDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет цену в прайс-листе на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Цена в прайс-листе успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить товар в прайс-листе",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        priceListProductPercentsService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "getByPriceListProductId", notes = "Возвращает список цен в прайс-листе по PriceListProduct.id")
    @GetMapping("/priceList/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Цены в прайс-листе найдены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PriceListProductPercentsDto>> getByPriceListId(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо список товаров в накладной",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        List<PriceListProductPercentsDto> priceListProductDtoList = priceListProductPercentsService.searchByPriceListId(id);
        return ResponseEntity.ok(priceListProductDtoList);
    }
}
