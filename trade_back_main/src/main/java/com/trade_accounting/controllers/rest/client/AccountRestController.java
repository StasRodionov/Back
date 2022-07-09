package com.trade_accounting.controllers.rest.client;


import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.entity.client.Account;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.services.interfaces.client.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/account")
@Tag(name = "Account Rest Controller", description = "CRUD операции с аккаунтами")
@Api(tags = "Account Rest Controller")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получить список всех работников")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка работников"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")})

    public ResponseEntity<List<EmployeeDto>> getAll() {
        List<EmployeeDto> employeeDtos = accountService.getAll();
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "получение работника по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Работник успешно найден"),
            @ApiResponse(code = 201, message = "Запрос принят и данные получены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<EmployeeDto> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(accountService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Account> creatAccount(@RequestBody EmployeeDto employeeDto) {

        return ResponseEntity.ok().body(accountService.createAccount(employeeDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Обновление данных о работнике")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о работнике успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")})
    public ResponseEntity<Employee> update(@ApiParam(name = "employeeDto", value = "DTO работника, которого необходимо обновить") @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok().body(accountService.updateEmployee(employeeDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление информации о аккаунте по Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о аккаунте успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден")})

    public ResponseEntity<?> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить подразделение",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.ok().build();
    }
}
