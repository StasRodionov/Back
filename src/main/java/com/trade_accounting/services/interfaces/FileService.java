package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileInfo create(MultipartFile resource) throws IOException;

    Resource download(String key) throws IOException;

    FileInfo findById(Long fileId);

    void delete(Long fileId) throws IOException;
}
