package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
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
@RequestMapping("/api/employee")
@Tag(name = "Employee Rest Controller", description = "CRUD операции с работниками")
@Api(tags = "Employee Rest Controller")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final CheckEntityService checkEntityService;

    public EmployeeRestController(EmployeeService employeeService, CheckEntityService checkEntityService) {
        this.employeeService = employeeService;
        this.checkEntityService = checkEntityService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех работников")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка работников"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<EmployeeDto>> getAll(){
        List<EmployeeDto> employeeDtos = employeeService.getAll();
        log.info("Запрошен список EmployeeDto");
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка работников по заданным параметрам")
    public ResponseEntity<List<EmployeeDto>> getAll(
            @And({
                    @Spec(path = "lastName", params = "lastName", spec = LikeIgnoreCase.class),
                    @Spec(path = "firstName", params = "firstName", spec = LikeIgnoreCase.class),
                    @Spec(path = "middleName", params = "middleName", spec = LikeIgnoreCase.class),
                    @Spec(path = "email", params = "email", spec = LikeIgnoreCase.class),
                    @Spec(path = "phone", params = "phone", spec = LikeIgnoreCase.class),
                    @Spec(path = "description", params = "description", spec = LikeIgnoreCase.class),
                    @Spec(path = "roleDto", params = "roleDto", spec = LikeIgnoreCase.class)
            }) Specification<Employee> spec) {
        log.info("Запрошен поиск работника");
        return ResponseEntity.ok(employeeService.search(spec));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение работника по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Работник найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<EmployeeDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти работника")
                                                   @PathVariable(name = "id") Long id) {
        checkEntityService.checkExistsEmployeeById(id);
        EmployeeDto employeeDto = employeeService.getById(id);
        log.info("Запрошен экземпляр EmployeeDto с id = {}", id);
        return ResponseEntity.ok(employeeDto);
    }


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
                                        @RequestBody EmployeeDto employeeDto){
        checkEntityService.checkForBadEmployee(employeeDto);
        employeeService.create(employeeDto);
        log.info("Записан новый экземпляр EmployeeDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Обновление информации о работнике")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о работнике успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "employeeDto",
            value = "DTO работника, c обновленными данными")
                                        @RequestBody EmployeeDto employeeDto) {
        checkEntityService.checkExistsEmployeeById(employeeDto.getId());
        checkEntityService.checkForBadEmployee(employeeDto);
        employeeService.update(employeeDto);
        log.info("Обновлен экземпляр EmployeeDto с id = {}", employeeDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление работника по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о работнике успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id",
            value = "ID работника, которого необходимо удалить")
                                            @PathVariable(name = "id") Long id) {
        employeeService.deleteById(id);
        log.info("Удален экземпляр EmployeeDto с id = {}", id);
        return ResponseEntity.ok().build();
    }
}
