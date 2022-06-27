package com.trade_accounting.services.impl.warehouse;


import com.trade_accounting.models.dto.warehouse.KitDto;
import com.trade_accounting.models.entity.util.File;
import com.trade_accounting.models.entity.util.Image;
import com.trade_accounting.models.entity.warehouse.Kit;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.ProductPrice;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.company.TaxSystemRepository;
import com.trade_accounting.repositories.units.UnitRepository;
import com.trade_accounting.repositories.util.FileRepository;
import com.trade_accounting.repositories.util.ImageRepository;
import com.trade_accounting.repositories.warehouse.KitRepository;
import com.trade_accounting.repositories.warehouse.ProductGroupRepository;
import com.trade_accounting.repositories.warehouse.ProductPriceRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.services.interfaces.warehouse.KitService;
import com.trade_accounting.utils.mapper.util.FileMapper;
import com.trade_accounting.utils.mapper.util.ImageMapper;
import com.trade_accounting.utils.mapper.warehouse.KitMapper;
import com.trade_accounting.utils.mapper.warehouse.ProductMapper;
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
public class KitServiceImpl implements KitService {

    private final KitRepository kitRepository;

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

    private final KitMapper kitMapper;


    @Override
    public List<KitDto> getAll() {
        return kitRepository
                .findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(kitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public KitDto getById(Long id) {
       Kit kit = kitRepository.getOne(id);

       KitDto kitDto = kitMapper.toDto(kit);
       kitDto.setImageDtos(imageMapper.toListDto(kit.getImages()));
       kitDto.setFileDtos(fileMapper.toListDto(kit.getFiles()));

        return kitDto;
    }

    @Override
    public KitDto create(KitDto dto) {
        List<Image> preparedImages = imageMapper.toListModel(dto.getImageDtos(), "kit");
        List<Image> savedImages = imageRepository.saveAll(preparedImages);
        List<File> preparedFiles = fileMapper.toListModel(dto.getFileDtos());
        List<File> savedFiles = fileRepository.saveAll(preparedFiles);
        Kit kit = kitMapper.toModel(dto);
        kit.setImages(savedImages);

        savedFiles.forEach(file -> file.setKit(kit));
        kit.setFiles(savedFiles);

        kit.setUnit(unitRepository.getOne(dto.getUnitId()));
        kit.setContractor((contractorRepository.getOne(dto.getContractorId())));

        List<Product> products =new ArrayList<>();
        dto.getProductIds()
                .forEach(productId -> products.add(productRepository.getOne(productId)));
        kit.setProducts(products);

        List<ProductPrice> prices = new ArrayList<>();
        dto.getProductPriceIds()
                .forEach(productPriceId -> prices.add(productPriceRepository.getOne(productPriceId)));
        kit.setProductPrices(prices);

        kit.setTaxSystem(taxSystemRepository.getOne(dto.getTaxSystemId()));
        kit.setProductGroup(productGroupRepository.getOne(dto.getProductGroupId()));

        kitRepository.saveAndFlush(kit);
        return kitMapper.toDto(kit);
    }

    @Override
    public KitDto update(KitDto dto) {
        List<Image> preparedImages = imageMapper.toListModel(dto.getImageDtos(), "kit");
        List<Image> savedImages = imageRepository.saveAll(preparedImages);
        List<File> preparedFiles = fileMapper.toListModel(dto.getFileDtos());
        List<File> savedFiles = fileRepository.saveAll(preparedFiles);
        Kit kit = kitMapper.toModel(dto);
        kit.setImages(savedImages);

        savedFiles.forEach(file -> file.setKit(kit));
        kit.setFiles(savedFiles);

        List<Product> products =new ArrayList<>();
        dto.getProductIds()
                .forEach(productId -> products.add(productRepository.getOne(productId)));
        kit.setProducts(products);

        List<ProductPrice> prices = new ArrayList<>();
        dto.getProductPriceIds()
                .forEach(productPriceId -> prices.add(productPriceRepository.getOne(productPriceId)));
        kit.setProductPrices(prices);

        kitRepository.saveAndFlush(kit);
        return kitMapper.toDto(kit);
    }

    @Override
    public void deleteById(Long id) {
        Kit kit = kitRepository.getOne(id);
        kitRepository.deleteById(id);
        kit.getFiles().forEach(file -> {
            try {
                Files.deleteIfExists(Path.of(file.getPlacement() + file.getKey() + file.getExtension()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public List<KitDto> search(Specification<Kit> spec) {
        return null;
    }
}
