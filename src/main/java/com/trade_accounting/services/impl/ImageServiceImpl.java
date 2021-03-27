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
        return imageRepository.getAll();
    }

    @Override
    public ImageDto getById(Long id) {
        return dtoMapper.imageToImageDto(imageRepository.getOne(id));
    }

    @Override
    public Image create(ImageDto imageDto, String imageDir) {
        return imageRepository.saveAndFlush(dtoMapper.imageDtoToImage(imageDto, imageDir));
    }


    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        Optional<Image> optional = imageRepository.findById(id);
        if (optional.isPresent()) {
            Files.deleteIfExists(Paths.get(optional.get().getImageUrl()));
            imageRepository.deleteById(id);
        }
    }

}
