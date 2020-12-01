package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.TaxSystemDto;
import com.trade_accounting.services.interfaces.TaxSystemService;
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
@RequestMapping("/api/taxsystem")
public class TaxSystemRestController {

    private final TaxSystemService taxSystemService;

    public TaxSystemRestController(TaxSystemService taxSystemService) {
        this.taxSystemService = taxSystemService;
    }

    @GetMapping
    public ResponseEntity<List<TaxSystemDto>> getAll() {
        List<TaxSystemDto> taxSystems = taxSystemService.getAll();
        log.info("Запрошен список TaxSystemDto");
        return ResponseEntity.ok(taxSystems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxSystemDto> getById(@PathVariable(name = "id") Long id) {
        TaxSystemDto taxSystem = taxSystemService.getById(id);
        log.info("Запрошен экземпляр TaxSystemDto с id= {}", id);
        return ResponseEntity.ok(taxSystem);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaxSystemDto taxSystemDto) {
        taxSystemService.create(taxSystemDto);
        log.info("Записан новый экземпляр TaxSystemDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TaxSystemDto taxSystemDto) {
        taxSystemService.update(taxSystemDto);
        log.info("Обновлен экземпляр TaxSystemDto");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        taxSystemService.deleteById(id);
        log.info("Удален экземпляр TaxSystemDto с id= {}", id);
        return ResponseEntity.ok().build();
    }

}
