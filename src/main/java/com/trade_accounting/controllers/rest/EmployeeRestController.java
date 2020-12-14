package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.services.interfaces.EmployeeService;
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
@RequestMapping("/api/employee")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll(){
        List<EmployeeDto> employeeDtos = employeeService.getAll();
        log.info("Запрошен список EmployeeDto");
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable("id") Long id){
        EmployeeDto employeeDto = employeeService.getById(id);
        log.info("Запрошен экземпляр EmployeeDto с id = {}", id);
        return ResponseEntity.ok(employeeDto);
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeeDto employeeDto){
        employeeService.create(employeeDto);
        log.info("Записан новый экземпляр EmployeeDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody EmployeeDto employeeDto) {
        employeeService.update(employeeDto);
        log.info("Обновлен экземпляр EmployeeDto с id = {}", employeeDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        log.info("Удален экземпляр EmployeeDto с id = {}", id);
        return ResponseEntity.ok().build();
    }
}
