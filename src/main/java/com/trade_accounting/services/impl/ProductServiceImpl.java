package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.ProductPrice;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.models.dto.ProductPriceDto;
import com.trade_accounting.repositories.AttributeOfCalculationObjectRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.ProductGroupRepository;
import com.trade_accounting.repositories.ProductPriceRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.repositories.TaxSystemRepository;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.repositories.UnitRepository;
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

    private final ProductPriceRepository productPriceRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductGroupRepository productGroupRepository,
                              UnitRepository unitRepository,
                              TaxSystemRepository taxSystemRepository,
                              ContractorRepository contractorRepository,
                              AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository, ImageRepository imageRepository, TypeOfPriceRepository typeOfPriceRepository, ProductPriceRepository productPriceRepository) {
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
        this.unitRepository = unitRepository;
        this.taxSystemRepository = taxSystemRepository;
        this.contractorRepository = contractorRepository;
        this.attributeOfCalculationObjectRepository = attributeOfCalculationObjectRepository;
        this.imageRepository = imageRepository;
        this.productPriceRepository = productPriceRepository;
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
            productDto.setProductPriceDtos(productPriceRepository.getPricesDtoByProductId(productDto.getId()));
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
        List<ProductPriceDto> list = productPriceRepository.getPricesDtoByProductId(id);
        list.forEach(productPriceDto -> {
            ProductPrice productPrice = productPriceRepository.getOne(productPriceDto.getId());
            productPriceDto.setTypeOfPriceDto(ModelDtoConverter.convertToTypeOfPriceDto(productPrice.getTypeOfPrice()));
        });
        productDto.setProductPriceDtos(list);
        return productDto;
    }

    @Override
    public void create(ProductDto productDto) {
        List<ProductPrice> productPrices = new ArrayList<>();
        if (productDto.getProductPriceDtos() != null){
            productPrices = productDto.getProductPriceDtos().stream()
                    .map(ModelDtoConverter::convertToProductPrice).collect(Collectors.toList());
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
                productPrices
        ));
    }

    @Override
    public void update(ProductDto productDto) {
        List<ProductPrice> productPrices = new ArrayList<>();
        if (productDto.getProductPriceDtos() != null) {
            for (ProductPriceDto productPriceDto : productDto.getProductPriceDtos()) {
                ProductPrice productPrice;
                if (productPriceDto.getId() == null) {
                    productPrice = productPriceRepository.saveAndFlush(ModelDtoConverter.convertToProductPrice(productPriceDto));
                } else {
                    productPrice = productPriceRepository.getOne(productPriceDto.getId());
                    productPrice.setValue(productPriceDto.getValue());
                }
                productPrices.add(productPrice);
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
                productPrices,
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
            productDto.setProductPriceDtos(productPriceRepository.getPricesDtoByProductId(productDto.getId()));
        }
        return productDtos;
    }

    @Override
    public List<ProductDto> getAllLiteByProductGroupId(Long id) {
        return productRepository.getAllByProductGroupId(id);
    }
}
