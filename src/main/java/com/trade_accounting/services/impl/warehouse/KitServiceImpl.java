package com.trade_accounting.services.impl.warehouse;


import com.trade_accounting.models.dto.warehouse.KitDto;
import com.trade_accounting.models.entity.warehouse.Kit;
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

    private final ProductRepository productRepository;

    private final UnitRepository unitRepository;

    private final ContractorRepository contractorRepository;

    private final TaxSystemRepository taxSystemRepository;

    private final ProductGroupRepository productGroupRepository;

    private final ImageMapper imageMapper;

    private final ProductMapper productMapper;

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
        return null;
    }

    @Override
    public KitDto create(KitDto dto) {
        return null;
    }

    @Override
    public KitDto update(KitDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<KitDto> search(Specification<Kit> spec) {
        return null;
    }
}
