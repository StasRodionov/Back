package com.trade_accounting.config.init;

import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.services.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author by Stanislav Dusyak
 */

@Component
@RequiredArgsConstructor
public class ImageInit extends InitData {
    private final ImageService imageService;

    @Override
    void init() {
        initImage();
    }

    private void initImage() {
        for (long i = 1L; i < 19; i++) {
            imageService.create(ImageDto.builder()
                    .fileExtension("JPG")
                    .build()
            );
        }
    }
}
