package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.ContractorDto;
import com.trade_accounting.services.interfaces.ContractorService;
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
@RequestMapping("/api/contractor")
public class ContractorRestController {

    private final ContractorService contractorService;

    public ContractorRestController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @GetMapping
    public ResponseEntity<List<ContractorDto>> getAll() {
        List<ContractorDto> contractorDtoList = contractorService.getAll();
        log.info("Запрошен список ContractorDto");
        return ResponseEntity.ok(contractorDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractorDto> getById(@PathVariable("id") Long id) {
        ContractorDto contractorDto = contractorService.getById(id);
        log.info("Запрошен экземпляр ContractorDto с id= {}", id);
        return ResponseEntity.ok(contractorDto);
    }

    @PostMapping
    public ResponseEntity<ContractorDto> create(@RequestBody ContractorDto contractorDto) {
        contractorService.create(contractorDto);
        log.info("Записан новый экземпляр {}", contractorDto.toString());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ContractorDto> update(@RequestBody ContractorDto contractorDto) {
        contractorService.update(contractorDto);
        log.info("Обновлен экземпляр ContractorDto с id= {}", contractorDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContractorDto> deleteById(@PathVariable("id") Long id) {
        contractorService.deleteById(id);
        log.info("Удален экземпляр ContractorDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
