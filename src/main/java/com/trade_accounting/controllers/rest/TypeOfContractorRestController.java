package com.trade_accounting.controllers.rest;


import com.trade_accounting.models.dto.TypeOfContractorDto;
import com.trade_accounting.services.interfaces.TypeOfContractorService;
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
@RequestMapping("/api/typeofcontractor")
public class TypeOfContractorRestController {
    private final TypeOfContractorService typeOfContractorService;

    public TypeOfContractorRestController(TypeOfContractorService typeOfContractorService) {
        this.typeOfContractorService = typeOfContractorService;
    }

    @GetMapping
    public ResponseEntity<List<TypeOfContractorDto>> getAll() {
        List<TypeOfContractorDto> typeOfContractorDtos = typeOfContractorService.getAll();
        log.info("Запрошен список TypeOfContractorDto");
        return ResponseEntity.ok(typeOfContractorDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeOfContractorDto> getById(@PathVariable(name = "id") Long id) {
        TypeOfContractorDto typeOfContractorDto = typeOfContractorService.getById(id);
        log.info("Запрошен экземпляр TypeOfContractorDto с id= {}", id);
        return ResponseEntity.ok(typeOfContractorDto);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TypeOfContractorDto typeOfContractorDto) {
        typeOfContractorService.create(typeOfContractorDto);
        log.info("Записан новый экземпляр TypeOfContractorDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TypeOfContractorDto typeOfContractorDto) {
        typeOfContractorService.update(typeOfContractorDto);
        log.info("Обновлен экземпляр TypeOfContractorDto");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        typeOfContractorService.deleteById(id);
        log.info("Удален экземпляр TypeOfContractorDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
