package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController для ProductDto с реализацией ProductService
 *
 * @author Sanych
 * @see ProductService
 */
@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> productGroups = productService.getAll();
        log.info("Запрошен список ProductDto");
        return ResponseEntity.ok(productGroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable(name = "id") Long id) {
        ProductDto productGroup = productService.getById(id);
        log.info("Запрошен экземпляр ProductDto с id= {}", id);
        return ResponseEntity.ok(productGroup);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDto dto) {
        productService.create(dto);
        log.info("Записан новый экземпляр Product с id= {}, name= {}", dto.getId(), dto.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductDto productDto) {
        productService.update(productDto);
        log.info("Обновлен экземпляр Product с id= {}, name= {}", productDto.getId(), productDto.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        log.info("Удален экземпляр Product с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
