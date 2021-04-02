package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.PaymentDto;
import com.trade_accounting.services.interfaces.PaymentService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "Payment Rest Controller", description = "CRUD  операции с платежами")
@Api(tags = "Payment Rest Controller")
@RequestMapping("/api/payment")
public class PaymentRestController {

    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех платежей")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка платежей"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PaymentDto>> getAll(){
        List<PaymentDto> paymentDtoList = paymentService.getAll();
        log.info("Запрошен список платежей");
        return ResponseEntity.ok(paymentDtoList);
    }

    @ApiOperation(value = "search", notes = "Получение списка товаров по заданным параметрам")
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка платежей"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден")
    })
    public ResponseEntity<List<PaymentDto>> getAll(@RequestParam("query") String value) {
        log.info("Запрошен поиск платежей");
        return ResponseEntity.ok(paymentService.search(value));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение платежа по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Платеж найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PaymentDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти платеж")
                                              @PathVariable(name = "id") Long id){
        PaymentDto paymentDto = paymentService.getById(id);
        log.info("Запрошен экземпляр платежа с id = {}", id);
        return ResponseEntity.ok(paymentDto);
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового платежа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Платеж создан"),
            @ApiResponse(code = 201, message = "Запрос принят и платеж добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "paymentDto", value = "DTO платежа, который необходимо создать")
                                    @RequestBody PaymentDto paymentDto){
        paymentService.create(paymentDto);
        log.info("Записан новый экземпляр платежа - {}", paymentDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о платеже")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о платеже обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о платеже обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "paymentDto", value = "DTO платежа, который необходимо обновить")
                                    @RequestBody PaymentDto paymentDto) {
        paymentService.update(paymentDto);
        log.info("Обновлен экземпляр платежа с id = {}", paymentDto.getId());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление платежа по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Платеж удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить палтеж")
                                        @PathVariable(name = "id") Long id) {
        paymentService.deleteById(id);
        log.info("Удален экземпляр платежа с id = {}", id);
        return ResponseEntity.ok().build();
    }
}
