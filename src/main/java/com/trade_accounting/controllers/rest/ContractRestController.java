package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.ContractDto;
import com.trade_accounting.services.interfaces.ContractService;
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
@RequestMapping("/api/contract")
public class ContractRestController {

    private final ContractService contractService;

    public ContractRestController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    ResponseEntity<List<ContractDto>> getAll() {
        List<ContractDto> contracts = contractService.getAll();
        log.info("Запрошен список ContractDto");
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/{id}")
    ResponseEntity<ContractDto> getById(@PathVariable Long id) {
        ContractDto contractDto = contractService.getById(id);
        log.info("Запрошен ContractorDto с id= {}", id);
        return ResponseEntity.ok(contractDto);
    }

    @PostMapping
    ResponseEntity<ContractDto> create(@RequestBody ContractDto contractDto) {
        contractService.create(contractDto);
        log.info("Записан новый экземпляр - {}", contractDto.toString());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    ResponseEntity<ContractDto> update(@RequestBody ContractDto contractDto) {
        contractService.update(contractDto);
        log.info("Обновлен экземпляр ContractDto с id= {}", contractDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ContractDto> deleteById(@PathVariable Long id) {
        contractService.deleteById(id);
        log.info("Удален экземпляр ContractDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
