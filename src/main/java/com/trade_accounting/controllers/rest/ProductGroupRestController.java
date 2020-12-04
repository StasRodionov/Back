package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.services.interfaces.ProductGroupService;
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
@RequestMapping("/api/productgroup")
public class ProductGroupRestController {

    private final ProductGroupService productGroupService;

    public ProductGroupRestController(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    @GetMapping
    public ResponseEntity<List<ProductGroupDto>> getAll() {
        List<ProductGroupDto> productGroups = productGroupService.getAll();
        log.info("Запрошен список ProductGroupDto");
        return ResponseEntity.ok(productGroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGroupDto> getById(@PathVariable(name = "id") Long id) {
        ProductGroupDto productGroup = productGroupService.getById(id);
        log.info("Запрошен экземпляр ProductGroupDto с id= {}", id);
        return ResponseEntity.ok(productGroup);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductGroupDto productGroupDto) {
        productGroupService.create(productGroupDto);
        log.info("Записан новый экземпляр ProductGroup с id= {}, name= {}", productGroupDto.getId(), productGroupDto.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductGroupDto productGroupDto) {
        productGroupService.update(productGroupDto);
        log.info("Обновлен экземпляр ProductGroup с id= {}, name= {}", productGroupDto.getId(), productGroupDto.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        productGroupService.deleteById(id);
        log.info("Удален экземпляр ProductGroup с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
