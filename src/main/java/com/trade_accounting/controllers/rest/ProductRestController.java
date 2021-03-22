package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.services.interfaces.ProductService;
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

/**
 * RestController для ProductDto с реализацией ProductService
 *
 * @author Sanych
 * @see ProductService
 */
@Slf4j
@RestController
@Tag(name = "Product Rest Controller", description = "CRUD операции с товаром")
@Api(tags = "Product Rest Controller")
@RequestMapping("/api/product")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


    @ApiOperation(value = "getAll", notes = "Возвращает список всех товаров (лёгкое дто)")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех товаров (лёгкое дто)"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> productGroups = productService.getAll();
        log.info("Запрошен список ProductDto");
        return ResponseEntity.ok(productGroups);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенный товар по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти товар") @PathVariable(name = "id") Long id) {
        ProductDto productGroup = productService.getById(id);
        log.info("Запрошен экземпляр ProductDto с id= {}", id);
        return ResponseEntity.ok(productGroup);
    }

    @ApiOperation(value = "getByProductGroupId", notes = "Возвращает товары из определенной группы")
    @GetMapping("/pg/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductDto>> getByProductGroupId(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти товар") @PathVariable(name = "id") Long id) {
            List<ProductDto> productGroups = productService.getAllByProductGroupId(id);
            log.info("Запрошен список ProductDto");
            return ResponseEntity.ok(productGroups);
    }

    @ApiOperation(value = "getByContractorId", notes = "Возвращает товары из определенной группы")
    @GetMapping("/cnt/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductDto>> getAllByContractorId(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти товар") @PathVariable(name = "id") Long id) {
            List<ProductDto> productGroups = productService.getAllByContractorId(id);
            log.info("Запрошен список ProductDto");
            return ResponseEntity.ok(productGroups);
    }

    @ApiOperation(value = "getLiteByProductGroupId", notes = "Возвращает товары из определенной группы (лёгкое дто)")
    @GetMapping("lite/pg/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductDto>> getLiteByProductGroupId(
            @ApiParam(name = "id", value = "ID группы товаров, переданный в URL, по которому необходимо найти товары")
            @PathVariable(name = "id") Long id) {
        List<ProductDto> productGroups = productService.getAllByProductGroupId(id);
        log.info("Запрошен список ProductDto (Легкое Дто) принадлежащих гуруппе с id = {}", id);
        return ResponseEntity.ok(productGroups);
    }

    @ApiOperation(value = "create", notes = "Создает товар на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductDto> create(@ApiParam(name = "productDto", value = "DTO товара, который необходимо создать")
                                    @RequestBody ProductDto productDto) {
        productService.save(productDto);
        log.info("Записан новый экземпляр ProductDto с id= {}, name= {}", productDto.getId(), productDto.getName());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "update", notes = "Обновляет товар на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductDto> update(@ApiParam(name = "productDto",
            value = "DTO товара, c обновленными данными")
                                    @RequestBody ProductDto productDto) {
        productService.save(productDto);
        log.info("Обновлен экземпляр ProductDto с id= {}, name= {}", productDto.getId(), productDto.getName());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет товар на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductDto> deleteById(@ApiParam(name = "id",
            value = "ID товара, который необходимо удалить")
                                        @PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        log.info("Удален экземпляр ProductDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
