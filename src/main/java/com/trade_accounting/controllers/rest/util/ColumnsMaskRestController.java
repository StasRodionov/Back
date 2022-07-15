package com.trade_accounting.controllers.rest.util;


import com.trade_accounting.models.dto.util.ColumnsMaskDto;
import com.trade_accounting.services.interfaces.util.ColumnsMaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@Tag(name = "ColumnsMask Rest Controller", description = "CRUD операции с масками видимостей колонок")
@Api(tags = "ColumnsMask Rest Controller")
@RequestMapping("/api/columns-mask")
@RequiredArgsConstructor
public class ColumnsMaskRestController {

    private final ColumnsMaskService columnsMaskService;

    @ApiOperation(value = "getById", notes = "Возвращает маску определенной таблицы по её Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Маска найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ColumnsMaskDto> getById(@ApiParam(name = "id", value = "ID переданный в URL по которому необходимо найти маску таблицы")
            @PathVariable(name = "id") Integer id, Principal principal) {
        return ResponseEntity.ok(columnsMaskService.getById(id, principal));
    }

    @ApiOperation(value = "create", notes = "Создает маску определенной таблицы на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Маска успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ColumnsMaskDto> create(@ApiParam(name = "columnsMaskDto", value = "DTO маски, которую необходимо создать")
                                             @RequestBody ColumnsMaskDto columnsMaskDto, Principal principal) {
        return ResponseEntity.ok().body(columnsMaskService.create(columnsMaskDto, principal));
    }

    @ApiOperation(value = "update", notes = "Обновляет маску определенной таблицы на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Маска успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ColumnsMaskDto> update(@ApiParam(name = "columnsMaskDto", value = "DTO маски, c обновленными данными")
                                             @RequestBody ColumnsMaskDto columnsMaskDto, Principal principal) {
        return ResponseEntity.ok().body(columnsMaskService.update(columnsMaskDto, principal));
    }



}
