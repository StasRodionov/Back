package com.trade_accounting.controllers.rest;


import com.trade_accounting.models.dto.ContractorGroupDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.services.interfaces.ContractorGroupService;
import com.trade_accounting.services.interfaces.PositionService;
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
@RequestMapping("/api/contractor/group")
public class ContractorGroupRestController {
    private final ContractorGroupService contractorGroupService;

    public ContractorGroupRestController(ContractorGroupService contractorGroupService) {
        this.contractorGroupService = contractorGroupService;
    }

    @GetMapping
    public ResponseEntity<List<ContractorGroupDto>> getAll() {
        List<ContractorGroupDto> contractorGroupDtos = contractorGroupService.getAll();
        log.info("Запрошен список ContractorGroupDto");
        return ResponseEntity.ok(contractorGroupDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractorGroupDto> getById(@PathVariable(name = "id") Long id) {
        ContractorGroupDto contractorGroupDto = contractorGroupService.getById(id);
        log.info("Запрошен экземпляр ContractorGroupDto с id= {}", id);
        return ResponseEntity.ok(contractorGroupDto);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ContractorGroupDto contractorGroupDto) {
        contractorGroupService.create(contractorGroupDto);
        log.info("Записан новый экземпляр ContractorGroupDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ContractorGroupDto contractorGroupDto) {
        contractorGroupService.update(contractorGroupDto);
        log.info("Обновлен экземпляр ContractorGroupDto");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        contractorGroupService.deleteById(id);
        log.info("Удален экземпляр ContractorGroupDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
