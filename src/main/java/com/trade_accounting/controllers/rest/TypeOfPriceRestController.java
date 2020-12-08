package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/typeofprice")
public class TypeOfPriceRestController {

    private final TypeOfPriceService typeOfPriceService;

    public TypeOfPriceRestController(TypeOfPriceService typeOfPriceService) {
        this.typeOfPriceService = typeOfPriceService;
    }

    @GetMapping
    public ResponseEntity<List<TypeOfPriceDto>> getAll() {
        List<TypeOfPriceDto> types = typeOfPriceService.getAll();
        log.info("Запрошен список TypeOfPriceDto");
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeOfPriceDto> getById(@PathVariable(name = "id") Long id) {
        TypeOfPriceDto type = typeOfPriceService.getById(id);
        log.info("Запрошен экземпляр TypeOfPriceDto с id= {}", id);
        return ResponseEntity.ok(type);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TypeOfPriceDto typeOfPriceDto) {
        typeOfPriceService.create(typeOfPriceDto);
        log.info("Записан новый экземпляр TypeOfPriceDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TypeOfPriceDto typeOfPriceDto) {
        typeOfPriceService.update(typeOfPriceDto);
        log.info("Обновлен экземпляр TypeOfPriceDto");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        typeOfPriceService.deleteById(id);
        log.info("Удален экземпляр TypeOfPriceDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
