package com.trade_accounting.controllers.rest.units;

import com.trade_accounting.models.dto.units.OnlineStoreDto;
import com.trade_accounting.models.entity.units.OnlineStore;
import com.trade_accounting.repositories.units.OnlineStoreRepository;
import com.trade_accounting.services.interfaces.units.OnlineStoreService;
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
@Tag(name = "OnlineStore Rest Controller", description = "CRUD операции с Интернет Магазинами")
@Api(tags = "OnlineStore Rest Controller")
@RequestMapping("/api/online_store")
@RequiredArgsConstructor
public class OnlineStoreRestController {

    private final CheckEntityService checkEntityService;
    private final OnlineStoreRepository onlineStoreRepository;
    private final OnlineStoreService onlineStoreService;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех Интернет магазинов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение всех Интернет магазинов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    @GetMapping
    public ResponseEntity<List<OnlineStoreDto>> getAll() {
        List<OnlineStoreDto> dtos = onlineStoreService.getAll();
        return ResponseEntity.ok(dtos);
    }

    @ApiOperation(value = "getById", notes = "Возвращает Интернет магазин по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение Интернет магазина по id"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OnlineStoreDto> getById(
            @ApiParam(name = "id",
                    type = "Long",
                    value = "id, по которому надо найти Интернет магазин",
                    example = "1",
                    required = true)
            @PathVariable("id") Long id) {
        checkEntityService.checkExists((JpaRepository) onlineStoreRepository, id);
        OnlineStoreDto dto = onlineStoreService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "create", notes = "Создание нового Интернет магазина")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Интернет магазин успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    @PostMapping
    public ResponseEntity<OnlineStoreDto> create(
            @ApiParam(name = "onlineStoreDto",
                    value = "Dto, которое нужно создать")
            @RequestBody OnlineStoreDto onlineStoreDto) {
        OnlineStoreDto dto = onlineStoreService.create(onlineStoreDto);
        return ResponseEntity.ok().body(dto);
    }

    @ApiOperation(value = "update", notes = "Обновление информации об Интернет магазине")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о магазине успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")
    })
    @PutMapping
    public ResponseEntity<OnlineStoreDto> update(
            @ApiParam(name = "onlineStoreDto",
                    value = "Dto, которое нужно обновить")
            @RequestBody OnlineStoreDto onlineStoreDto) {
        OnlineStoreDto dto = onlineStoreService.update(onlineStoreDto);
        return ResponseEntity.ok().body(dto);
    }

    @ApiOperation(value = "deleteById", notes = "Удаление магазина по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Магазин удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(
            @ApiParam(name = "id", type = "Long", value = "id, по которому необходимо удалить магазин")
            @PathVariable("id") Long id) {
        onlineStoreService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "search", notes = "Получение списка магазинов по заданным параметрам")
    @GetMapping("/search")
    public ResponseEntity<List<OnlineStoreDto>> search(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "type", params = "type", spec = LikeIgnoreCase.class),
                    @Spec(path = "order", params = "type", spec = LikeIgnoreCase.class),
                    @Spec(path = "remains", params = "remains", spec = LikeIgnoreCase.class),
            })
            Specification<OnlineStore> spec) {
        List<OnlineStoreDto> dtos = onlineStoreService.search(spec);
        return ResponseEntity.ok(dtos);
    }

    @ApiOperation(value = "searchByString", notes = "Получение списка магазинов по заданным параметрам")
    @GetMapping("/searchByString")
    public ResponseEntity<List<OnlineStoreDto>> searchByString(@RequestParam("search") String text) {
        List<OnlineStoreDto> dtos = onlineStoreService.searchByString(text);
        return ResponseEntity.ok(dtos);
    }

}
