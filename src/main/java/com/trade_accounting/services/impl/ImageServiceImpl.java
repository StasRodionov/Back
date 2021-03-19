package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.services.interfaces.ImageService;
import com.trade_accounting.utils.ModelDtoConverter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    private static final String UPLOAD_DIR = "images\\";
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
        Image image = imageRepository.getOne(id);
        ImageDto imageDto = ModelDtoConverter.convertToImageDto(image);
        imageDto.setContent(downloadImage(image.getImageUrl()));
        return imageDto;
    }

    @Override
    public Image create(ImageDto imageDto) {
        String url = uploadImage(imageDto.getContent(),
                "product_images\\",
                String.valueOf(new Date().getTime()));
        Image image = ModelDtoConverter.convertToImage(imageDto);
        image.setImageUrl(url);
        return imageRepository.saveAndFlush(image);
    }


    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }


    @SneakyThrows
    @Override
    public String uploadImage(byte[] content, String imageDir, String fileName) {


        Path path = Paths.get(UPLOAD_DIR + imageDir);
        if (!Files.exists(path)){
            Files.createDirectories(path);
        }

        path = Paths.get(path.toString() + File.separator + fileName);

        if (content != null) {
            Files.write(path, content);
        }

        return path.toString();
    }

    @SneakyThrows
    @Override
    public byte[] downloadImage(String url) {
        Path path = Paths.get(url);
        if (Files.exists(path)){
            return Files.readAllBytes(path);
        } else {
            return null;
        }
    }



}
