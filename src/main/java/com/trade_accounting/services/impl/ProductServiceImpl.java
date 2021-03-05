package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.Price;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.PriceDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.repositories.*;
import com.trade_accounting.services.interfaces.ProductService;
import com.trade_accounting.utils.ModelDtoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductGroupRepository productGroupRepository;

    private final UnitRepository unitRepository;

    private final TaxSystemRepository taxSystemRepository;

    private final ContractorRepository contractorRepository;

    private final AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository;

    private final ImageRepository imageRepository;

    private final PriceRepository priceRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductGroupRepository productGroupRepository,
                              UnitRepository unitRepository,
                              TaxSystemRepository taxSystemRepository,
                              ContractorRepository contractorRepository,
                              AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository, ImageRepository imageRepository, TypeOfPriceRepository typeOfPriceRepository, PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
        this.unitRepository = unitRepository;
        this.taxSystemRepository = taxSystemRepository;
        this.contractorRepository = contractorRepository;
        this.attributeOfCalculationObjectRepository = attributeOfCalculationObjectRepository;
        this.imageRepository = imageRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> productDtos = productRepository.getAll();

        for (ProductDto productDto : productDtos) {
            productDto.setUnitDto(unitRepository.getUnitByProductId(productDto.getId()));
            productDto.setProductGroupDto(productGroupRepository.getProductGroupByProductId(productDto.getId()));
            productDto.setAttributeOfCalculationObjectDto(
                    attributeOfCalculationObjectRepository.getAttributeOfCalculationObjectById(productDto.getId()));
            productDto.setContractorDto(contractorRepository.getContractorById(productDto.getId()));
            productDto.setTaxSystemDto(taxSystemRepository.getTaxSystemById(productDto.getId()));
            productDto.setImageDto(imageRepository.getAllById(productDto.getId()).stream()
                    .map(image -> imageRepository.getById(image.getId()))
                    .collect(Collectors.toList()));
            productDto.setPriceDtos(priceRepository.getPricesByProductId(productDto.getId()).stream()
                    .map(ModelDtoConverter::convertToPriceDto)
                    .collect(Collectors.toList()));
        }
        return productDtos;
    }

    @Override
    public List<ProductDto> getAllLite() {
        return productRepository.getAll();
    }

    @Override
    public ProductDto getById(Long id) {
        ProductDto productDto = productRepository.getById(id);
        productDto.setUnitDto(unitRepository.getUnitByProductId(id));
        productDto.setProductGroupDto(productGroupRepository.getProductGroupByProductId(id));
        productDto.setAttributeOfCalculationObjectDto(
                attributeOfCalculationObjectRepository.getAttributeOfCalculationObjectById(id));
        productDto.setContractorDto(contractorRepository.getContractorById(id));
        productDto.setTaxSystemDto(taxSystemRepository.getTaxSystemById(id));
        productDto.setImageDto(imageRepository.getAllById(id).stream()
                .map(image -> imageRepository.getById(image.getId()))
                .collect(Collectors.toList()));
        productDto.setPriceDtos(priceRepository.getPricesByProductId(id).stream()
                .map(ModelDtoConverter::convertToPriceDto)
                .collect(Collectors.toList()));
        return productDto;
    }

    @Override
    public void create(ProductDto productDto) {
        List<Price> prices = new ArrayList<>();
        if (productDto.getPriceDtos() != null){
            prices = productDto.getPriceDtos().stream()
                    .map(ModelDtoConverter::convertToPrice).collect(Collectors.toList());
        }


        List<Image> images = new ArrayList<>();
        if (productDto.getImageDto() != null) {
            for (ImageDto imageDto : productDto.getImageDto()) {
                images.add(imageRepository.getOne(imageDto.getId()));
            }
        }

        productRepository.save(new Product(
                productDto.getName(),
                productDto.getPurchasePrice(),
                productDto.getDescription(),
                productDto.getWeight(),
                productDto.getVolume(),
                productDto.getArchive(),
                productDto.getUnitDto() != null
                        ? unitRepository.getOne(productDto.getUnitDto().getId())
                        : null,
                productDto.getProductGroupDto() != null
                        ? productGroupRepository.getOne(productDto.getProductGroupDto().getId())
                        : null,
                productDto.getTaxSystemDto() != null
                        ? taxSystemRepository.getOne(productDto.getTaxSystemDto().getId())
                        : null,
                productDto.getContractorDto() != null
                        ? contractorRepository.getOne(productDto.getContractorDto().getId())
                        : null,
                productDto.getAttributeOfCalculationObjectDto() != null
                        ? attributeOfCalculationObjectRepository.getOne(productDto.getAttributeOfCalculationObjectDto().getId())
                        : null,
                images,
                prices
        ));
    }

    @Override
    public void update(ProductDto productDto) {
        List<Price> prices = new ArrayList<>();
        if (productDto.getPriceDtos() != null) {
            for (PriceDto priceDto : productDto.getPriceDtos()) {
                prices.add(priceRepository.getOne(priceDto.getId()));
            }
        }

        List<Image> images = new ArrayList<>();
        if (productDto.getImageDto() != null) {
            for (ImageDto imageDto : productDto.getImageDto()) {
                images.add(imageRepository.getOne(imageDto.getId()));
            }
        }

        productRepository.save(new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getWeight(),
                productDto.getVolume(),
                productDto.getPurchasePrice(),
                productDto.getDescription(),
                productDto.getUnitDto() != null
                        ? unitRepository.getOne(productDto.getUnitDto().getId())
                        : null,
                productDto.getArchive(),
                productDto.getContractorDto() != null
                        ? contractorRepository.getOne(productDto.getContractorDto().getId())
                        : null,
                prices,
                productDto.getTaxSystemDto() != null
                        ? taxSystemRepository.getOne(productDto.getTaxSystemDto().getId())
                        : null,
                images,
                productDto.getProductGroupDto() != null
                        ? productGroupRepository.getOne(productDto.getProductGroupDto().getId())
                        : null,
                productDto.getAttributeOfCalculationObjectDto() != null
                        ? attributeOfCalculationObjectRepository.getOne(productDto.getAttributeOfCalculationObjectDto().getId())
                        : null
        ));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllByProductGroupId(Long id) {
        List<ProductDto> productDtos = productRepository.getAllByProductGroupId(id);
        for (ProductDto productDto : productDtos) {
            productDto.setUnitDto(unitRepository.getUnitByProductId(productDto.getId()));
            productDto.setAttributeOfCalculationObjectDto(
                    attributeOfCalculationObjectRepository.getAttributeOfCalculationObjectById(productDto.getId()));
            productDto.setContractorDto(contractorRepository.getContractorById(productDto.getId()));
            productDto.setTaxSystemDto(taxSystemRepository.getTaxSystemById(productDto.getId()));
            productDto.setImageDto(imageRepository.getAllById(productDto.getId()).stream()
                    .map(image -> imageRepository.getById(image.getId()))
                    .collect(Collectors.toList()));
            productDto.setPriceDtos(priceRepository.getPricesByProductId(productDto.getId()).stream()
                    .map(ModelDtoConverter::convertToPriceDto)
                    .collect(Collectors.toList()));
        }
        return productDtos;
    }
}
