package com.trade_accounting.controllers.rest;


import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.services.interfaces.WarehouseService;
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
@RequestMapping("/api/warehouse")
public class WarehouseRestController {

    private final WarehouseService warehouseService;

    public WarehouseRestController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<WarehouseDto>> getAll() {
        log.info("returned all objects");
        return ResponseEntity.ok(warehouseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDto> getById(@PathVariable("id") Long id) {
        log.info("returned object byId - {}", id);
        return ResponseEntity.ok(warehouseService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody WarehouseDto warehouseDto) {
        warehouseService.create(warehouseDto);
        log.info("created object: " + warehouseDto.toString());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody WarehouseDto warehouseDto) {
        warehouseService.update(warehouseDto);
        log.info("updated object: " + warehouseDto.toString());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        warehouseService.deleteById(id);
        log.info("deleted object by id - {}", id);
        return ResponseEntity.ok().build();
    }
}
