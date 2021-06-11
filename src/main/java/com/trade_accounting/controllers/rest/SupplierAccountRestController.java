package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.SupplierAccountService;
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
@Tag(name="SupplierAccount Rest Controller", description = "CRUD операции со счетами поставщиков")
@Api(tags = "SupplierAccount Rest Controller")
@RequestMapping("api/supplierAccount")
public class SupplierAccountRestController {

    private final SupplierAccountService invoices;
    private final CheckEntityService checkEntityService;

    public SupplierAccountRestController(SupplierAccountService invoices,
                                         CheckEntityService checkEntityService) {
        this.invoices = invoices;
        this.checkEntityService = checkEntityService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех счетов поставщиков")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение счетов поставщиков"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<SupplierAccountDto>> getAll() {
        List<SupplierAccountDto> getAll = invoices.getAll();
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение счета поставщика по  id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Счет поставщика найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SupplierAccountDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти счет поставщика")
                                                          @PathVariable(name = "id") Long id) {
            checkEntityService.checkExistsSupplierAccountById(id);
            return ResponseEntity.ok(invoices.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового счета поставщика")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Счет поставщика создан"),
            @ApiResponse(code = 201, message = "Запрос принят и счет поставщика добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SupplierAccountDto> create(@ApiParam(name = "invoice", value = "DTO счета, который необходимо создать")
                                                         @RequestBody SupplierAccountDto invoice) {
        return ResponseEntity.ok().body(invoices.create(invoice));

    }

    @PutMapping
    @ApiOperation(value = "create", notes = "Изменение счета поставщика")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о счете обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и счет поставщика обновлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<SupplierAccountDto> update(@ApiParam(name = "invoice", value = "DTO счета, который необходимо создать")
                                                         @RequestBody SupplierAccountDto invoice) {
        return ResponseEntity.ok().body(invoices.update(invoice));

    }


    @ApiOperation(value = "deleteById", notes = "Удаляет счет поставщика на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Счет поставщика успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<SupplierAccountDto> deleteById(@ApiParam(name = "id", type = "Long",
                     value = "Переданный в URL id по которому необходимо удалить счет поставщика")
                                                             @PathVariable(name = "id") Long id) {
        invoices.deleteById(id);
        return ResponseEntity.ok().build();
    }




}
