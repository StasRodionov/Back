package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAll();

    List<Image> getProductByImage(Long id);

    List<TypeOfPrice> getProductByTypeOfPrice(Long id);

    List<ImageDto> getProductByImageDto(Long id);

    List<TypeOfPriceDto> getProductByTypeOfPriceDto(Long id);

    ProductDto getById(Long id);

    void create(ProductDto productDto);

    void update(ProductDto productDto);

    void deleteById(Long id);
}
