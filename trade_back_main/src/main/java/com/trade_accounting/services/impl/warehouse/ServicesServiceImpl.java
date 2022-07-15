package com.trade_accounting.services.impl.warehouse;


import com.trade_accounting.models.dto.warehouse.KitDto;
import com.trade_accounting.models.dto.warehouse.ServicesDto;
import com.trade_accounting.models.entity.util.File;
import com.trade_accounting.models.entity.util.Image;
import com.trade_accounting.models.entity.warehouse.Kit;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.ProductPrice;
import com.trade_accounting.models.entity.warehouse.Services;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.company.TaxSystemRepository;
import com.trade_accounting.repositories.units.UnitRepository;
import com.trade_accounting.repositories.util.FileRepository;
import com.trade_accounting.repositories.util.ImageRepository;
import com.trade_accounting.repositories.warehouse.KitRepository;
import com.trade_accounting.repositories.warehouse.ProductGroupRepository;
import com.trade_accounting.repositories.warehouse.ProductPriceRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.repositories.warehouse.ServicesRepository;
import com.trade_accounting.services.interfaces.warehouse.KitService;
import com.trade_accounting.services.interfaces.warehouse.ServicesService;
import com.trade_accounting.utils.mapper.util.FileMapper;
import com.trade_accounting.utils.mapper.util.ImageMapper;
import com.trade_accounting.utils.mapper.warehouse.KitMapper;
import com.trade_accounting.utils.mapper.warehouse.ServicesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;

    private final ImageRepository imageRepository;

    private final FileRepository fileRepository;

    private final ProductPriceRepository productPriceRepository;

    private final UnitRepository unitRepository;

    private final ContractorRepository contractorRepository;

    private final TaxSystemRepository taxSystemRepository;

    private final ProductGroupRepository productGroupRepository;

    private final ProductRepository productRepository;

    private final ImageMapper imageMapper;

    private final FileMapper fileMapper;

    private final ServicesMapper servicesMapper;


    @Override
    public List<ServicesDto> getAll() {
        return servicesRepository
                .findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(servicesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServicesDto getById(Long id) {
       Services services = servicesRepository.getOne(id);

        ServicesDto servicesDto = servicesMapper.toDto(services);

        return servicesDto;
    }

    @Override
    public ServicesDto create(ServicesDto dto) {
        List<Image> preparedImages = imageMapper.toListModel(dto.getImageDtos(), "services");
        List<Image> savedImages = imageRepository.saveAll(preparedImages);
        List<File> preparedFiles = fileMapper.toListModel(dto.getFileDtos());
        List<File> savedFiles = fileRepository.saveAll(preparedFiles);
        Services services = servicesMapper.toModel(dto);
        services.setImages(savedImages);

        savedFiles.forEach(file -> file.setServices(services));
        services.setFiles(savedFiles);

        services.setUnit(unitRepository.getOne(dto.getUnitId()));
        services.setContractor((contractorRepository.getOne(dto.getContractorId())));

        List<Product> products =new ArrayList<>();
        dto.getProductIds()
                .forEach(productId -> products.add(productRepository.getOne(productId)));
        services.setProducts(products);

        List<ProductPrice> prices = new ArrayList<>();
        dto.getProductPriceIds()
                .forEach(productPriceId -> prices.add(productPriceRepository.getOne(productPriceId)));
        services.setProductPrices(prices);

        services.setTaxSystem(taxSystemRepository.getOne(dto.getTaxSystemId()));
        services.setProductGroup(productGroupRepository.getOne(dto.getProductGroupId()));

        servicesRepository.saveAndFlush(services);
        return servicesMapper.toDto(services);
    }

    @Override
    public ServicesDto update(ServicesDto dto) {
        List<Image> preparedImages = imageMapper.toListModel(dto.getImageDtos(), "services");
        List<Image> savedImages = imageRepository.saveAll(preparedImages);
        List<File> preparedFiles = fileMapper.toListModel(dto.getFileDtos());
        List<File> savedFiles = fileRepository.saveAll(preparedFiles);
        Services services = servicesMapper.toModel(dto);
        services.setImages(savedImages);

        savedFiles.forEach(file -> file.setServices(services));
        services.setFiles(savedFiles);

        List<Product> products =new ArrayList<>();
        dto.getProductIds()
                .forEach(productId -> products.add(productRepository.getOne(productId)));
        services.setProducts(products);

        List<ProductPrice> prices = new ArrayList<>();
        dto.getProductPriceIds()
                .forEach(productPriceId -> prices.add(productPriceRepository.getOne(productPriceId)));
        services.setProductPrices(prices);

        servicesRepository.saveAndFlush(services);
        return servicesMapper.toDto(services);
    }

    @Override
    public void deleteById(Long id) {
        Services services = servicesRepository.getOne(id);
        servicesRepository.deleteById(id);
        services.getFiles().forEach(file -> {
            try {
                Files.deleteIfExists(Path.of(file.getPlacement() + file.getKey() + file.getExtension()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public List<ServicesDto> search(Specification<Services> spec) {
        return null;
    }
}
