package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.services.interfaces.CompanyService;
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
@RequestMapping("/api/company")
public class CompanyRestController {

    private final CompanyService companyService;

    public CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAll(){
        List<CompanyDto> companyDtos = companyService.getAll();
        log.info("Запрошен список");
        return ResponseEntity.ok(companyDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getById(@PathVariable("id") Long id){
        CompanyDto companyDto = companyService.getById(id);
        log.info("Запрошен экземпляр с id = {}", id);
        return ResponseEntity.ok(companyDto);
    }

    @GetMapping("/{email}")
    public ResponseEntity<CompanyDto> getByEmail(@PathVariable("email") String email){
        CompanyDto companyDto = companyService.getByEmail(email);
        log.info("Запрошен экземпляр с email = {}", email);
        return ResponseEntity.ok(companyDto);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CompanyDto companyDto){
        companyService.create(companyDto);
        log.info("Записан новый экземпляр - {}", companyDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CompanyDto companyDto) {
        companyService.update(companyDto);
        log.info("Обновлен экземпляр с id = {}", companyDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        companyService.deleteById(id);
        log.info("Удален экземпляр с id = {}", id);
        return ResponseEntity.ok().build();
    }
}
