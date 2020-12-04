package com.trade_accounting.controllers.rest;

import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.services.interfaces.ImageService;
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
@RequestMapping("/api/image")
public class ImageRestController {

    private final ImageService imageService;

    public ImageRestController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> getAll() {
        List<ImageDto> images = imageService.getAll();
        log.info("Запрошен список Image");
        return ResponseEntity.ok(images);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDto> getById(@PathVariable(name = "id") Long id) {
        ImageDto image = imageService.getById(id);
        log.info("Запрошен экземпляр Image с id= {}", id);
        return ResponseEntity.ok(image);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ImageDto imageDto) {
        imageService.create(imageDto);
        log.info("Записан новый экземпляр Image c id= {}", imageDto.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ImageDto imageDto) {
        imageService.update(imageDto);
        log.info("Обновлен экземпляр Image с id= {}", imageDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        imageService.deleteById(id);
        log.info("Удален экземпляр Image с id= {}", id);
        return ResponseEntity.ok().build();
    }
}
