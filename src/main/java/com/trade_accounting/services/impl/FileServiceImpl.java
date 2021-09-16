package com.trade_accounting.services.impl;

import com.trade_accounting.models.File;
import com.trade_accounting.repositories.FileRepository;
import com.trade_accounting.services.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public File getById(Long fileId) {
        return fileRepository.findById(fileId).get();
    }

    @Override
    public File create(MultipartFile resource) throws IOException {
        String key = UUID.randomUUID().toString();

        File createdFile = File.builder()
                .name(resource.getOriginalFilename())
                .key(key)
                .size(resource.getSize())
                .uploadDate(LocalDate.now())
                .build();
        createdFile = fileRepository.save(createdFile);
        createFile(resource.getBytes(), key);

        return createdFile;
    }

    @Override
    public void delete(Long fileId) throws IOException {
        File file = fileRepository.findById(fileId).get();
        fileRepository.deleteById(fileId);

        Path path = Paths.get("src/main/resources/file/" + file.getKey());
        Files.delete(path);
    }

    @Override
    public Resource download(String key) throws IOException {
        Path path = Paths.get("src/main/resources/file/" + key);
        Resource resource = new UrlResource(path.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new IOException();
        }
    }

    public void createFile(byte[] resource, String keyName) throws IOException {

        Path path = Paths.get("src/main/resources/file", keyName);
        Path file = Files.createFile(path);
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file.toString());
            stream.write(resource);
        } finally {
            stream.close();
        }
    }
}
