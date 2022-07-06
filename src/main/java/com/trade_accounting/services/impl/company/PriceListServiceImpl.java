package com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.PriceList;
import com.trade_accounting.models.dto.company.PriceListDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.PriceListRepository;
import com.trade_accounting.services.interfaces.company.CompanyService;
import com.trade_accounting.services.interfaces.company.PriceListProductPercentsService;
import com.trade_accounting.services.interfaces.company.PriceListProductService;
import com.trade_accounting.services.interfaces.company.PriceListService;
import com.trade_accounting.utils.mapper.company.PriceListMapper;
import com.trade_accounting.utils.mapper.company.PriceListProductMapper;
import com.trade_accounting.utils.mapper.company.PriceListProductPercentsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceListServiceImpl implements PriceListService {

    private final PriceListRepository priceListRepository;
    private final CompanyRepository companyRepository;
    private final PriceListMapper priceListMapper;
    private final CompanyService companyService;

    private final PriceListProductService priceListProductService;

    private final PriceListProductPercentsService priceListProductPercentsService;

    private final PriceListProductMapper priceListProductMapper;

    private final PriceListProductPercentsMapper priceListProductPercentsMapper;
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
        LocalDateTime time = LocalDateTime.parse(dto.getDate().replace("T"," ")
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        PriceList priceList = priceListMapper.toModel(dto);
        priceList.setCompany(companyRepository.getOne(dto.getCompanyId()));
        priceList.setDate(time);
        priceList.setComment(dto.getComment());
        priceList.setProducts(priceListProductService.searchByPriceListId(dto.getId()).stream()
                .map(priceListProductMapper::toModel).collect(Collectors.toList()));
        priceList.setPercents(priceListProductPercentsService.searchByPriceListId(dto.getId()).stream()
                .map(priceListProductPercentsMapper::toModel).collect(Collectors.toList()));
        return priceListMapper.toDto(priceListRepository.save(priceList));
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
                .filter(e-> companyService.getById(e.getCompanyId()).getName().equals(string))
                .forEach(e -> listFilter.add(e));
        return listFilter;
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

}
