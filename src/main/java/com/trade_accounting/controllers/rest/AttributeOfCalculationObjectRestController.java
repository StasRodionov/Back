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
        return ResponseEntity.ok(attributeOfCalculationObjectService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeOfCalculationObjectDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(attributeOfCalculationObjectService.getById(id));
    }

    @PostMapping
    public void create(@RequestBody AttributeOfCalculationObjectDto attributeOfCalculationObjectDto){
        attributeOfCalculationObjectService.create(attributeOfCalculationObjectDto);
    }

    @PutMapping
    public void update(@RequestBody AttributeOfCalculationObjectDto attributeOfCalculationObjectDto){
        attributeOfCalculationObjectService.update(attributeOfCalculationObjectDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        attributeOfCalculationObjectService.deleteById(id);
    }

}
