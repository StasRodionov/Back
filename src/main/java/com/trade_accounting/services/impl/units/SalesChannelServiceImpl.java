package com.trade_accounting.services.impl.units;


import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.dto.units.SalesChannelDto;
import com.trade_accounting.models.entity.units.SalesChannel;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.units.SalesChannelRepository;
import com.trade_accounting.services.interfaces.client.EmployeeService;
import com.trade_accounting.services.interfaces.units.SalesChannelService;
import com.trade_accounting.utils.mapper.units.SalesChannelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SalesChannelServiceImpl implements SalesChannelService {
    private final SalesChannelRepository salesChannelRepository;
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final SalesChannelMapper salesChannelMapper;

    @Override
    public List<SalesChannelDto> getAll() {
        return salesChannelRepository.getAll();
    }

    @Override
    public SalesChannelDto getById(Long id) {
        return salesChannelMapper.toDto(salesChannelRepository.findById(id).orElse(new SalesChannel()));
    }

    @Override
    public SalesChannelDto create(SalesChannelDto dto) {
        SalesChannel salesChannel = salesChannelMapper.toModel(dto);
        salesChannel.setGeneralAccess(false);
        salesChannel.setDepartmentOwner(employeeRepository.findByEmail(getPrincipalName()).get().getDepartment().getName());
        salesChannel.setEmployeeOwner(getPrincipalFullName());
        salesChannel.setDateOfChange(LocalDateTime.now().toString());
        salesChannel.setEmployeeChange(getPrincipalFullName());
        return salesChannelMapper.toDto(salesChannelRepository.save(salesChannelMapper.toModel(dto)));
    }

    @Override
    public SalesChannelDto update(SalesChannelDto dto) {
        SalesChannel salesChannel = salesChannelMapper.toModel(dto);
        salesChannel.setDateOfChange(LocalDateTime.now().toString());
        salesChannel.setEmployeeChange(getPrincipalName());
        return salesChannelMapper.toDto(salesChannelRepository.save(salesChannel));
    }

    @Override
    public void deleteById(Long id) {
        salesChannelRepository.deleteById(id);
    }

    private String getPrincipalName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    private String getPrincipalFullName() {
        String principalFullName = "";
        for (EmployeeDto employeeDto : employeeService.getAll()) {
            if (Objects.equals(employeeDto.getEmail(), getPrincipalName())) {
                principalFullName = employeeDto.getLastName() + " " + employeeDto.getFirstName().substring(0, 0) + ". "
                        + employeeDto.getMiddleName().substring(0, 0) + ".";
            }
        }
        return principalFullName;
    }
}

