package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.TechnicalOperations;
import com.trade_accounting.models.dto.TechnicalOperationsDto;
import com.trade_accounting.repositories.TechnicalOperationsRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.TechnicalOperationsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Technical Operations Rest Controller", description = "CRUD операции с техническими операциями")
@Api(tags = "Technical Operations Rest Controller")
@RequestMapping("/api/technical/operations")
@RequiredArgsConstructor
public class TechnicalOperationsRestController {

    private final TechnicalOperationsRepository technicalOperationsRepository;
    private final TechnicalOperationsService technicalOperationsService;
    private final CheckEntityService checkEntityService;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех технических операции")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех технических операции"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<TechnicalOperationsDto>> getAll() {
        List<TechnicalOperationsDto> technicalOperations = technicalOperationsService.getAll();
        return ResponseEntity.ok(technicalOperations);
    }

//    @ApiOperation(value = "search", notes = "Получение списка технических операции по заданным параметрам")
//    @GetMapping("/search")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Успешное получение списка технических операции по заданным параметрам"),
//            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
//            @ApiResponse(code = 403, message = "Операция запрещена"),
//            @ApiResponse(code = 404, message = "Данный контроллер не найден")
//    })
//    public ResponseEntity<List<TechnicalOperationsDto>> getAll(@RequestParam("query") String value) {
//        return ResponseEntity.ok(technicalOperationsService.search(value));
//    }

    @ApiOperation(value = "getById", notes = "Возвращает определенную техническую операцию по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Техническая операция найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalOperationsDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти техническую операцию") @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) technicalOperationsRepository, id);
        return ResponseEntity.ok(technicalOperationsService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает техническую операцию на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Техническая операция успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalOperationsDto> create(@ApiParam(name = "technicalOperationsDto", value = "DTO технической операции, которую необходимо создать")
                                                   @RequestBody TechnicalOperationsDto technicalOperationsDto) {
        return ResponseEntity.ok().body(technicalOperationsService.create(technicalOperationsDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет техническую операцию на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Техническая операция успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalOperationsDto> update(@ApiParam(name = "technicalOperationsDto",
            value = "DTO технической операции, c обновленными данными")
                                                   @RequestBody TechnicalOperationsDto technicalOperationsDto) {
        return ResponseEntity.ok().body(technicalOperationsService.update(technicalOperationsDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет техническую операцию на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Техническая операция успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<TechnicalOperationsDto> deleteById(@ApiParam(name = "id",
            value = "ID технической операции, которую необходимо удалить")
                                                       @PathVariable(name = "id") Long id) {
        technicalOperationsService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("searchTechnicalCard")
    @ApiOperation(value = "searchTechnicalCard", notes = "Получение списка технических карт по заданным параметрам")
    public ResponseEntity<List<TechnicalOperationsDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "dateOperation", params = "dateOperation", spec = GreaterThanOrEqual.class),
                    @Spec(path = "technicalCard.name", params = "technicalCard", spec = LikeIgnoreCase.class),
                    @Spec(path = "volume", params = "volume", spec = LikeIgnoreCase.class),
                    @Spec(path = "warehouse.name", params = "warehouse", spec = LikeIgnoreCase.class),

            }) Specification<TechnicalOperations> spec) {
        return ResponseEntity.ok(technicalOperationsService.search(spec));
    }
}
