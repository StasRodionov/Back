package com.trade_accounting.services.impl.util;

import com.trade_accounting.models.dto.util.ColumnsMaskDto;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.util.ColumnsMask;
import com.trade_accounting.models.entity.util.Grid;
import com.trade_accounting.models.entity.util.GridEmployeeKey;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.util.ColumnsMaskRepository;
import com.trade_accounting.services.interfaces.util.ColumnsMaskService;
import com.trade_accounting.utils.mapper.util.ColumnsMaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Transactional
@RequiredArgsConstructor
public class ColumnsMaskServiceImpl implements ColumnsMaskService {

    private final ColumnsMaskRepository columnsMaskRepository;

    private final EmployeeRepository employeeRepository;

    private final ColumnsMaskMapper columnsMaskMapper;

    @Override
    public ColumnsMaskDto getById(Integer gridId, Principal principal) {
        Employee employee = employeeRepository.findByEmail(principal.getName()).get();
        ColumnsMaskDto columnsMaskDto = columnsMaskRepository.getById(gridId, employee.getId());
        return columnsMaskDto == null ? new ColumnsMaskDto(0, employee.getId(), Integer.MAX_VALUE) : columnsMaskDto;
    }

    @Override
    public ColumnsMaskDto update(ColumnsMaskDto columnsMaskDto, Principal principal) {
        Employee employee = employeeRepository.findByEmail(principal.getName()).get();
        ColumnsMask columnMask = ColumnsMask.builder()
                .gridEmployeeKey(new GridEmployeeKey(columnsMaskDto.getGridDtoId(), employee.getId()))
                .grid(new Grid(columnsMaskDto.getGridDtoId()))
                .employee(employee)
                .mask(columnsMaskDto.getMask())
                .build();
        return columnsMaskMapper.toDto(columnsMaskRepository.save(columnMask));
    }

    @Override
    public ColumnsMaskDto create(ColumnsMaskDto columnsMaskDto, Principal principal) {
        return update(columnsMaskDto, principal);
    }
}
