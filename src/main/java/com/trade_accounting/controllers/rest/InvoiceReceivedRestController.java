package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.InvoiceReceivedDto;
import com.trade_accounting.repositories.InvoiceReceivedRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.InvoiceReceivedService;
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
@Tag(name = "InvoiceReceived Rest Controller", description = "CRUD  операции с полученными счетами-фактурами")
@Api(tags = "InvoiceReceived Rest Controller")
@RequestMapping("/api/invoice_received")
@RequiredArgsConstructor
public class InvoiceReceivedRestController {

    private final InvoiceReceivedService invoiceReceivedService;
    private final CheckEntityService checkEntityService;
    private final InvoiceReceivedRepository invoiceReceivedRepository;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех полученных счетов-фактур")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка полученных счетов-фактур"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InvoiceReceivedDto>> getAll() {
        List<InvoiceReceivedDto> invoiceReceivedDtos;
        invoiceReceivedDtos = invoiceReceivedService.getAll();
        return ResponseEntity.ok(invoiceReceivedDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение полученного счета-фактуры по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Полученный счет-фактура найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InvoiceReceivedDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти полученный счет-фактуру")
                                                    @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) invoiceReceivedRepository, id);
        return ResponseEntity.ok(invoiceReceivedService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового полученного счета-фактуры")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Полученный счет-фактура создан"),
            @ApiResponse(code = 201, message = "Запрос принят и полученный счет-фактура добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InvoiceReceivedDto> create(@ApiParam(name = "invoiceDto", value = "DTO полученного счета-фактуры, которую необходимо создать")
                                                   @RequestBody InvoiceReceivedDto invoiceReceivedDto) {
        return ResponseEntity.ok().body(invoiceReceivedService.create(invoiceReceivedDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о полученном счете-фактуре")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о полученном счета-фактуре обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о полученном счете-фактуре обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "issuedInvoiceDto", value = "DTO полученного счета-фактуры, которого необходимо обновить")
                                    @RequestBody InvoiceReceivedDto invoiceReceivedDto) {
        return ResponseEntity.ok().body(invoiceReceivedService.update(invoiceReceivedDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление полученного счета-фактуры по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Полученный счет-фактура удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить полученный счет-фактуру")
                                        @PathVariable(name = "id") Long id) {
        invoiceReceivedService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
