package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.LegalDetailDto;
import com.trade_accounting.services.interfaces.LegalDetailService;
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
@RequestMapping("/api/legaldetail")
public class LegalDetailRestController {

    private final LegalDetailService legalDetailService;

    public LegalDetailRestController(LegalDetailService legalDetailService) {
        this.legalDetailService = legalDetailService;
    }

    @GetMapping
    ResponseEntity<List<LegalDetailDto>> getAll() {
        List<LegalDetailDto> legalDetailDtoList = legalDetailService.getAll();
        log.info("Запрошен список LegalDetailDto");
        return ResponseEntity.ok(legalDetailDtoList);
    }

    @GetMapping("/{id}")
    ResponseEntity<LegalDetailDto> getById(@PathVariable("id") Long id) {
        LegalDetailDto legalDetailDto = legalDetailService.getById(id);
        log.info("Запрошен экземпляр LegalDetailDto с id= {}", id);
        return ResponseEntity.ok(legalDetailDto);
    }

    @PostMapping
    ResponseEntity<LegalDetailDto> create(@RequestBody LegalDetailDto legalDetailDto) {
        legalDetailService.create(legalDetailDto);
        log.info("Записан новый экземпляр LegalDetailDto - {}", legalDetailDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    ResponseEntity<LegalDetailService> update(@RequestBody LegalDetailDto legalDetailDto) {
        legalDetailService.update(legalDetailDto);
        log.info("Обновлен экземпляр LegalDetailDto - {}", legalDetailDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<LegalDetailDto> deleteById(@PathVariable("id") Long id) {
        legalDetailService.deleteById(id);
        log.info("Удален экземпляр LegalDetailDto с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
