package com.trade_accounting.controllers.rest;


import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.services.interfaces.WarehouseService;
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
@RequestMapping("/api/warehouse")
public class WarehouseRestController {

    private final WarehouseService warehouseService;

    public WarehouseRestController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("")
    public ResponseEntity<List<WarehouseDto>> getAll() {
        List<WarehouseDto> warehouseDtoList = warehouseService.getAll();
        return warehouseDtoList != null
                ? new ResponseEntity<>(warehouseDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDto> getById(@PathVariable("id") Long id) {
        WarehouseDto warehouseDto = warehouseService.getById(id);
        return warehouseDto != null
                ? new ResponseEntity<>(warehouseDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> create(@RequestBody WarehouseDto warehouseDto) {
        warehouseService.create(warehouseDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<HttpStatus> update(@RequestBody WarehouseDto warehouseDto) {
        warehouseService.update(warehouseDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        warehouseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
