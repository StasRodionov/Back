package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.dto.ImageDto;
import lombok.SneakyThrows;

import java.util.List;

public interface ImageService {

    List<ImageDto> getAll();

    ImageDto getById(Long id);

    Image create(ImageDto imageDto, String imageDir);

    void deleteById(Long id);

}
