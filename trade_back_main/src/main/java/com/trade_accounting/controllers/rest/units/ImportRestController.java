package com.trade_accounting.controllers.rest.units;

import com.trade_accounting.models.dto.units.ImportDto;
import com.trade_accounting.models.entity.units.Import;
import com.trade_accounting.repositories.units.ImportRepository;
import com.trade_accounting.services.interfaces.units.ImportService;
import com.trade_accounting.services.interfaces.util.CheckEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
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
@Tag(name = "Import Rest Controller", description = "CRUD операции с импортами")
@Api(tags = "Import Rest Controller")
@RequestMapping("/api/import")
@RequiredArgsConstructor
public class ImportRestController {

    private final CheckEntityService checkEntityService;
    private final ImportService importService;
    private final ImportRepository importRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех импортов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех импортов"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @GetMapping
    public ResponseEntity<List<ImportDto>> getAll() {
        List<ImportDto> importDtos = importService.getAll();
        return ResponseEntity.ok(importDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Возвращает импорт по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Импорт найден"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ImportDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо найти импорт",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) importRepository, id);
        return ResponseEntity.ok(importService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создание нового импорта")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Импорт успешно создан"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PostMapping
    public ResponseEntity<ImportDto> create(@ApiParam(
            name = "importDto",
            value = "DTO импорта,который необходимо создать")@RequestBody ImportDto importDto) {
        return ResponseEntity.ok().body(importService.create(importDto));
    }

    @ApiOperation(value = "update", notes = "Обновление информации об импорте")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация об импорте успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PutMapping
    public ResponseEntity<ImportDto> update(@ApiParam(
            name = "importDto",
            value = "DTO импорта, который необходимо обновить")@RequestBody ImportDto importDto) {
        return ResponseEntity.ok().body(importService.update(importDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаление импорта по его id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Импорт удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо удалить импорт") @PathVariable Long id) {
        importService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка импортов по заданным параметрам")
    public ResponseEntity<List<ImportDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "task", params = "task", spec = LikeIgnoreCase.class),
                    @Spec(path = "start", params = "start", spec = LikeIgnoreCase.class),
                    @Spec(path = "end", params = "end", spec = Like.class),
                    @Spec(path = "status", params = "status", spec = Like.class),
            }) Specification<Import> spec) {
        return ResponseEntity.ok(importService.search(spec));
    }

    @GetMapping("/searchByString")
    @ApiOperation(value = "search", notes = "Получение списка импортов по заданным параметрам")
    public ResponseEntity<List<ImportDto>> searchByString(@RequestParam("search") String search) {
        return ResponseEntity.ok(importService.searchByString(search));
    }
}
