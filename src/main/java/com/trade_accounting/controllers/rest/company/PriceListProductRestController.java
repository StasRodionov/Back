package com.trade_accounting.controllers.rest.company;

import com.trade_accounting.models.dto.company.PriceListProductDto;
import com.trade_accounting.models.entity.company.PriceListProduct;
import com.trade_accounting.repositories.company.PriceListProductRepository;
import com.trade_accounting.services.interfaces.company.PriceListProductService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "PriceList Product Rest Controller", description = "CRUD операции с прайс-листом")
@Api(tags = "PriceList Product Rest Controller")
@RequestMapping("/api/priceList/product")
@RequiredArgsConstructor
public class PriceListProductRestController {

    private final PriceListProductRepository priceListProductRepository;
    private final PriceListProductService priceListProductService;
    private final CheckEntityService checkEntityService;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех прайс-листов")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех прайс-листов"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PriceListProductDto>> getAll() {
        return ResponseEntity.ok(priceListProductService.getAll());
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка прайс-листов по заданным параметрам из фильтра")
    public ResponseEntity<List<PriceListProductDto>> getAll(
            @And({
                    @Spec(path = "products.id", params = "productID", spec = Equal.class),
                    @Spec(path = "products.name", params = "productName", spec = LikeIgnoreCase.class),
                    @Spec(path = "price", params = "price", spec = Equal.class),
                    @Spec(path = "products.productGroup.name", params = "groupName", spec = LikeIgnoreCase.class)
            }) Specification<PriceListProduct> spec) {
        return ResponseEntity.ok(priceListProductService.search(spec));
    }

    @ApiOperation(value = "quickSearch", notes = "Получение списка прайс-листов по заданным параметрам - название и комментарий")
    @GetMapping("/quickSearch")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка прайс-листов по заданным параметрам"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PriceListProductDto>> quickSearch(@RequestParam("search") String search) {
        return ResponseEntity.ok(priceListProductService.quickSearch(search));
    }

    @ApiOperation(value = "getById", notes = "Возвращает товар в прайс-листе по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Прайс-лист в накладной найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PriceListProductDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти прайс-лист",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository)priceListProductRepository,id);
        return ResponseEntity.ok(priceListProductService.getById(id));
    }

    @ApiOperation(value = "getByPriceListId", notes = "Возвращает список товаров в прайс-листе по PriceList.id")
    @GetMapping("/priceList/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в прайс-листе найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PriceListProductDto>> getByPriceListId(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо список товаров в накладной",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        List<PriceListProductDto> priceListProductDtoList = priceListProductService.searchByPriceListId(id);
        return ResponseEntity.ok(priceListProductDtoList);
    }

    @ApiOperation(value = "create", notes = "Добавляет товар в прайс-лист на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в прайс-лист успешно добавлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "priceListProductDto",
            value = "DTO PriceListProductDto, который необходимо создать") @RequestBody PriceListProductDto priceListProductDto) {
        return ResponseEntity.ok().body(priceListProductService.create(priceListProductDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет товар в прайс-листе на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в прайс-лист успешно обновлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "priceListProductDto",
            value = "DTO PriceListProductDto, который необходимо обновить") @RequestBody PriceListProductDto priceListProductDto) {
        return ResponseEntity.ok().body(priceListProductService.update(priceListProductDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет товар в прайс-листе на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в прайс-листе успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить товар в прайс-листе",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        priceListProductService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "getByProductId", notes = "Возвращает список товаров в прайс-листе по Product.id")
    @GetMapping("/product/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в прайс-листе найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PriceListProductDto>> getByProductId(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо список товаров в прайс-листе",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        List<PriceListProductDto> priceListProductDtoList = priceListProductService.getAllByProductId(id);
        return ResponseEntity.ok(priceListProductDtoList);
    }
}

