package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.services.interfaces.ContractorService;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@Tag(name = "Contractor Rest Controller", description = "CRUD  операции с контрагентами")
@Api(tags = "Contractor Rest Controller")
@RequestMapping("/api/contractor")
public class ContractorRestController {

    private final ContractorService contractorService;

    public ContractorRestController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех контрагентов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка контрагентов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ContractorDto>> getAll() {
        List<ContractorDto> contractorDtoList = contractorService.getAll();
        log.info("Запрошен список ContractorDto через getAll");
        return ResponseEntity.ok(contractorDtoList);
    }

    @GetMapping("/lite")
    @ApiOperation(value = "getAllLite", notes = "Получение лёгкого списка всех контрагентов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение лёгкого списка контрагентов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ContractorDto>> getAllLite() {
        List<ContractorDto> contractorDtoList = contractorService.getAll();
        log.info("Запрошен список ContractorDto (Лёгкое ДТО)");
        return ResponseEntity.ok(contractorDtoList);
    }

    @GetMapping("/search/{searchTerm}")
    @ApiOperation(value = "searchTerm", notes = "Получение списка некоторых контрагентов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение отф. списка контрагентов"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ContractorDto>> getAll(@ApiParam(name = "searchTerm",
            value = "Переданный в URL searchTerm, по которому необходимо найти контрагента")
                                                          @PathVariable(name = "searchTerm") String searchTerm) {
        List<ContractorDto> contractorDtoList = contractorService.getAll(searchTerm);
        log.info("Запрошен список ContractorDto searchTerm");
        return ResponseEntity.ok(contractorDtoList);
    }

    @GetMapping("/searchContractor")
    @ApiOperation(value = "searchContractor", notes = "Получение списка контрактов по заданным параметрам")
    public ResponseEntity<List<ContractorDto>> getAllFilter(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "sortNumber", params = "sortNumber", spec = LikeIgnoreCase.class),
                    @Spec(path = "phone", params = "phone", spec = LikeIgnoreCase.class),
                    @Spec(path = "fax", params = "fax", spec = LikeIgnoreCase.class),
                    @Spec(path = "email", params = "email", spec = LikeIgnoreCase.class),
                    @Spec(path = "address", params = "address", spec = LikeIgnoreCase.class),
                    @Spec(path = "commentToAddress", params = "commentToAddress", spec = LikeIgnoreCase.class),
                    @Spec(path = "comment", params = "comment", spec = LikeIgnoreCase.class),
//                    @Spec(path = "contractor.contractorGroup", params = "contractorGroupDto", spec = Like.class),
//                    @Spec(path = "contractor.typeOfContractor", params = "typeOfContractorDto", spec = LikeIgnoreCase.class),
//                    @Spec(path = "contractor.typeOfPrice", params = "typeOfPriceDto", spec = LikeIgnoreCase.class),
//                    @Spec(path = "contractor.bankAccounts", params = "bankAccountsDto", spec = LikeIgnoreCase.class),
//                    @Spec(path = "contractor.legalDetail", params = "legalDetail", spec = Equal.class),
            }) Specification<Contractor> spec) {
        log.info("Запрошен фильтр по поиску контрактов contractor");
        return ResponseEntity.ok(contractorService.searchContractor(spec));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение контрагента по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Контрагент найден"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractorDto> getById(@ApiParam(name = "id",
            value = "Переданный в URL id по которому необходимо найти контрагента")
                                                 @PathVariable(name = "id") Long id) {
        ContractorDto contractorDto = contractorService.getById(id);
        log.info("Запрошен экземпляр ContractorDto с id= {}", id);
        return ResponseEntity.ok(contractorDto);
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Внесение нового контрагента")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Контрагент создан"),
            @ApiResponse(code = 201, message = "Запрос принят и контрагент добавлен"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractorDto> create(@ApiParam(name = "contractorDto",
            value = "DTO контрагента, которого необходимо создать")
                                                @RequestBody ContractorDto contractorDto) {
        contractorService.create(contractorDto);
        log.info("Записан новый экземпляр {}", contractorDto.toString());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации о контрагенте")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация о контрагенте обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные о контрагенте обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractorDto> update(@ApiParam(name = "contractorDto",
            value = "DTO контрагента, которого необходимо обновить")
                                                @RequestBody ContractorDto contractorDto) {
        contractorService.update(contractorDto);
        log.info("Обновлен экземпляр ContractorDto с id= {}", contractorDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление контрагента по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Контрагент удален"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<ContractorDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id по которому необходимо удалить контрагента")
                                                    @PathVariable("id") Long id) {
        contractorService.deleteById(id);
        log.info("Удален экземпляр ContractorDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
