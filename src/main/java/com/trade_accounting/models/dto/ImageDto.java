package com.trade_accounting.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class ImageDto {

    private Long id;

    private String imageUrl;

    private String sortNumber;

    @Transient
    private byte[] content;

    @Transient
    private String fileName;

    public ImageDto(Long id, String imageUrl, String sortNumber) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.sortNumber = sortNumber;
    }
}
