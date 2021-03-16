package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.services.interfaces.ImageService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    private static final String UPLOAD_DIR = "images/";
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
        ImageDto imageDto = imageRepository.getById(id);
        String content = getImageFromFile(imageDto.getImageUrl());
        String fileName = Paths.get(imageDto.getImageUrl()).getFileName().toString();
        imageDto.setContent(content);
        imageDto.setFileName(fileName);
        return imageDto;
    }

    @Override
    public ImageDto create(ImageDto imageDto) {
        byte[] content = Base64.getDecoder().decode(imageDto.getContent());
        String path = saveImageToFile(content, imageDto.getFileName());
        imageDto.setImageUrl(path);

        Image image = imageRepository.save(new Image(path, imageDto.getSortNumber()));
        imageDto.setId(image.getId());
        return imageDto;
    }

    @Override
    public ImageDto update(ImageDto imageDto) {
        Image image = new Image();
        image.setId(imageDto.getId());
        image.setImageUrl(imageDto.getImageUrl());
        image.setSortNumber(imageDto.getSortNumber());
        imageRepository.save(image);

        return imageDto;
    }

    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        String path = imageRepository.getById(id).getImageUrl();
        Files.deleteIfExists(Paths.get(path));
        imageRepository.deleteById(id);
    }

    @SneakyThrows
    @Override
    public String saveImageToFile(byte[] content, String name) {
        File uploadDir = new File(UPLOAD_DIR);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        File file = new File(UPLOAD_DIR + name);
        try (OutputStream out = new FileOutputStream(file, false)) {
            out.write(content);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file.getPath();
    }

    @SneakyThrows
    @Override
    public String getImageFromFile(String path) {
        return Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(path)));
    }
}