package com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.FiasAddressModelDto;
import com.trade_accounting.models.fias.FiasAddressModel;
import com.trade_accounting.repositories.fias.AddressDbRepository;
import com.trade_accounting.services.interfaces.fias.FiasDbService;
import com.trade_accounting.utils.ModelDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiasDbServiceImpl implements FiasDbService {
    private final AddressDbRepository repository;

    public FiasDbServiceImpl(AddressDbRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FiasAddressModelDto> getAll() {
        List<FiasAddressModel> all = repository.findAll();
        return all.stream().map(ModelDtoConverter::toFiasAddressModelDto).collect(Collectors.toList());
    }

    @Override
    public FiasAddressModelDto getById(Integer id) {
        FiasAddressModel model = repository.findById(id).orElse(null);
        return ModelDtoConverter.toFiasAddressModelDto(model);
    }

    @Override
    public FiasAddressModelDto create(FiasAddressModelDto dto) {
        FiasAddressModel model = repository.save(ModelDtoConverter.toFiasAddressModel(dto));
        return ModelDtoConverter.toFiasAddressModelDto(model);
    }

    @Override
    public void createAll(List<FiasAddressModelDto> dtoList) {
        List<FiasAddressModel> collect = dtoList.stream()
                .map(ModelDtoConverter::toFiasAddressModel).collect(Collectors.toList());
        repository.saveAll(collect);
    }

    @Override
    public FiasAddressModelDto update(FiasAddressModelDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<FiasAddressModelDto> findAllByLevel(String level) {
        List<FiasAddressModel> allByLevel = repository.findAllByLevel(level);
        return allByLevel.stream().map(ModelDtoConverter::toFiasAddressModelDto).collect(Collectors.toList());
    }

    @Override
    public List<FiasAddressModelDto> findAllByAoguid(String aoguid) {
        List<FiasAddressModel> byAoguid = repository.findAdressesByAoguid(aoguid);
        return byAoguid.stream().map(ModelDtoConverter::toFiasAddressModelDto).collect(Collectors.toList());
    }
}
