package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.services.interfaces.DepartmentService;
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
@RequestMapping("/api/department")
public class DepartmentRestController {

    private final DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAll(){
        List<DepartmentDto> departmentDtos = departmentService.getAll();
        log.info("Запрошен список DepartmentDto");
        return ResponseEntity.ok(departmentDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getById(@PathVariable(name = "id") Long id){
        DepartmentDto departmentDto = departmentService.getById(id);
        log.info("Запрошен экземпляр DepartmentDto с id = {}", id);
        return ResponseEntity.ok(departmentDto);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DepartmentDto departmentDto){
        departmentService.create(departmentDto);
        log.info("Записан новый экземпляр DepartmentDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DepartmentDto departmentDto) {
        departmentService.update(departmentDto);
        log.info("Обновлен экземпляр DepartmentDto");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        departmentService.deleteById(id);
        log.info("Удален экземпляр DepartmentDto с id= {}", id);
        return ResponseEntity.ok().build();
    }

}
