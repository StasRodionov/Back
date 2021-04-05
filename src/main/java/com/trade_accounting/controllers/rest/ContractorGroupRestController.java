package com.trade_accounting.controllers.rest;


import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.ContractorGroupService;

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
@Tag(name = "Contractor Group Rest Controller", description = "CRUD  операции с группой контрагентов")
@Api(tags = "Contractor Group Rest Controller")
@RequestMapping("/api/contractor/group")
public class ContractorGroupRestController {
    private final ContractorGroupService contractorGroupService;
    private final CheckEntityService checkEntityService;

    public ContractorGroupRestController(ContractorGroupService contractorGroupService, CheckEntityService checkEntityService) {
        this.contractorGroupService = contractorGroupService;
        this.checkEntityService = checkEntityService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех групп")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка групп"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ContractorGroupDto>> getAll() {
        List<ContractorGroupDto> contractorGroupDtos = contractorGroupService.getAll();
        log.info("Запрошен список ContractorGroupDto");
        return ResponseEntity.ok(contractorGroupDtos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение группы контрагентов по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Группа найдена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractorGroupDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо найти группу")
                                                      @PathVariable(name = "id") Long id) {
        checkEntityService.checkExistsContractorGroupById(id);
        ContractorGroupDto contractorGroupDto = contractorGroupService.getById(id);
        log.info("Запрошен экземпляр ContractorGroupDto с id= {}", id);
        return  ResponseEntity.ok(contractorGroupDto);

    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение новой группы контрагентов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Группа создана"),
            @ApiResponse(code = 201, message = "Запрос принят и группа добавлена"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> create(@ApiParam(name = "ContractorGroupDto", value = "DTO группы, которую необходимо создать")
                                    @RequestBody ContractorGroupDto contractorGroupDto) {
        contractorGroupService.create(contractorGroupDto);
        log.info("Записан новый экземпляр ContractorGroupDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о группе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о группе обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о группе обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> update(@ApiParam(name = "ContractorGroupDto", value = "DTO группы, которую необходимо обновить")
                                    @RequestBody ContractorGroupDto contractorGroupDto) {
        contractorGroupService.update(contractorGroupDto);
        log.info("Обновлен экземпляр ContractorGroupDto");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление группы по ее id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Группа удалена"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить групу")
                                        @PathVariable(name = "id") Long id) {
        contractorGroupService.deleteById(id);
        log.info("Удален экземпляр ContractorGroupDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
