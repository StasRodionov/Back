package com.trade_accounting.controllers.rest.units;


import com.trade_accounting.models.dto.units.CountryDto;
import com.trade_accounting.models.dto.units.ImportDto;
import com.trade_accounting.models.entity.units.Country;
import com.trade_accounting.models.entity.units.Import;
import com.trade_accounting.repositories.units.CountryRepository;
import com.trade_accounting.services.interfaces.units.CountryService;
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

@RequiredArgsConstructor
@Tag(name = "Country Rest Controller", description = "CRUD операции со странами")
@Api(tags = "Country Rest Controller")
@RestController
@RequestMapping("/api/country")
public class CountryRestController {

    private final CheckEntityService checkEntityService;
    private final CountryService countryService;
    private final CountryRepository countryRepository;

    @ApiOperation(value = "getAll", notes = "Возвращает список всех стран")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех стран"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @GetMapping
    public ResponseEntity<List<CountryDto>> getAll() {
        List<CountryDto> countryDtos = countryService.getAll();
        return ResponseEntity.ok(countryDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Возвращает страну по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Страна найдена"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<CountryDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо найти страну",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        checkEntityService.checkExists((JpaRepository) countryRepository, id);
        return ResponseEntity.ok(countryService.getById(id));
    }

    @ApiOperation(value = "create", notes = "Создание новой страны")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Страна успешно создана"),
            @ApiResponse(code = 201, message = "Запрос принят и данные созданы"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PostMapping
    public ResponseEntity<CountryDto> create(@ApiParam(
            name = "countryDto",
            value = "DTO страны,которую необходимо создать")@RequestBody CountryDto countryDto) {
        return ResponseEntity.ok().body(countryService.create(countryDto));
    }

    @ApiOperation(value = "update", notes = "Обновление информации о стране")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о стране успешно обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные обновлены"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @PutMapping
    public ResponseEntity<CountryDto> update(@ApiParam(
            name = "countryDto",
            value = "DTO страны, который необходимо обновить")@RequestBody CountryDto countryDto) {
        return ResponseEntity.ok().body(countryService.update(countryDto));
    }

    @ApiOperation(value = "deleteById", notes = "Удаление страны по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Страна удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "ID переданный в URL по которому необходимо удалить страну") @PathVariable Long id) {
        countryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @ApiOperation(value = "search", notes = "Получение списка стран по заданным параметрам")
    public ResponseEntity<List<CountryDto>> getAll(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "type", params = "type", spec = LikeIgnoreCase.class),
                    @Spec(path = "shortName", params = "shortName", spec = LikeIgnoreCase.class),
                    @Spec(path = "fullName", params = "fullName", spec = LikeIgnoreCase.class),
                    @Spec(path = "digitCode", params = "digitCode", spec = LikeIgnoreCase.class),
                    @Spec(path = "twoLetterCode", params = "twoLetterCode", spec = LikeIgnoreCase.class),
                    @Spec(path = "threeLetterCode", params = "threeLetterCode", spec = LikeIgnoreCase.class),
            }) Specification<Country> spec) {
        return ResponseEntity.ok(countryService.search(spec));
    }

    @GetMapping("/searchByString")
    @ApiOperation(value = "search", notes = "Получение списка стран по заданным параметрам")
    public ResponseEntity<List<CountryDto>> searchByString(@RequestParam("search") String search) {
        return ResponseEntity.ok(countryService.searchByString(search));
    }
}
