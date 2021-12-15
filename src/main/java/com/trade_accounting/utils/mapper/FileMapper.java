package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.File;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.FileDto;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.springframework.security.core.parameters.P;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mapper(componentModel = "spring")
public interface FileMapper {
    String fs = java.io.File.separator;
    String UPLOAD_DIR = "src" + fs +"main" + fs +"resources" + fs +"file" + fs;

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
                .productId(file.getProduct().getId())
                .build();
    }

    default File toModel(FileDto fileDto) {
        fileDto.setPlacement(UPLOAD_DIR);
        uploadFile(fileDto.getContent(), fileDto.getKey(), fileDto.getExtension());
        return File.builder()
                .id(fileDto.getId())
                .name(fileDto.getName())
                .extension(fileDto.getExtension())
                .placement(fileDto.getPlacement())
                .employee(fileDto.getEmployee())
                .uploadDateTime(fileDto.getUploadDateTime())
                .key(fileDto.getKey())
                .product(fileDtoToProduct(fileDto))
                .build();
    }

    default Product fileDtoToProduct(FileDto fileDto) {
        if (fileDto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(fileDto.getId());
        return product;
    }

    @SneakyThrows
    default void uploadFile(byte[] content, String key, String extension) {
        Path path = Paths.get(UPLOAD_DIR + key + extension);
        if (content != null) {
            Files.write(path, content);
        }
    }

    @SneakyThrows
    default byte[] downloadFile(String key, String extension) {
        Path path = Paths.get(UPLOAD_DIR + key + extension);
        if (Files.exists(path)) {
            return Files.readAllBytes(path);
        } else {
            return new byte[0];
        }
    }

}
