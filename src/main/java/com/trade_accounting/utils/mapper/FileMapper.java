package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.File;
import com.trade_accounting.models.dto.FileDto;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mapper(componentModel = "spring")
public interface FileMapper {

    default FileDto toDto(File file) {
        if (file == null) {
            return null;
        }
        return FileDto.builder()
                .id(file.getId())
                .name(file.getName())
                .extension(file.getExtension())
                .placement(file.getPlacement())
                .employee(file.getEmployee())
                .content(downloadFile(file.getKey(), file.getExtension()))
                .uploadDateTime(file.getUploadDateTime())
                .key(file.getKey())
                .build();
    }

    default File toModel(FileDto fileDto) {
        uploadFile(fileDto.getContent(), fileDto.getKey(), fileDto.getExtension());
        return File.builder()
                .id(fileDto.getId())
                .name(fileDto.getName())
                .extension(fileDto.getExtension())
                .placement(fileDto.getPlacement())
                .employee(fileDto.getEmployee())
                .uploadDateTime(fileDto.getUploadDateTime())
                .key(fileDto.getKey())
                .build();
    }

    @SneakyThrows
    default void uploadFile(byte[] content, String key, String extension) {
        String fs = java.io.File.separator;
        final String UPLOAD_DIR = "src" + fs +"main" + fs +"resources" + fs +"file" + fs;
        Path path = Paths.get(UPLOAD_DIR + key + extension);
        if (content != null) {
            Files.write(path, content);
        }
    }

    @SneakyThrows
    default byte[] downloadFile(String key, String extension) {
        String fs = java.io.File.separator;
        Path path = Paths.get("src" + fs +"main" + fs +"resources" + fs +"file" + fs + key + extension);
        if (Files.exists(path)) {
            return Files.readAllBytes(path);
        } else {
            return new byte[0];
        }
    }

}
