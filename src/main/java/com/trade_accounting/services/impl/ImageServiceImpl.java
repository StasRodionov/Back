package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.services.interfaces.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<ImageDto> getAll() {
        return imageRepository.getAll();
    }

    @Override
    public ImageDto getById(Long id) {
        return imageRepository.getById(id);
    }

    @Override
    public void create(ImageDto imageDto) {
        imageRepository.save(
                new Image(imageDto.getImageUrl(), imageDto.getSortNumber()));
    }

    @Override
    public void update(ImageDto imageDto) {
        imageRepository.save(
                new Image(imageDto.getId(), imageDto.getImageUrl(), imageDto.getSortNumber())
        );
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }


}
