package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import com.trade_accounting.repositories.AttributeOfCalculationObjectRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.ProductGroupRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.repositories.TaxSystemRepository;
import com.trade_accounting.repositories.TypeOfPriceRepository;
import com.trade_accounting.repositories.UnitRepository;
import com.trade_accounting.services.interfaces.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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

    private final TypeOfPriceRepository typeOfPriceRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductGroupRepository productGroupRepository,
                              UnitRepository unitRepository,
                              TaxSystemRepository taxSystemRepository,
                              ContractorRepository contractorRepository,
                              AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository, ImageRepository imageRepository, TypeOfPriceRepository typeOfPriceRepository) {
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
        this.unitRepository = unitRepository;
        this.taxSystemRepository = taxSystemRepository;
        this.contractorRepository = contractorRepository;
        this.attributeOfCalculationObjectRepository = attributeOfCalculationObjectRepository;
        this.imageRepository = imageRepository;
        this.typeOfPriceRepository = typeOfPriceRepository;
    }

//    @PostConstruct
//    public void method() {
//        create(new ProductDto());
//        update(new ProductDto());
//        getAll();
//        getById(1L);
//        deleteById(1L);
//    }

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
            productDto.setTypeOfPriceDto(typeOfPriceRepository.getTypeOfPriceById(productDto.getId()).stream()
                    .map(typeOfPrice -> typeOfPriceRepository.getById(typeOfPrice.getId()))
                    .collect(Collectors.toList()));        }
        return productDtos;
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
        productDto.setTypeOfPriceDto(typeOfPriceRepository.getTypeOfPriceById(id).stream()
                .map(typeOfPrice -> typeOfPriceRepository.getById(typeOfPrice.getId()))
                .collect(Collectors.toList()));
        return productDto;
    }

    @Override
    public void create(ProductDto productDto) {
        List<TypeOfPrice> typeOfPrices = new ArrayList<>();
        for (TypeOfPriceDto typeOfPriceDto : productDto.getTypeOfPriceDto()) {
            typeOfPrices.add(typeOfPriceRepository.getOne(typeOfPriceDto.getId()));
        }
        List<Image> images = new ArrayList<>();
        for (ImageDto imageDto : productDto.getImageDto()) {
            images.add(imageRepository.getOne(imageDto.getId()));
        }
        productRepository.save(new Product(
                productDto.getName(),
                productDto.getPurchasePrice(),
                productDto.getDescription(),
                productDto.getWeight(),
                productDto.getVolume(),
                productDto.getArchive(),
                unitRepository.getOne(productDto.getUnitDto().getId()),
                productGroupRepository.getOne(productDto.getProductGroupDto().getId()),
                taxSystemRepository.getOne(productDto.getTaxSystemDto().getId()),
                contractorRepository.getOne(productDto.getContractorDto().getId()),
                attributeOfCalculationObjectRepository.getOne(productDto.getAttributeOfCalculationObjectDto().getId()),
                images,
                typeOfPrices
        ));
    }

    @Override
    public void update(ProductDto productDto) {
        List<TypeOfPrice> typeOfPrices = new ArrayList<>();
        for (TypeOfPriceDto typeOfPriceDto : productDto.getTypeOfPriceDto()) {
            typeOfPrices.add(typeOfPriceRepository.getOne(typeOfPriceDto.getId()));
        }
        List<Image> images = new ArrayList<>();
        for (ImageDto imageDto : productDto.getImageDto()) {
            images.add(imageRepository.getOne(imageDto.getId()));
        }
        productRepository.save(new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getWeight(),
                productDto.getVolume(),
                productDto.getPurchasePrice(),
                productDto.getDescription(),
                unitRepository.getOne(productDto.getUnitDto().getId()),
                productDto.getArchive(),
                contractorRepository.getOne(productDto.getContractorDto().getId()),
                typeOfPrices,
                taxSystemRepository.getOne(productDto.getTaxSystemDto().getId()),
                images,
                productGroupRepository.getOne(productDto.getProductGroupDto().getId()),
                attributeOfCalculationObjectRepository.getOne(productDto.getAttributeOfCalculationObjectDto().getId())
        ));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
