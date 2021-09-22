package com.trade_accounting.services.impl;

import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.TechnicalOperations;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.TechnicalOperationsDto;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.repositories.TechnicalOperationsRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.TechnicalOperationsService;
import com.trade_accounting.utils.mapper.TechnicalOperationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TechnicalOperationsServiceImpl implements TechnicalOperationsService {

    private final TechnicalOperationsRepository technicalOperationsRepository;
    private final TechnicalOperationsMapper technicalOperationsMapper;
    private final TechnicalCardRepository technicalCardRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public List<TechnicalOperationsDto> search(Specification<TechnicalOperations> spec) {
        return executeSearch(technicalOperationsRepository, technicalOperationsMapper::toDto,spec);
    }

    @Override
    public List<TechnicalOperationsDto> getAll() {
        return technicalOperationsRepository.findAll().stream()
                .map(technicalOperationsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicalOperationsDto getById(Long id) {
        return technicalOperationsMapper.toDto(technicalOperationsRepository.getOne(id));
    }

    @Override
    public TechnicalOperationsDto create(TechnicalOperationsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public TechnicalOperationsDto update(TechnicalOperationsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        technicalOperationsRepository.deleteById(id);

    }

//    @Override
//    public List<TechnicalOperationsDto> search(String searchTerm) {
//        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
//            return technicalOperationsRepository.findAll().stream()
//                    .map(technicalOperationsMapper::toDto)
//                    .collect(Collectors.toList());
//        } else {
//            return technicalOperationsRepository.search(searchTerm).stream()
//                    .map(technicalOperationsMapper::toDto)
//                    .collect(Collectors.toList());
//        }
//    }

    private TechnicalOperationsDto saveOrUpdate(TechnicalOperationsDto dto) {
        TechnicalOperations technicalOperations = new TechnicalOperations();

        TechnicalCard technicalCard = technicalCardRepository.getTechnicalCardById(dto.getTechnicalCard());
        Warehouse warehouse = warehouseRepository.getWarehouseById(dto.getWarehouse());
        LocalDateTime dateOperation = LocalDateTime.parse(dto.getDate().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        technicalOperations.setTechnicalCard(technicalCard);
        technicalOperations.setWarehouse(warehouse);
        technicalOperations.setDate(dateOperation);

        technicalOperations.setId(dto.getId());
        technicalOperations.setVolume(dto.getVolume());
        technicalOperations.setComment(dto.getComment());
        technicalOperations.setIsPrint(dto.getIsPrint());
        technicalOperations.setIsSent(dto.getIsSent());

        return technicalOperationsMapper.toDto(technicalOperationsRepository.save(technicalOperations));
    }

}
