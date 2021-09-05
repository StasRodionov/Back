package com.trade_accounting.controllers.rest;


import com.trade_accounting.models.dto.ProductPriceDto;
import com.trade_accounting.repositories.ProductPriceRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.ProductPriceService;
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
@Tag(name = "ProductPrice Rest Controller", description = "CRUD операции с ProductPrice")
@Api(tags = "ProductPrice Rest Controller")
@RequestMapping("/api/productprice")
@RequiredArgsConstructor
public class ProductPriceRestController {
    private final ProductPriceService productPriceService;
    private final CheckEntityService checkEntityService;
    private final ProductPriceRepository productPriceRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех цен на товары (лёгкое дто)")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех цен на товары (лёгкое дто)"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<ProductPriceDto>> getAll() {
        List<ProductPriceDto> productPrices = productPriceService.getAll();
        return ResponseEntity.ok(productPrices);
    }

//    @ApiOperation(value = "search", notes = "Получение списка товаров по заданным параметрам")
//    @GetMapping("/search")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Успешное получение списка всех товаров (лёгкое дто)"),
//            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
//            @ApiResponse(code = 403, message = "Операция запрещена"),
//            @ApiResponse(code = 404, message = "Данный контроллер не найден")
//    })
//    public ResponseEntity<List<ProductPriceDto>> getAll(@RequestParam("query") String value) {
//        return ResponseEntity.ok(productPriceService.search(value));
//    }

//    @GetMapping("/searchByFilter")
//    @ApiOperation(value = "search", notes = "Получение списка товаров по фильтру")
//    public ResponseEntity<List<ProductDto>> getAllByFilter(
//            @And({
//                    @Spec(path = "id", params = "id", spec = Equal.class),
//                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
//                    @Spec(path = "weight", params = "weight", spec = Equal.class),
//                    @Spec(path = "volume", params = "volume", spec = Equal.class),
//                    @Spec(path = "description", params = "description", spec = LikeIgnoreCase.class),
//                    @Spec(path = "purchasePrice", params = "purchasePrice", spec = Equal.class),
//            }) Specification<Product> spec) {
//        return ResponseEntity.ok(productService.search(spec));
//    }

    @ApiOperation(value = "getById", notes = "Возвращает определенную цену на товар по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Цена на товар найдена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductPriceDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти цену на товар") @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) productPriceRepository, id);
        return ResponseEntity.ok(productPriceService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создает цену на товар на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Цена на товар успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductPriceDto> create(@ApiParam(name = "productPriceDto", value = "DTO цены на товар, которую необходимо создать")
                                             @RequestBody ProductPriceDto productPriceDto) {
        return ResponseEntity.ok().body(productPriceService.create(productPriceDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет цену на товар на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Цена успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductPriceDto> update(@ApiParam(name = "productDto",
            value = "DTO цены на товар, c обновленными данными")
                                             @RequestBody ProductPriceDto productPriceDto) {
        return ResponseEntity.ok().body(productPriceService.update(productPriceDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет цену на товар на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Цена на товар успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<ProductPriceDto> deleteById(@ApiParam(name = "id",
            value = "ID цены на товар, которую необходимо удалить")
                                                 @PathVariable(name = "id") Long id) {
        productPriceService.deleteById(id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/pages")
//    @ApiOperation(value = "searchWithPages", notes = "Получение списка товаров по заданным параметрам постранично")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Успешное получение страницы товаров"),
//            @ApiResponse(code = 404, message = "Данный контролер не найден"),
//            @ApiResponse(code = 403, message = "Операция запрещена"),
//            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
//    )
//    public ResponseEntity<PageDto<ProductDto>> searchWithPages(
//            @Conjunction(value = {
//                    @Or({
//                            @Spec(path = "weight", params = "search", spec = LikeIgnoreCase.class),
//                            @Spec(path = "volume", params = "search", spec = LikeIgnoreCase.class),
//                            @Spec(path = "purchase_price", params = "search", spec = LikeIgnoreCase.class),
//                            @Spec(path = "description", params = "search", spec = LikeIgnoreCase.class),
//                            @Spec(path = "name", params = "search", spec = LikeIgnoreCase.class)
//
//                    })
//            }, and = {
//                    @Spec(path = "weight", params = "weight", spec = LikeIgnoreCase.class),
//                    @Spec(path = "volume", params = "volume", spec = LikeIgnoreCase.class),
//                    @Spec(path = "purchase_price", params = "purchase_price", spec = LikeIgnoreCase.class),
//                    @Spec(path = "description", params = "description", spec = LikeIgnoreCase.class),
//                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
//                    @Spec(path = "archive", params = "archive", spec = LikeIgnoreCase.class),
//                    @Spec(path = "countryOrigin", params = "countryOrigin", spec = LikeIgnoreCase.class),
//                    @Spec(path = "minimumBalance", params = "minimumBalance", spec = LikeIgnoreCase.class),
//                    @Spec(path = "saleTax", params = "saleTax", spec = LikeIgnoreCase.class)
//            }) Specification<Product> specification,
//            @RequestParam("column") String sortColumn,
//            @RequestParam("direction") String sortDirection,
//            @RequestParam("pageNumber") Integer pageNumber,
//            @RequestParam("rowsLimit") Integer rowsLimit) {
//        Pageable pageParams = PageRequest.of(pageNumber - 1, rowsLimit,
//                Sort.by(sortDirection.equals("ASCENDING") ?
//                                Sort.Direction.ASC : Sort.Direction.DESC,
//                        sortColumn));
//        return ResponseEntity.ok(productService.search(specification, pageParams));
//    }
}
