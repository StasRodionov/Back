package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.services.interfaces.TypeOfPriceService;
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

@RestController
@RequestMapping("/api/typeofprice")
public class TypeOfPriceRestController {

    private final TypeOfPriceService service;

    public TypeOfPriceRestController(TypeOfPriceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TypeOfPriceDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TypeOfPriceDto> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TypeOfPriceDto typeOfPriceDto) {
        service.create(typeOfPriceDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TypeOfPriceDto typeOfPriceDto) {
        service.update(typeOfPriceDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
