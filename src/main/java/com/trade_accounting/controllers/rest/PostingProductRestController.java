package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.PostingProductDto;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.PostingProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Posting Product Rest Controller", description = "CRUD  операции с оприходованием")
@Api(tags = "Posting Product Rest Controller")
@RequestMapping("/api/posting_product")
public class PostingProductRestController {

    private final PostingProductService postingProductService;
    private final CheckEntityService checkEntityService;

    public PostingProductRestController(PostingProductService postingProductService, CheckEntityService checkEntityService) {
        this.postingProductService = postingProductService;
        this.checkEntityService = checkEntityService;
    }

    @GetMapping
    @ApiOperation(value = "getAll", notes = "Получение списка всех оприходований")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка оприходований"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<PostingProductDto>> getAll() {
        return ResponseEntity.ok(postingProductService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getById", notes = "Получение списка всех оприходований")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка оприходований"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PostingProductDto> getById(@ApiParam(name = "id", type = "Long",
            value = "Переданный в URL id, по которому необходимо найти оприходование")
                                                     @PathVariable(name = "id") Long id) {
        checkEntityService.checkExistsPostingProductById(id);

        return ResponseEntity.ok(postingProductService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "Добавление нового оприходования")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Оприходование создано"),
            @ApiResponse(code = 201, message = "Запрос принят и оприходование добавлено"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PostingProductDto> create(@ApiParam(name = "postingProductDto",
            value = "DTO оприходования, которое необходимо создать")
                                                    @RequestBody PostingProductDto postingProductDto) {
        return ResponseEntity.ok(postingProductService.create(postingProductDto));
    }

    @PutMapping
    @ApiOperation(value = "update", notes = "Изменение информации об оприходовании")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Информация об оприходовании обновлена"),
            @ApiResponse(code = 201, message = "Запрос принят и данные об оприходовании обновлены"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PostingProductDto> update(@ApiParam(name = "PostingProductDto",
            value = "DTO оприходования, которую необходимо обновить")
                                                    @RequestBody PostingProductDto postingProductDto) {
        return ResponseEntity.ok(postingProductService.update(postingProductDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteById", notes = "Удаление оприходования по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Оприходование удалено"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<PostingProductDto> deleteById(@ApiParam(name = "id", type = "Long",
            value = "Переданный id, по которому необходимо удалить оприходование")
                                                        @PathVariable("id") Long id) {
        checkEntityService.checkExistsPostingProductById(id);
        postingProductService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}

