package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.services.interfaces.ProductGroupService;
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
@Tag(name = "Product Group Rest Controller", description = "CRUD операции с продуктовыми группами")
@Api(tags = "Product Group Rest Controller")
@RequestMapping("/api/productgroup")
public class ProductGroupRestController {

    private final ProductGroupService productGroupService;

    public ProductGroupRestController(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех продуктовых групп")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка продуктовых групп"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<List<ProductGroupDto>> getAll() {
        List<ProductGroupDto> productGroups = productGroupService.getAll();
        log.info("Запрошен список ProductGroupDto");
        return ResponseEntity.ok(productGroups);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение продуктовой группы по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Продуктовая группа успешно найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    public ResponseEntity<ProductGroupDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо найти продуктовую группу")
            @PathVariable(name = "id") Long id) {
        ProductGroupDto productGroup = productGroupService.getById(id);
        log.info("Запрошен экземпляр ProductGroupDto с id= {}", id);
        return ResponseEntity.ok(productGroup);
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Создание новой продуктовой группы")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аккаунт продуктовой группы создан"),
            @ApiResponse(code = 201, message = "Запрос принят и продуктовая группа добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "productGroupDto",
            value = "DTO продуктовой группы, которую необходимо создать")
                                        @RequestBody ProductGroupDto productGroupDto){
        productGroupService.create(productGroupDto);
        log.info("Записан новый экземпляр ProductGroup с id= {}, name= {}", productGroupDto.getId(),
                productGroupDto.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о продуктовой группе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о продуктовой группе обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о продуктовой группе обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "ProductGroupDto", value = "DTO продуктовой группы, которую необходимо обновить")
            @RequestBody ProductGroupDto productGroupDto) {
        productGroupService.update(productGroupDto);
        log.info("Обновлен экземпляр ProductGroup с id= {}, name= {}", productGroupDto.getId(), productGroupDto.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление продуктовой группы по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аккаунт продуктовой группы удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить продуктовую группу")
            @PathVariable(name = "id") Long id) {
        productGroupService.deleteById(id);
        log.info("Удален экземпляр ProductGroup с id= {}", id);
        return ResponseEntity.ok().build();
    }

}
