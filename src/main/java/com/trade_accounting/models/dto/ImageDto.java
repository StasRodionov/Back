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

    private String sortNumber;

    private String content;

    private String fileName;

    public ImageDto(Long id, String imageUrl, String sortNumber) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.sortNumber = sortNumber;
    }
}
