package com.trade_accounting.controllers.rest.client;

import com.trade_accounting.models.dto.client.AccountDto;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.dto.util.PageDto;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.services.interfaces.client.AccountService;
import com.trade_accounting.services.interfaces.client.EmployeeService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/api/account")
@Tag(name = "Account Rest Controller", description = "CRUD операции с аккаунтами")
@Api(tags = "Account Rest Controller")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    @PostMapping
    @ApiOperation(value = "create", notes = "Регистрация нового работника")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Работник успешно зарегестрирован"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "employeeDto", value = "DTO работника, который необходимо создать")
                                    @RequestBody AccountDto accountDto,
                                    @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok().body(accountService.create(accountDto, employeeDto));
    }
}
