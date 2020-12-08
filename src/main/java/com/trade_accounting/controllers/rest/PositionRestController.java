package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.services.interfaces.PositionService;
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
@RequestMapping("/api/position")
public class PositionRestController {

    private final PositionService positionService;

    public PositionRestController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public ResponseEntity<List<PositionDto>> getAll() {
        List<PositionDto> positions = positionService.getAll();
        log.info("Запрошен список PositionDto");
        return ResponseEntity.ok(positions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDto> getById(@PathVariable(name = "id") Long id) {
        PositionDto positions = positionService.getById(id);
        log.info("Запрошен экземпляр PositionDto с id= {}", id);
        return ResponseEntity.ok(positions);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PositionDto positionDto) {
        positionService.create(positionDto);
        log.info("Записан новый экземпляр PositionDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PositionDto positionDto) {
        positionService.update(positionDto);
        log.info("Обновлен экземпляр PositionDto");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        positionService.deleteById(id);
        log.info("Удален экземпляр PositionDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
