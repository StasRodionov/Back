package com.trade_accounting.services.impl.units;

import com.trade_accounting.models.dto.units.ExportDto;
import com.trade_accounting.models.entity.units.Export;
import com.trade_accounting.repositories.units.ExportRepository;
import com.trade_accounting.services.interfaces.units.ExportService;
import com.trade_accounting.utils.mapper.units.ExportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ExportServiceImpl implements ExportService {

    private final ExportRepository exportRepository;

    private final ExportMapper exportMapper;

    @Override
    public List<ExportDto> getAll() {
        return exportRepository.findAll()
                .stream()
                .map(exportMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExportDto getById(Long id) {
        return null;
    }

    @Override
    public ExportDto create(ExportDto dto) {
        return null;
    }

    @Override
    public ExportDto update(ExportDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<ExportDto> search(Specification<Export> spec) {
        return null;
    }

    @Override
    public List<ExportDto> searchByString(String text) {
        return null;
    }
}
