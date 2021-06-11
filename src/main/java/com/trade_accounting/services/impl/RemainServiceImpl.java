package com.trade_accounting.services.impl;

import com.trade_accounting.models.Production;
import com.trade_accounting.models.Remain;
import com.trade_accounting.models.dto.RemainDto;
import com.trade_accounting.repositories.RemainRepository;
import com.trade_accounting.services.interfaces.RemainService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RemainServiceImpl implements RemainService {

    private final RemainRepository remainRepository;

    private final DtoMapper dtoMapper;

    public RemainServiceImpl (RemainRepository remainRepository, DtoMapper dtoMapper){
        this.remainRepository = remainRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<RemainDto> getAll() {

      final List<RemainDto> collect = remainRepository.findAll().stream()
              .map(dtoMapper::remainToRemainDto)
              .collect(Collectors.toList());
      return collect;
    }

    @Override
    public RemainDto getById(Long id) {
        return dtoMapper.remainToRemainDto(remainRepository.findById(id).orElse(new Remain()));
    }

    @Override
    public RemainDto create(RemainDto dto) {
        Remain remain = remainRepository.save(dtoMapper.remainDtoToRemain(dto));
        dto.setId(remain.getId());
        return dtoMapper.remainToRemainDto(remain);

    }

    @Override
    public RemainDto update(RemainDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        remainRepository.deleteById(id);
    }
}
