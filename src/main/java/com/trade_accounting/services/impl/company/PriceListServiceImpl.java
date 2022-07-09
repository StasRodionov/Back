package com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.models.dto.company.PriceListDto;
import com.trade_accounting.repositories.company.PriceListRepository;
import com.trade_accounting.services.interfaces.company.CompanyService;
import com.trade_accounting.services.interfaces.company.PriceListService;
import com.trade_accounting.utils.mapper.company.PriceListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceListServiceImpl implements PriceListService {

    private final PriceListRepository priceListRepository;
    private final PriceListMapper priceListMapper;
    private final CompanyService companyService;

    @Override
    public List<PriceListDto> getAll() {
        return priceListRepository.findAll().stream()
                .map(priceListMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PriceListDto getById(Long id) {
        PriceList priceList = priceListRepository.getOne(id);
        return priceListMapper.toDto(priceList);
    }

    /**
     * @changed by Pavel Andrusov
     */
    @Override
    public PriceListDto create(PriceListDto dto) {
        return priceListMapper.toDto(priceListRepository.save(priceListMapper.toModel(dto)));
    }

    @Override
    public PriceListDto update(PriceListDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        priceListRepository.deleteById(id);
    }

    @Override
    public List<PriceListDto> getAllForFilter(String string) {
        List<PriceListDto> list = getAll();
        List<PriceListDto> listFilter = new ArrayList<>();
        list.stream()
                .filter(e -> companyService.getById(e.getCompanyId()).getName().equals(string))
                .forEach(listFilter::add);
        return listFilter;
    }

    @Override
    public List<PriceListDto> search(Specification<PriceList> spec) {
        return priceListRepository.findAll(spec).stream().map(priceListMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void moveToRecyclebin(long id) {
        PriceList priceList = priceListRepository.getOne(id);
        priceList.setIsRecyclebin(true);
        priceListRepository.save(priceList);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        PriceList priceList = priceListRepository.getOne(id);
        priceList.setIsRecyclebin(false);
        priceListRepository.save(priceList);
    }

    @Override
    public List<PriceListDto> quickSearch(String text) {
        return priceListRepository.getBySearch(text).stream()
                .map(priceListMapper::toDto)
                .collect(Collectors.toList());
    }

}
