package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.services.interfaces.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@Tag(name = "Contract Rest Controller", description = "CRUD  операции с договорами")
@Api(tags = "Contract Rest Controller")
@RequestMapping("/api/contract")
public class ContractRestController {

    private final ContractService contractService;

    public ContractRestController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех договоров")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка договоров"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ContractDto>> getAll() {
        List<ContractDto> contracts = contractService.getAll();
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение договора по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Договор найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо найти договор")
                                                   @PathVariable(name = "id") Long id) {
        ContractDto contractDto = contractService.getById(id);
        log.info("Запрошен ContractDto с id= {}", id);
        return ResponseEntity.ok(contractDto);
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение нового договора")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Договор создан"),
            @ApiResponse(code = 201, message = "Запрос принят и договор добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractDto> create(@ApiParam(name = "contractDto",
            value = "DTO договора, который необходимо создать")
                                                  @RequestBody ContractDto contractDto) {
        contractService.create(contractDto);
        log.info("Записан новый экземпляр - {}", contractDto.toString());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о договоре")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о договоре обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о договоре обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractDto> update(@ApiParam(name = "contractDto",
            value = "DTO договора, который необходимо обновить")
                                                  @RequestBody ContractDto contractDto) {
        contractService.update(contractDto);
        log.info("Обновлен экземпляр ContractDto с id= {}", contractDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление договора по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Договор удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить договор")
                                                      @PathVariable(name = "id") Long id) {
        contractService.deleteById(id);
        log.info("Удален экземпляр ContractDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
