package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    private Long id;

    private String imageUrl;

    private byte[] content;

    private String sortNumber;

    public ImageDto(Long id, String imageUrl, String sortNumber) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.sortNumber = sortNumber;
    }
}
