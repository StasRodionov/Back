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

import java.util.List;

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

    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> productDtos = productRepository.getAll();
        for (ProductDto productDto : productDtos) {
            productDto.setUnitDto(unitRepository.getById(productDto.getUnitDto().getId()));
            productDto.setProductGroupDto(productGroupRepository.getById(productDto.getProductGroupDto().getId()));
            productDto.setAttributeOfCalculationObjectDto(attributeOfCalculationObjectRepository.getById(productDto.getAttributeOfCalculationObjectDto().getId()));
            productDto.setContractorDto(contractorRepository.getById(productDto.getContractorDto().getId()));
            productDto.setTaxSystemDto(taxSystemRepository.getById(productDto.getTaxSystemDto().getId()));
            productDto.setImageDto(imageRepository.getAllDtoById(productDto.getId()));
            productDto.setTypeOfPriceDto(typeOfPriceRepository.getAllDtoById(productDto.getId()));
        }
        return productDtos;
    }

    @Override
    public ProductDto getById(Long id) {
        ProductDto productDto = productRepository.getById(id);
        productDto.setUnitDto(unitRepository.getById(productDto.getUnitDto().getId()));
        productDto.setProductGroupDto(productGroupRepository.getById(productDto.getProductGroupDto().getId()));
        productDto.setAttributeOfCalculationObjectDto(attributeOfCalculationObjectRepository.getById(productDto.getAttributeOfCalculationObjectDto().getId()));
        productDto.setContractorDto(contractorRepository.getById(productDto.getContractorDto().getId()));
        productDto.setTaxSystemDto(taxSystemRepository.getById(productDto.getTaxSystemDto().getId()));
        productDto.setImageDto(imageRepository.getAllDtoById(id));
        productDto.setTypeOfPriceDto(typeOfPriceRepository.getAllDtoById(id));
        return productDto;
    }

    @Override
    public void create(ProductDto productDto) {
        Product product = new Product(
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
                imageRepository.getAllById(productDto.getId()),
                typeOfPriceRepository.getAllById(productDto.getId())
        );
        productRepository.save(product);
    }

    @Override
    public void update(ProductDto productDto) {
        Product product = new Product(
                productDto.getId(),
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
                imageRepository.getAllById(productDto.getId()),
                typeOfPriceRepository.getAllById(productDto.getId())
        );
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
