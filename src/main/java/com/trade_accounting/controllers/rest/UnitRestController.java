package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.UnitDto;
import com.trade_accounting.services.interfaces.UnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/unit")
public class UnitRestController {

    private final UnitService unitService;

    public UnitRestController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public ResponseEntity<List<UnitDto>> getAll() {
        List<UnitDto> units = unitService.getAll();
        log.info("Запрошен список UnitDto");
        return ResponseEntity.ok(units);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitDto> getById(@PathVariable Long id) {
        UnitDto unit = unitService.getById(id);
        log.info("Запрошен экземпляр UnitDto с id= {}", id);
        return ResponseEntity.ok(unit);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UnitDto unitDto) {
        unitService.create(unitDto);
        log.info("Записан новый экземпляр {}", unitDto.toString());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UnitDto unitDto) {
        unitService.update(unitDto);
        log.info("Обновлен экземпляр UnitDto с id= {}", unitDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        unitService.deleteById(id);
        log.info("Удален экземпляр UnitDto с id= {}", id);
        return ResponseEntity.ok().build();
    }

}
