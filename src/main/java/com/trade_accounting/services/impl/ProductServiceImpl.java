package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.LegalDetailDto;
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

    private final ImageRepository imageRepository;

    private final ProductGroupRepository productGroupRepository;

    private final UnitRepository unitRepository;

    private final TaxSystemRepository taxSystemRepository;

    private final ContractorRepository contractorRepository;

    private final TypeOfPriceRepository typeOfPriceRepository;

    private final AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository;

    public ProductServiceImpl(ProductRepository productRepository, ImageRepository imageRepository, ProductGroupRepository productGroupRepository, UnitRepository unitRepository, TaxSystemRepository taxSystemRepository, ContractorRepository contractorRepository, TypeOfPriceRepository typeOfPriceRepository, AttributeOfCalculationObjectRepository attributeOfCalculationObjectRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.productGroupRepository = productGroupRepository;
        this.unitRepository = unitRepository;
        this.taxSystemRepository = taxSystemRepository;
        this.contractorRepository = contractorRepository;
        this.typeOfPriceRepository = typeOfPriceRepository;
        this.attributeOfCalculationObjectRepository = attributeOfCalculationObjectRepository;
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
            productDto.setImageDto(productRepository.getProductByImageDto(productDto.getId()));
            productDto.setTypeOfPriceDto(productRepository.getProductByTypeOfPriceDto(productDto.getId()));
        }
        return productDtos;
    }

    @Override
    public List<Image> getProductByImage(Long id) {
        return productRepository.getProductByImage(id);
    }

    @Override
    public List<TypeOfPrice> getProductByTypeOfPrice(Long id) {
        return productRepository.getProductByTypeOfPrice(id);
    }

    @Override
    public List<ImageDto> getProductByImageDto(Long id) {
        return productRepository.getProductByImageDto(id);
    }

    @Override
    public List<TypeOfPriceDto> getProductByTypeOfPriceDto(Long id) {
        return productRepository.getProductByTypeOfPriceDto(id);
    }

    @Override
    public ProductDto getById(Long id) {
        ProductDto productDto = productRepository.getById(id);
        productDto.setUnitDto(unitRepository.getById(productDto.getUnitDto().getId()));
        productDto.setProductGroupDto(productGroupRepository.getById(productDto.getProductGroupDto().getId()));
        productDto.setAttributeOfCalculationObjectDto(attributeOfCalculationObjectRepository.getById(productDto.getAttributeOfCalculationObjectDto().getId()));
        productDto.setContractorDto(contractorRepository.getById(productDto.getContractorDto().getId()));
        productDto.setTaxSystemDto(taxSystemRepository.getById(productDto.getTaxSystemDto().getId()));
        productDto.setImageDto(productRepository.getProductByImageDto(id));
        productDto.setTypeOfPriceDto(productRepository.getProductByTypeOfPriceDto(id));
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
                );
        product.setTypeOfPrices(productRepository.getProductByTypeOfPrice(productDto.getId()));
        product.setImages(productRepository.getProductByImage(productDto.getId()));
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
                productDto.getArchive(), productDto.getArchive(),
                unitRepository.getOne(productDto.getUnitDto().getId()),
                productGroupRepository.getOne(productDto.getProductGroupDto().getId()),
                taxSystemRepository.getOne(productDto.getTaxSystemDto().getId()),
                contractorRepository.getOne(productDto.getContractorDto().getId()),
                attributeOfCalculationObjectRepository.getOne(productDto.getAttributeOfCalculationObjectDto().getId()),
                );
        product.setTypeOfPrices(productRepository.getProductByTypeOfPrice(productDto.getId()));
        product.setImages(productRepository.getProductByImage(productDto.getId()));
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
