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
import java.nio.file.Path;
import java.nio.file.Paths;
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
        ImageDto imageDto = imageRepository.getById(id);
        imageDto.setContent(downloadImage(imageDto.getImageUrl()));
        return imageDto;
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


    @SneakyThrows
    @Override
    public String uploadImage(byte[] content, String name) {
        File uploadDir = new File(UPLOAD_DIR);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }


        File file = new File(UPLOAD_DIR + name);
        try (OutputStream outputStream =  new FileOutputStream(file, false);){
            outputStream.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file.getPath();
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
