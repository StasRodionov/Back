package com.trade_accounting.controllers.rest.warehouse;


import com.trade_accounting.models.dto.warehouse.KitDto;
import com.trade_accounting.models.dto.warehouse.ProductDto;
import com.trade_accounting.models.dto.warehouse.ServicesDto;
import com.trade_accounting.repositories.warehouse.KitRepository;
import com.trade_accounting.repositories.warehouse.ServicesRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.KitService;
import com.trade_accounting.services.interfaces.warehouse.ServicesService;
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
@Tag(name = "Services Rest Controller", description = "CRUD операции с услугами")
@Api(tags = "Services Rest Controller")
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServicesRestController {

    private final ServicesService servicesService;
    private final CheckEntityService checkEntityService;
    private final ServicesRepository servicesRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех услуг")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех услуг"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ServicesDto>> getAll() {
        List<ServicesDto> services = servicesService.getAll();
        return ResponseEntity.ok(services);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенную услугу по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Услуга найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ServicesDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти услугу") @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) servicesRepository, id);
        return ResponseEntity.ok(servicesService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает услугу на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Услуга успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ServicesDto> create(@ApiParam(name = "servicesDto", value = "DTO услуги, которую необходимо создать")
                                             @RequestBody ServicesDto servicesDto) {
        return ResponseEntity.ok().body(servicesService.create(servicesDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет услугу на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Услуга успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ServicesDto> update(@ApiParam(name = "servicesDto",
            value = "DTO улуги c обновленными данными")
                                             @RequestBody ServicesDto servicesDto) {
        return ResponseEntity.ok().body(servicesService.update(servicesDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет услугу на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Услуга успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductDto> deleteById(@ApiParam(name = "id",
            value = "ID услуги, которую необходимо удалить")
                                                 @PathVariable(name = "id") Long id) {
        servicesService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
