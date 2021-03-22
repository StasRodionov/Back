package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.services.interfaces.ImageService;
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
@Tag(name = "Image Rest Controller", description = "CRUD операции с фото")
@Api(tags = "Image Rest Controller")
@RequestMapping("/api/image")
public class ImageRestController {

    private final ImageService imageService;

    public ImageRestController(ImageService imageService) {
        this.imageService = imageService;
    }

    @ApiOperation(value = "getAll", notes = "Возвращает список всех фото")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка всех фото"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<List<ImageDto>> getAll() {
        List<ImageDto> images = imageService.getAll();
        log.info("Запрошен список Image");
        return ResponseEntity.ok(images);
    }

    @ApiOperation(value = "getById", notes = "Возвращает определенное фото по Id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Фото найдено"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )

    public ResponseEntity<ImageDto> getById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо найти фото",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {

        ImageDto imageDto = imageService.getById(id);

        log.info("Запрошен экземпляр Image с id= {}", id);
        return ResponseEntity.ok(imageDto);
    }



    @ApiOperation(value = "deleteById", notes = "Удаляет фото на основе переданного ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Фото успешно удалено"),
            @ApiResponse(code = 204, message = "Запрос получен и обработан, данных для возврата нет"),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")}
    )
    public ResponseEntity<?> deleteById(@ApiParam(
            name = "id",
            type = "Long",
            value = "Переданный ID  в URL по которому необходимо удалить фото",
            example = "1",
            required = true) @PathVariable(name = "id") Long id) {
        imageService.deleteById(id);
        log.info("Удален экземпляр Image с id= {}", id);
        return ResponseEntity.ok().build();
    }
}