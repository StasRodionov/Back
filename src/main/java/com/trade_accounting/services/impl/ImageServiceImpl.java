package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.services.interfaces.ImageService;
import com.trade_accounting.utils.DtoMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final DtoMapper dtoMapper;

    public ImageServiceImpl(ImageRepository imageRepository, DtoMapper dtoMapper) {
        this.imageRepository = imageRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ImageDto> getAll() {
        return imageRepository.getAll()
                .stream()
                .map(dtoMapper::imageToImageDto)
                .collect(Collectors.toList());
    }

    @Override
    public ImageDto getById(Long id) {
        return dtoMapper.imageToImageDto(imageRepository.getOne(id));
    }

    @Override
    public ImageDto create(ImageDto dto) {
        Image image = dtoMapper.imageDtoToImage(dto, "picture");
        imageRepository.save(image);
        dto.setId(image.getId());
        return dto;
    }


    @Override
    public ImageDto update(ImageDto dto) {
        Image image = dtoMapper.imageDtoToImage(dto, "picture");
        imageRepository.save(image);
        return dto;
    }

    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        Optional<Image> optional = imageRepository.findById(id);
        if (optional.isPresent() && imageRepository.countProductImage(id) == 0) {
            imageRepository.deleteById(id);
            Files.deleteIfExists(Paths.get(optional.get().getImageUrl()));
        }
    }
}
