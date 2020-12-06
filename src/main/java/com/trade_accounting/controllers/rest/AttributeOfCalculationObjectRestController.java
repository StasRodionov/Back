package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.AttributeOfCalculationObjectDto;
import com.trade_accounting.services.interfaces.AttributeOfCalculationObjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/attribute/calculation/object")
public class AttributeOfCalculationObjectRestController {

    private final AttributeOfCalculationObjectService attributeOfCalculationObjectService;

    @Autowired
    public AttributeOfCalculationObjectRestController(AttributeOfCalculationObjectService attributeOfCalculationObjectService) {
        this.attributeOfCalculationObjectService = attributeOfCalculationObjectService;
    }

    @GetMapping
    public ResponseEntity<List<AttributeOfCalculationObjectDto>> getAll(){
        List<AttributeOfCalculationObjectDto> attributeOfCalculationObjectDtos = attributeOfCalculationObjectService.getAll();
        log.info("Запрошен список AttributeOfCalculationObjectDto");
        return ResponseEntity.ok(attributeOfCalculationObjectDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeOfCalculationObjectDto> getById(@PathVariable("id") Long id) {
        AttributeOfCalculationObjectDto attributeOfCalculationObjectDto = attributeOfCalculationObjectService.getById(id);
        log.info("Запрошен экземпляр AttributeOfCalculationObjectDto с id = {}", id);
        return ResponseEntity.ok(attributeOfCalculationObjectDto);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AttributeOfCalculationObjectDto attributeOfCalculationObjectDto){
        attributeOfCalculationObjectService.create(attributeOfCalculationObjectDto);
        log.info("Записан новый экземпляр AttributeOfCalculationObjectDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AttributeOfCalculationObjectDto attributeOfCalculationObjectDto){
        attributeOfCalculationObjectService.update(attributeOfCalculationObjectDto);
        log.info("Обновлен экземплярAttributeOfCalculationObjectDto");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        attributeOfCalculationObjectService.deleteById(id);
        log.info("Удален экземпляр с id = {}", id);
        return ResponseEntity.ok().build();
    }

}
