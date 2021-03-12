package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.BadRequestException;
import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.repositories.UnitRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class CheckEntityServiceImpl implements CheckEntityService {
    private final UnitRepository unitRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final ImageRepository imageRepository;
    private final RoleRepository roleRepository;

    public CheckEntityServiceImpl(UnitRepository unitRepository,
                                  EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
                                  PositionRepository positionRepository,
                                  ImageRepository imageRepository,
                                  RoleRepository roleRepository) {
        this.unitRepository = unitRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.imageRepository = imageRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public void checkExistsUnitById(Long unitId) {
        if(!unitRepository.existsById(unitId)) {
            throw new NotFoundEntityException("Ед. измерения с id=" + unitId + ", не найдена");
        }
    }

    @Override
    public void checkExistsEmployeeById(Long employeeId) {
        if(!employeeRepository.existsById(employeeId)) {
            throw new NotFoundEntityException("Сотрудника с id=" + employeeId + ", не наден");
        }
    }

    @Override
    public void checkForBadEmployee(EmployeeDto employee) {
        DepartmentDto department = employee.getDepartmentDto();
        PositionDto position = employee.getPositionDto();
        ImageDto image = employee.getImageDto();
        Set<RoleDto> roles = employee.getRoleDto();

        boolean isDepartmentFilled = department != null && department.getId() != null;
        boolean isPositionFilled = position != null && position.getId() != null;
        boolean isImageFilled = image != null && image.getId() != null;

        if(isDepartmentFilled && !departmentRepository.existsById(department.getId())) {
            throw new BadRequestException(
                    String.format("Подразделения с id %d не существует", department.getId())
            );
        }

        if(isPositionFilled && !positionRepository.existsById(position.getId())) {
            throw new BadRequestException(
                    String.format("Должности с id %d не существует", position.getId())
            );
        }

        if(isImageFilled && !imageRepository.existsById(image.getId())) {
            throw new BadRequestException(
                    String.format("Изображения с id %d не существует", image.getId())
            );
        }

        for(RoleDto role : roles) {
            boolean isRoleFilled = role != null && role.getId() != null;

            if(isRoleFilled && !roleRepository.existsById(role.getId())) {
                throw new BadRequestException(
                        String.format("Роли с id %d не существует.", role.getId())
                );
            }
        }
    }
}
