package com.trade_accounting.controllers.rest;


import com.trade_accounting.models.dto.OrdersOfProductionDto;
import com.trade_accounting.repositories.OrdersOfProductionRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.OrdersOfProductionService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Orders Of Production Rest Controller", description = "CRUD операции с заказами на производство")
@Api(tags = "Orders Of Production Rest Controller")
@RequestMapping("/api/orders_of_production")
@RequiredArgsConstructor
public class OrdersOfProductionRestController {

    private final OrdersOfProductionRepository ordersOfProductionRepository;
    private final OrdersOfProductionService ordersOfProductionService;
    private final CheckEntityService checkEntityService;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех заказов на производство")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех заказов на производство"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<OrdersOfProductionDto>> getAll() {
        List<OrdersOfProductionDto> ordersOfProduction = ordersOfProductionService.getAll();
        return ResponseEntity.ok(ordersOfProduction);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенную заказ на производство по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "заказ на производство найден"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<OrdersOfProductionDto> getById(@ApiParam(name = "id",
            value = "ID переданный в URL по которому необходимо найти заказ на производство") @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) ordersOfProductionRepository, id);
        return ResponseEntity.ok(ordersOfProductionService.getById(id));
    }

    @ApiOperation(value = "deleteById", notes = "Удаляет заказ на производство на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Заказ на производство успешно удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<OrdersOfProductionDto> deleteById(@ApiParam(name = "id",
            value = "ID заказа на производство, которую необходимо удалить")
                                                                @PathVariable(name = "id") Long id) {
        ordersOfProductionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("searchOrdersOfProduction")
//    @ApiOperation(value = "searchOrdersOfProduction", notes = "Получение списка заказ на производство по заданным параметрам")
//    public ResponseEntity<List<OrdersOfProductionDto>> getAllFilter(
//            @And({
//                    @Spec(path = "id", params = "id", spec = Equal.class),
//                    @Spec(path = "dateOperation", params = "dateOperation", spec = GreaterThanOrEqual.class),
//                    @Spec(path = "technicalCard.name", params = "technicalCard", spec = LikeIgnoreCase.class),
//                    @Spec(path = "volume", params = "volume", spec = LikeIgnoreCase.class),
//                    @Spec(path = "warehouse.name", params = "warehouse", spec = LikeIgnoreCase.class),
//
//            }) Specification<OrdersOfProductionDto> spec) {
//        return ResponseEntity.ok(ordersOfProductionService.search(spec));
//    }

    @ApiOperation(value = "search", notes = "Получение списка заказов на производство по заданным параметрам")
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка технических карт по заданным параметрам"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<OrdersOfProductionDto>> getAll(@RequestParam("query") String value) {
        return ResponseEntity.ok(ordersOfProductionService.search(value));
    }
}
