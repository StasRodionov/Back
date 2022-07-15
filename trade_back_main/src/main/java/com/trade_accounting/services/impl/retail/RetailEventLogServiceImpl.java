package com.trade_accounting.services.impl.retail;

import com.trade_accounting.models.dto.retail.RetailEventLogDto;
import com.trade_accounting.models.entity.retail.RetailEventLog;
import com.trade_accounting.repositories.retail.RetailEventLogRepository;
import com.trade_accounting.services.interfaces.retail.RetailEventLogService;
import com.trade_accounting.utils.mapper.retail.RetailEventLogMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RetailEventLogServiceImpl implements RetailEventLogService {

    private final RetailEventLogRepository retailEventLogRepository;
    private final RetailEventLogMapper retailEventLogMapper;

    public RetailEventLogServiceImpl(RetailEventLogRepository retailEventLogRepository, RetailEventLogMapper retailEventLogMapper) {
        this.retailEventLogRepository = retailEventLogRepository;
        this.retailEventLogMapper = retailEventLogMapper;
    }

    @Override
    public List<RetailEventLogDto> getAll() {
        return retailEventLogRepository.findAll().stream()
                .map(retailEventLogMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailEventLogDto getById(Long id) {
        return null;
    }

    @Override
    public RetailEventLogDto create(RetailEventLogDto dto) {
        return null;
    }

    @Override
    public RetailEventLogDto update(RetailEventLogDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<RetailEventLogDto> search(Specification<RetailEventLog> spec) {
        return null;
    }
}
