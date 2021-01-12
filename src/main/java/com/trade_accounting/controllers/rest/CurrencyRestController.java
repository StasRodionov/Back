package com.trade_accounting.controllers.rest;


import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.CurrencyDto;
import com.trade_accounting.services.interfaces.CurrencyService;
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
@RequestMapping("/api/currency")
public class CurrencyRestController {

    private final CurrencyService currencyService;

    CurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping
    public ResponseEntity<List<CurrencyDto>> getAll() {
        log.info("Запрошен список CurrencyDto");
        return ResponseEntity.ok(currencyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDto> getById(@PathVariable(name = "id") Long id) {
        log.info("Запрошен экземпляр CurrencyDto с id= {}", id);
        return ResponseEntity.ok(currencyService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CurrencyDto currencyDto){
        currencyService.create(currencyDto);
        log.info("Записан новый экземпляр - {}", currencyDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CurrencyDto currencyDto) {
        currencyService.update(currencyDto);
        log.info("Обновлен экземпляр с id = {}", currencyDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        currencyService.deleteById(id);
        log.info("Удален экземпляр с id = {}", id);
        return ResponseEntity.ok().build();
    }


}
