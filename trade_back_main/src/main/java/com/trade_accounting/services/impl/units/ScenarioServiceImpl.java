package com.trade_accounting.services.impl.units;

import com.trade_accounting.models.dto.units.ScenarioDto;
import com.trade_accounting.models.entity.units.Scenario;
import com.trade_accounting.repositories.units.ScenarioRepository;
import com.trade_accounting.services.interfaces.units.ScenarioService;
import com.trade_accounting.utils.mapper.units.ScenarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScenarioServiceImpl implements ScenarioService {

    private final ScenarioRepository scenarioRepository;
    private final ScenarioMapper scenarioMapper;

    @Override
    public List<ScenarioDto> getAll() {
        return scenarioRepository.findAll()
                .stream()
                .map(scenarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScenarioDto> search(Specification<Scenario> spec) {
        return executeSearch(scenarioRepository, scenarioMapper::toDto, spec);
    }

    @Override
    public ScenarioDto getById(Long id) {
        Optional<Scenario> scenario = scenarioRepository.findById(id);
        return scenarioMapper.toDto(
                scenario.orElse(
                        new Scenario()
                )
        );
    }

    @Override
    public ScenarioDto create(ScenarioDto scenarioDto) {
        Scenario scenario = scenarioRepository.save(scenarioMapper.toModel(scenarioDto));
        scenarioDto.setId(scenario.getId());
        return scenarioMapper.toDto(scenario);
    }

    @Override
    public ScenarioDto update(ScenarioDto scenarioDto) {return create(scenarioDto);
    }

    @Override
    public void deleteById(Long id) {
        scenarioRepository.deleteById(id);
    }

    @Override
    public List<ScenarioDto> searchByString(String text) {
        return scenarioRepository.getBySearch(text).stream()
                .map(scenarioMapper::toDto)
                .collect(Collectors.toList());

    }
}
