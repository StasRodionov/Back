package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.services.interfaces.BankAccountService;
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
@RequestMapping("/api/bank/account")
public class BankAccountRestController {

    private final BankAccountService bankAccountService;

    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<List<BankAccountDto>> getAll() {
        List<BankAccountDto> accounts = bankAccountService.getAll();
        log.info("Запрошен список BankAccountDto");
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDto> getById(@PathVariable(name = "id") Long id) {
        BankAccountDto bankAccount = bankAccountService.getById(id);
        log.info("Запрошен экземпляр BankAccountDto с id= {}", id);
        return ResponseEntity.ok(bankAccount);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BankAccountDto bankAccountDto) {
        bankAccountService.create(bankAccountDto);
        log.info("Записан новый экземпляр BankAccountDto");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody BankAccountDto bankAccountDto) {
        bankAccountService.update(bankAccountDto);
        log.info("Обновлен экземпляр BankAccountDto");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        bankAccountService.deleteById(id);
        log.info("Удален экземпляр BankAccountDto с id= {}", id);
        return ResponseEntity.ok().build();
    }

}
