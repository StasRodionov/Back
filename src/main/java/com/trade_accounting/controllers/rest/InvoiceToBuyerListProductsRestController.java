package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.InvoiceToBuyerListProducts;
import com.trade_accounting.models.dto.InvoiceToBuyerListProductsDto;
import com.trade_accounting.repositories.InvoiceToBuyerListProductsRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.InvoiceToBuyerListProductsService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Invoice To Buyer List Products Rest Controller", description = "CRUD операции с товарами в счёте")
@Api(tags = "Invoice To Buyer List Products Rest Controller")
@RequestMapping("api/invoice/tobuyerlistproducts")
@RequiredArgsConstructor
public class InvoiceToBuyerListProductsRestController {

    private final InvoiceToBuyerListProductsService service;
    private final CheckEntityService checkEntityService;
    private final InvoiceToBuyerListProductsRepository repository;

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка товаров из счёта по заданным параметрам из фильтра")
    public ResponseEntity<List<InvoiceToBuyerListProductsDto>> getAll(
            @And({
                    @Spec(path = "product.name", params = "productDto", spec = LikeIgnoreCase.class),
                    @Spec(path = "amount", params = "amount", spec = Equal.class),
                    @Spec(path = "price", params = "price", spec = Equal.class),
                    @Spec(path = "product.purchasePrice", params = "costPrice", spec = Equal.class),
                    @Spec(path = "product.description", params = "description", spec = LikeIgnoreCase.class)
            }) Specification<InvoiceToBuyerListProducts> spec) {
        return ResponseEntity.ok(service.search(spec));
    }

    @ApiOperation(value = "getAll", notes = "Возвращает список всех товаров из счёта")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех товаров из счёта"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InvoiceToBuyerListProductsDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @ApiOperation(value = "getById", notes = "Возвращает товар из счёта по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в счёте найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<InvoiceToBuyerListProductsDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти товар в счёте",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository)repository,id);
        return ResponseEntity.ok(service.getById(id));
    }

    @ApiOperation(value = "getBySupplierId", notes = "Возвращает список товаров из счёта по Supplier.id")
    @GetMapping("/invoice_to_buyer_list_products/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в счёте найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<InvoiceToBuyerListProductsDto>> getBySupplierId(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо список товаров в счёте",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        List<InvoiceToBuyerListProductsDto> invoiceToBuyerListProductsDtoList = service.searchBySupplierId(id);
        return ResponseEntity.ok(invoiceToBuyerListProductsDtoList);
    }

    @ApiOperation(value = "create", notes = "Добавляет товар в счёт на основе переданных данных")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в счёт успешно добавлен"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "invoiceToBuyerListProductsDto",
            value = "DTO товара в счёте, который необходимо создать") @RequestBody InvoiceToBuyerListProductsDto invoiceProductDto) {
        return ResponseEntity.ok().body(service.create(invoiceProductDto));
    }

    @ApiOperation(value = "update", notes = "Обновляет товар в счёте на основе переданных данных")
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в счёте успешно обновлён"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "invoiceToBuyerListProductsDto",
            value = "DTO InvoiceToBuyersListProducts, который необходимо обновить") @RequestBody InvoiceToBuyerListProductsDto invoiceToBuyerListProductsDto) {
        return ResponseEntity.ok().body(service.update(invoiceToBuyerListProductsDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет товар в счёте на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Товар в счёте успешно удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить товар в счёте",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
