package com.trade_accounting.controllers.rest.warehouse;


import com.trade_accounting.models.dto.warehouse.KitDto;
import com.trade_accounting.models.dto.warehouse.ProductDto;
import com.trade_accounting.models.entity.warehouse.Kit;
import com.trade_accounting.repositories.warehouse.KitRepository;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import com.trade_accounting.services.interfaces.warehouse.KitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Kit Rest Controller", description = "CRUD операции с наборами")
@Api(tags = "Kit Rest Controller")
@RequestMapping("/api/kit")
@RequiredArgsConstructor
public class KitRestController {

    private final KitService kitService;
    private final CheckEntityService checkEntityService;
    private final KitRepository kitRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех наборов")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех наборов"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<KitDto>> getAll() {
        List<KitDto> kits = kitService.getAll();
        return ResponseEntity.ok(kits);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный набор по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Набор найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<KitDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти набор") @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) kitRepository, id);
        return ResponseEntity.ok(kitService.getById(id));
    }
}
