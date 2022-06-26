package com.trade_accounting.services.impl.units;

import com.trade_accounting.models.dto.units.ImportDto;
import com.trade_accounting.models.entity.units.Import;
import com.trade_accounting.repositories.units.ImportRepository;
import com.trade_accounting.services.interfaces.units.ImportService;
import com.trade_accounting.utils.mapper.units.ImportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {
    @Autowired
    private ImportRepository importRepository;

    private ImportMapper importMapper;

    @Override
    public List<ImportDto> getAll() {
        return importRepository.findAll()
                .stream()
                .map(importMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ImportDto getById(Long id) {
        Optional<Import> importOptional = importRepository.findById(id);
        return importMapper.toDto(importOptional.orElse(new Import()));
    }

    @Override
    public ImportDto create(ImportDto dto) {
        Import importEntity = importRepository.save(importMapper.toModel(dto));
        dto.setId(importEntity.getId());
        return importMapper.toDto(importEntity);
    }

    @Override
    public ImportDto update(ImportDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        importRepository.deleteById(id);
    }

    @Override
    public List<ImportDto> search(Specification<Import> spec) {
        return executeSearch(importRepository, importMapper::toDto, spec);
    }

    @Override
    public List<ImportDto> searchByString(String text) {
        return importRepository.getBySearch(text)
                .stream()
                .map(importMapper::toDto)
                .collect(Collectors.toList());
    }
}
