package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.File;
import com.trade_accounting.models.dto.FileDto;

import java.util.List;

public interface FileService extends AbstractService<File>, SearchableService<File, FileDto>  {

    List<File> getAll();

    FileDto create(FileDto fileDto);

    File getById(Long fileId);

    void delete(Long fileId);
}
