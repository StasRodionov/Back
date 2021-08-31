package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.SupplierAccount;
import com.trade_accounting.models.dto.SupplierAccountDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.SupplierAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
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

    @GetMapping("/search/{nameFilter}")
    @ApiOperation(value = "searchTerm", notes = "Получение списка некоторых счетов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение отф. списка контрагентов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<SupplierAccountDto>> searchByNameFilter(@ApiParam(name ="nameFilter",
            value = "Переданный в URL searchTerm, по которому необходимо найти контрагента")
                                                                       @PathVariable(name = "nameFilter") String nameFilter) {
        List<SupplierAccountDto> listSupplier = invoices.searchByString(nameFilter);
        return ResponseEntity.ok(listSupplier);

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
            checkEntityService.checkExists((JpaRepository) invoices, id);
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


    @GetMapping("/querySupplier")
    @ApiOperation(value = "searchByFilter", notes = "Получение списка счетов  по заданным параметрам")
    public ResponseEntity<List<SupplierAccountDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "date", params = "date", spec = Equal.class),
                    @Spec(path = "contractor.name", params = "contractorDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "company.name", params = "companyDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "warehouse.name", params = "warehouseDto", spec = LikeIgnoreCase.class),
            })Specification<SupplierAccount> supplier) {
        return ResponseEntity.ok(invoices.search(supplier));
    }




}
