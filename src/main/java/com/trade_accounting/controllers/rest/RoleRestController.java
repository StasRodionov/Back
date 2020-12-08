package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.services.interfaces.RoleService;
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
@RequestMapping("/api/role")
public class RoleRestController {

    private final RoleService roleService;

    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAll() {
        List<RoleDto> roleDtos = roleService.getAll();
        log.info("Запрошен список RoleDto");
        return ResponseEntity.ok(roleDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getById(@PathVariable("id") Long id) {
        RoleDto roleDto = roleService.getById(id);
        log.info("Запрошен экземпляр RoleDto с id = {}", id);
        return ResponseEntity.ok(roleDto);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoleDto roleDto) {
        roleService.create(roleDto);
        log.info("Записан новый экземпляр - {}", roleDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody RoleDto roleDto) {
        roleService.update(roleDto);
        log.info("Обновлен экземпляр RoleDto с id = {}", roleDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        roleService.deleteById(id);
        log.info("Удален экземпляр RoleDto с id = {}", id);
        return ResponseEntity.ok().build();
    }
}
