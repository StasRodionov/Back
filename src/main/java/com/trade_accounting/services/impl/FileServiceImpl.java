package com.trade_accounting.services.impl;

import com.trade_accounting.models.FileInfo;
import com.trade_accounting.repositories.FileRepository;
import com.trade_accounting.services.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.core.appender.FileManager;

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
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileDAO;


    @Transactional(rollbackFor = {IOException.class})
    @Override
    public FileInfo create(MultipartFile resource) throws IOException {
        String key = generateKey(resource.getName());
        FileInfo createdFile = FileInfo.builder()
                .name(resource.getOriginalFilename())
                .key(key)
                .size(resource.getSize())
                .uploadDate(LocalDate.now())
                .build();
        createdFile = fileDAO.save(createdFile);
        upload(resource.getBytes(), key);

        return createdFile;
    }

    @Override
    public Resource download(String key) throws IOException {
        return downloadFile(key);
    }

    @Transactional(readOnly = true)
    @Override
    public FileInfo findById(Long fileId) {
        return fileDAO.findById(fileId).get();
    }

    @Transactional(rollbackFor = {IOException.class})
    @Override
    public void delete(Long fileId) throws IOException {
        FileInfo file = fileDAO.findById(fileId).get();
        fileDAO.deleteById(fileId);
        deleteFile(file.getKey());
    }

    public void upload(byte[] resource, String keyName) throws IOException {

        Path path = Paths.get("src/main/resources", keyName);
        Path file = Files.createFile(path);
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file.toString());
            stream.write(resource);
        } finally {
            stream.close();
        }
    }

    private String generateKey(String name) {
        return DigestUtils.md5Hex(name + LocalDateTime.now().toString());
    }

    public Resource downloadFile(String key) throws IOException {
        Path path = Paths.get("src/main/resources/" + key);
        Resource resource = new UrlResource(path.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new IOException();
        }
    }

    public void deleteFile(String key) throws IOException {
        Path path = Paths.get("src/main/resources/" + key);
        Files.delete(path);
    }
}
