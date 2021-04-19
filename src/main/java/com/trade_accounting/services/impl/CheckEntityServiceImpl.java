package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.BadRequestException;
import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.repositories.ContractorGroupRepository;
import com.trade_accounting.repositories.CurrencyRepository;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.repositories.TaskCommentRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.repositories.UnitRepository;
import com.trade_accounting.repositories.WarehouseRepository;
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
    private final WarehouseRepository warehouseRepository;
    private final TaskRepository taskRepository;
    private final TaskCommentRepository commentRepository;
    private final ContractorGroupRepository contractorGroupRepository;
    private final CurrencyRepository currencyRepository;
    private final RetailStoreRepository retailStoreRepository;

    public CheckEntityServiceImpl(UnitRepository unitRepository,
                                  EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
                                  PositionRepository positionRepository,
                                  ImageRepository imageRepository,
                                  RoleRepository roleRepository,
                                  WarehouseRepository warehouseRepository,
                                  TaskRepository taskRepository,
                                  TaskCommentRepository commentRepository, ContractorGroupRepository contractorGroupRepository, CurrencyRepository currencyRepository,
                                  RetailStoreRepository retailStoreRepository) {
        this.unitRepository = unitRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.imageRepository = imageRepository;
        this.roleRepository = roleRepository;
        this.warehouseRepository = warehouseRepository;
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.contractorGroupRepository = contractorGroupRepository;
        this.currencyRepository = currencyRepository;
        this.retailStoreRepository = retailStoreRepository;
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
            throw new NotFoundEntityException("Сотрудника с id=" + employeeId + ", не найдено");
        }
    }

    @Override
    public void checkExistsWarehouseById(Long warehouseId) {
        if(!warehouseRepository.existsById(warehouseId)) {
            throw new NotFoundEntityException("Склада с id=" + warehouseId + ", не найдено");
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
        boolean rolesFilled = roles != null && !roles.isEmpty();

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

        if(rolesFilled) {
            for (RoleDto role : roles) {
                boolean isRoleFilled = role != null && role.getId() != null;

                if (isRoleFilled && !roleRepository.existsById(role.getId())) {
                    throw new BadRequestException(
                            String.format("Роли с id %d не существует.", role.getId())
                    );
                }
            }
        }
    }

    @Override
    public void checkExistsTaskById(Long taskId) {
        if(!taskRepository.existsById(taskId)) {
            throw new NotFoundEntityException("Задача с id=" + taskId + ", не найдена");
        }
    }

    @Override
    public void checkExistsTaskCommentById(Long taskCommentId) {
        if(!commentRepository.existsById(taskCommentId)) {
            throw new NotFoundEntityException("Комментарий с id=" + taskCommentId + ", не найден");
        }
    }

    @Override
    public void checkExistsContractorGroupById(Long contractorGroupId) {
        if(!contractorGroupRepository.existsById(contractorGroupId)) {
            throw new NotFoundEntityException("Группа контрагентов с id=" + contractorGroupId + " не найдена");
        }
    }

    @Override
    public void checkExistsCurrencyById(Long currencyId) {
        if(!currencyRepository.existsById(currencyId)) {
            throw new NotFoundEntityException("Валюта с id=" + currencyId + ", не найдена");
        }
    }

    @Override
    public void checkExistsRetailStoreById(Long retailStoreId) {
        if(!retailStoreRepository.existsById(retailStoreId)) {
            throw new NotFoundEntityException("Точка продаж с id=" + retailStoreId + ", не найдена");
        }
    }
}
