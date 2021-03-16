package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.ImageDto;
import lombok.SneakyThrows;

import java.util.List;

public interface ImageService {

    List<ImageDto> getAll();

    ImageDto getById(Long id);

    void create(ImageDto imageDto);

    void update(ImageDto imageDto);

    void deleteById(Long id);

    @SneakyThrows
    String uploadImage(byte[] content, String name);

    @SneakyThrows
    byte[] downloadImage(String path);
}
