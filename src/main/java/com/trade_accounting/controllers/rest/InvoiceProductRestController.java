package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.services.interfaces.InvoiceProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Invoice Product Rest Controller", description = "CRUD операции с товарами в накладной")
@Api(tags = "Invoice Product Rest Controller")
@RequestMapping("/api/invoice/product")
public class InvoiceProductRestController {

    private final InvoiceProductService invoiceProductService;

    public InvoiceProductRestController(InvoiceProductService invoiceProductService) {
        this.invoiceProductService = invoiceProductService;
    }

    @ApiOperation(value = "getAll", notes = "Возвращает список всех товаров в накладной")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех товаров в накладной"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InvoiceProductDto>> getAll() {
        List<InvoiceProductDto> invoiceProductDtos = invoiceProductService.getAll();
        return ResponseEntity.ok(invoiceProductDtos);
    }

    @ApiOperation(value = "getById", notes = "Возвращает товар в накладной по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в накладной найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InvoiceProductDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти товар в накладной",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        InvoiceProductDto invoiceProductDto = invoiceProductService.getById(id);
        return ResponseEntity.ok(invoiceProductDto);
    }

    @ApiOperation(value = "getByInvoiceId", notes = "Возвращает список товаров в накладной по Invoice.id")
    @GetMapping("/invoice_product/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в накладной найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InvoiceProductDto>> getByInvoiceId(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо список товаров в накладной",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        List<InvoiceProductDto> invoiceProductDtoList = invoiceProductService.searchByInvoiceId(id);
        return ResponseEntity.ok(invoiceProductDtoList);
    }

    @ApiOperation(value = "create", notes = "Добавляет товар в накладной на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в накладной успешно добавлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "invoiceProductDto",
            value = "DTO товара в накладной, который необходимо создать") @RequestBody InvoiceProductDto invoiceProductDto) {
        InvoiceProductDto invoiceProductDtoCreate = invoiceProductService.create(invoiceProductDto);
        return ResponseEntity.ok(invoiceProductDtoCreate);
        //return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "update", notes = "Обновляет товар в накладной на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в накладной успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "invoiceProductDto",
            value = "DTO InvoiceProduct, который необходимо обновить") @RequestBody InvoiceProductDto invoiceProductDto) {
        InvoiceProductDto invoiceProductDtoUpdated = invoiceProductService.update(invoiceProductDto);
        return ResponseEntity.ok(invoiceProductDtoUpdated);
        //return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет товар в накладной на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в накладной успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить товар в накладной",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        invoiceProductService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
