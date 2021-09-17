package com.trade_accounting.services.impl;

import com.trade_accounting.models.BonusProgram;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.File;
import com.trade_accounting.models.RetailOperationWithPoints;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.Task;
import com.trade_accounting.models.dto.RetailOperationWithPointsDto;
import com.trade_accounting.repositories.BonusProgramRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.FileRepository;
import com.trade_accounting.repositories.RetailOperationWithPointsRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.services.interfaces.RetailOperationWithPointsService;
import com.trade_accounting.utils.mapper.RetailOperationWithPointsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RetailOperationWithPointsServiceImpl implements RetailOperationWithPointsService {

    private final RetailOperationWithPointsRepository retailOperationWithPointsRepository;
    private final BonusProgramRepository bonusProgramRepository;
    private final ContractorRepository contractorRepository;
    private final TaskRepository taskRepository;
    private final FileRepository fileRepository;
    private final RetailOperationWithPointsMapper retailOperationWithPointsMapper;

    @Override
    public List<RetailOperationWithPointsDto> getAll() {
        return retailOperationWithPointsRepository.findAll().stream()
                .map(retailOperationWithPointsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailOperationWithPointsDto getById(Long id) {
        Optional<RetailOperationWithPoints> retailOperationWithPoints = retailOperationWithPointsRepository.findById(id);
        return retailOperationWithPointsMapper.toDto(
                retailOperationWithPoints.orElse(new RetailOperationWithPoints()));
    }

    @Override
    public RetailOperationWithPointsDto create(RetailOperationWithPointsDto dto) {

        RetailOperationWithPoints retailOperationWithPoints = retailOperationWithPointsMapper.toModel(dto);
        BonusProgram bonusProgram = bonusProgramRepository.getOne(dto.getBonusProgramId());
        Contractor contractor = contractorRepository.getOne(dto.getContractorId());
        Task task = taskRepository.getOne(dto.getTaskId());
        List<File> fileList = dto.getFileIds().stream()
                .map(fileRepository::getOne)
                .collect(Collectors.toList());

        retailOperationWithPoints.setBonusProgram(bonusProgram);
        retailOperationWithPoints.setContractor(contractor);
        retailOperationWithPoints.setTask(task);
        retailOperationWithPoints.setFiles(fileList);

        return retailOperationWithPointsMapper.toDto(retailOperationWithPointsRepository.save(retailOperationWithPoints));
    }

    @Override
    public RetailOperationWithPointsDto update(RetailOperationWithPointsDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        retailOperationWithPointsRepository.deleteById(id);
    }
}
