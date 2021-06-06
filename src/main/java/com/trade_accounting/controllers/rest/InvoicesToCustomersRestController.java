package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.InvoicesToCustomersDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.InvoicesToCustomersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name="InvoicesToCustomers Rest Controller", description = "CRUD операции со счетами покупателя")
@Api(tags = "InvoicesToCustomers Rest Controller")
@RequestMapping("api/invoicesToCustomers")
public class InvoicesToCustomersRestController {

    private final InvoicesToCustomersService invoices;
    private final CheckEntityService checkEntityService;

    public InvoicesToCustomersRestController(InvoicesToCustomersService invoices,
                                             CheckEntityService checkEntityService) {
        this.invoices = invoices;
        this.checkEntityService = checkEntityService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех счетов покупателей")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение счетов покупателей"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InvoicesToCustomersDto>> getAll() {
        List<InvoicesToCustomersDto> getAll = invoices.getAll();
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение счета покупателя по  id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Счет найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InvoicesToCustomersDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти накладную")
                                                          @PathVariable(name = "id") Long id) {
            checkEntityService.checkExistsInvoicesToCustomersById(id);
            return ResponseEntity.ok(invoices.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового счета покупателя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Накладная создана"),
            @ApiResponse(code = 201, message = "Запрос принят и накладная добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InvoicesToCustomersDto> create(@ApiParam(name = "invoice", value = "DTO накладной, которую необходимо создать")
                                                         @RequestBody InvoicesToCustomersDto invoice) {
        return ResponseEntity.ok().body(invoices.create(invoice));

    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Изменение счета покупателя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о счете обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и счет покупателя обновлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InvoicesToCustomersDto> update(@ApiParam(name = "invoice", value = "DTO накладной, которую необходимо создать")
                                                         @RequestBody InvoicesToCustomersDto invoice) {
        return ResponseEntity.ok().body(invoices.update(invoice));

    }

    public ResponseEntity<InvoicesToCustomersDto> deleteById(@ApiParam(name = "id", type = "Long",
                     value = "Переданный в URL id по которому необходимо удалить счет покупателя")
                                                             @PathVariable(name = "id") Long id) {
        invoices.deleteById(id);
        return ResponseEntity.ok().build();
    }




}
