package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.services.interfaces.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Product Account Rest Controller")
@RequestMapping("/api/product")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "getAll", notes = "Возвращает список всех товаров")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех товаров"),
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
    public ResponseEntity<ProductDto> getById(@PathVariable(name = "id") Long id) {
        ProductDto productGroup = productService.getById(id);
        log.info("Запрошен экземпляр ProductDto с id= {}", id);
        return ResponseEntity.ok(productGroup);
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
    public ResponseEntity<?> create(@RequestBody ProductDto dto) {
        productService.create(dto);
        log.info("Записан новый экземпляр ProductDto с id= {}, name= {}", dto.getId(), dto.getName());
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
    public ResponseEntity<?> update(@RequestBody ProductDto productDto) {
        productService.update(productDto);
        log.info("Обновлен экземпляр ProductDto с id= {}, name= {}", productDto.getId(), productDto.getName());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет склад на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        log.info("Удален экземпляр ProductDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
