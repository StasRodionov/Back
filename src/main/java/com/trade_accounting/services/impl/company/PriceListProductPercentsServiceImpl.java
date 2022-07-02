package com.trade_accounting.services.impl.company;

import com.trade_accounting.models.dto.company.PriceListProductPercentsDto;
import com.trade_accounting.models.entity.company.PriceListProductPercents;
import com.trade_accounting.repositories.company.PriceListProductPercentsRepository;
import com.trade_accounting.repositories.company.PriceListRepository;
import com.trade_accounting.services.interfaces.company.PriceListProductPercentsService;
import com.trade_accounting.utils.mapper.company.PriceListProductPercentsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceListProductPercentsServiceImpl implements PriceListProductPercentsService {

    private final PriceListProductPercentsRepository priceListProductPercentsRepository;
    private final PriceListProductPercentsMapper priceListProductPercentsMapper;
    private final PriceListRepository priceListRepository;

    @Override
    public List<PriceListProductPercentsDto> getAll() {
        return priceListProductPercentsRepository.findAll().stream()
                .map(priceListProductPercentsMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PriceListProductPercentsDto getById(Long id) {
        return priceListProductPercentsMapper.toDto(priceListProductPercentsRepository
                .findById(id).orElse(new PriceListProductPercents()));
    }

    @Override
    public PriceListProductPercentsDto create(PriceListProductPercentsDto dto) {
        PriceListProductPercents priceListProductPercents = priceListProductPercentsMapper.toModel(dto);
        priceListProductPercents.setPriceList(priceListRepository.findById(dto.getPriceListId()).orElse(null));
        return priceListProductPercentsMapper.toDto(priceListProductPercentsRepository.save(priceListProductPercents));
    }

    @Override
    public PriceListProductPercentsDto update(PriceListProductPercentsDto dto) {
        PriceListProductPercents priceListProductPercents = priceListProductPercentsMapper.toModel(dto);
        priceListProductPercents.setPriceList(priceListRepository.findById(dto.getPriceListId()).orElse(null));
        return priceListProductPercentsMapper.toDto(priceListProductPercentsRepository.save(priceListProductPercents));
    }

    @Override
    public void deleteById(Long id) {
        priceListProductPercentsRepository.deleteById(id);
    }

    @Override
    public void createAll(List<PriceListProductPercentsDto> priceListProductPercentsDtos) {
        priceListProductPercentsRepository.saveAll(
                priceListProductPercentsDtos.stream()
                        .map(priceListProductPercentsMapper::toModel)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<PriceListProductPercentsDto> getAllByPriceListId(Long id) {
        return priceListProductPercentsRepository.findAllByPriceListId(id)
                .stream().map(priceListProductPercentsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PriceListProductPercentsDto> search(Specification<PriceListProductPercents> spec) {
        return executeSearch(priceListProductPercentsRepository, priceListProductPercentsMapper::toDto, spec);
    }
}
