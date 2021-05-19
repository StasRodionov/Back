package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.services.interfaces.LegalDetailService;
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

@Slf4j
@RestController
@Tag(name = "Legal Detail Rest Controller", description = "CRUD  операции с юридическими реквизитами")
@Api(tags = "Legal Detail Rest Controller")
@RequestMapping("/api/legaldetail")
public class LegalDetailRestController {

    private final LegalDetailService legalDetailService;

    public LegalDetailRestController(LegalDetailService legalDetailService) {
        this.legalDetailService = legalDetailService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всей юридических реквизитов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех юридических реквизитов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<LegalDetailDto>> getAll() {
        List<LegalDetailDto> legalDetailDtoList = legalDetailService.getAll();
        log.info("Запрошен список LegalDetailDto");
        return ResponseEntity.ok(legalDetailDtoList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение юридических реквизитов по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Юридические реквизиты не найдены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )

    public ResponseEntity<LegalDetailDto> getById(@ApiParam(name = "id", value = "ID переданный в URL по которому необходимо найти юридические реквизиты")
                                                  @PathVariable("id") Long id) {
        LegalDetailDto legalDetailDto = legalDetailService.getById(id);
        log.info("Запрошен экземпляр LegalDetailDto с id= {}", id);
        return ResponseEntity.ok(legalDetailDto);
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение новых юридических реквизитов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Запись юридических реквизитов создана"),
            @ApiResponse(code = 201, message = "Запрос принят и юридические реквизиты добавлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<LegalDetailDto> create(@ApiParam(name = "legalDetailDto", value = "DTO юридические реквизиты, которые необходимо создать")
                                                 @RequestBody LegalDetailDto legalDetailDto) {
       LegalDetailDto legalDetailDtoCreate = legalDetailService.create(legalDetailDto);
        log.info("Записан новый экземпляр LegalDetailDto - {}", legalDetailDto);
        return ResponseEntity.ok().body(legalDetailDtoCreate);
        //return ResponseEntity.ok().build();
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение юридических реквизитов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Юридические реквизиты обновлены"),
            @ApiResponse(code = 201, message = "Запрос принят и юридические реквизиты обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<LegalDetailService> update(@ApiParam(name = "legalDetailDto", value = "DTO юридические реквизиты с обновленными данными")
                                                     @RequestBody LegalDetailDto legalDetailDto) {
     LegalDetailDto legalDetailDtoUpdated = legalDetailService.update(legalDetailDto);
        log.info("Обновлен экземпляр LegalDetailDto - {}", legalDetailDto);
     //   return ResponseEntity.ok(legalDetailDtoUpdated);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление юридических реквизитов по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Юридические реквизиты удалены"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<LegalDetailDto> deleteById(@ApiParam(name = "id", value = "ID юридических реквизитов, которые необходимо удалить")
                                                     @PathVariable("id") Long id) {
        legalDetailService.deleteById(id);
        log.info("Удален экземпляр LegalDetailDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
