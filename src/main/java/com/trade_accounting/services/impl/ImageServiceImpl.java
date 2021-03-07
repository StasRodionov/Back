package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.services.interfaces.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
        Image image = imageRepository.save(
                new Image(imageDto.getImageUrl(), imageDto.getSortNumber()));
        imageDto.setId(image.getId());
    }

    @Override
    public void update(ImageDto imageDto) {
        Image image = new Image();
        image.setId(imageDto.getId());
        image.setImageUrl(imageDto.getImageUrl());
        image.setSortNumber(imageDto.getSortNumber());


        imageRepository.save(image);
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public String upload(byte[] content, String name) {
        OutputStream outputStream = null;
        File tmp;

        try {
            FileName fileName = new FileName(name);
            tmp = File.createTempFile("_image.", fileName.getExtension());
            outputStream = new FileOutputStream(tmp);
            outputStream.write(content);

        } catch (IOException e) {
            throw new RuntimeException(e);

        } finally {
            IOUtils.closeQuietly(outputStream);
        }
        return tmp.getAbsolutePath();
    }

    @Override
    public File download(String path) {
        return null;
    }

    public static class FileName {
        private final String name;

        FileName(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        String getExtension() {
            int index = name.lastIndexOf('.');
            return name.substring(index);
        }
    }
}