package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.dto.ImageDto;
import lombok.SneakyThrows;

import java.util.List;

public interface ImageService {

    List<ImageDto> getAll();

    ImageDto getById(Long id);

    Image create(ImageDto imageDto);

    void deleteById(Long id);

    @SneakyThrows
    String uploadImage(byte[] content, String imageDir, String fileName);

    @SneakyThrows
    byte[] downloadImage(String path);
}
