package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.BadRequestException;
import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.DepartmentDto;
import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.PositionDto;
import com.trade_accounting.models.dto.RoleDto;
import com.trade_accounting.repositories.BankAccountRepository;
import com.trade_accounting.repositories.DepartmentRepository;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.repositories.LegalDetailRepository;
import com.trade_accounting.repositories.PositionRepository;
import com.trade_accounting.repositories.RoleRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CheckEntityServiceImpl implements CheckEntityService {
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final ImageRepository imageRepository;
    private final RoleRepository roleRepository;
    private final LegalDetailRepository legalDetailRepository;
    private final BankAccountRepository bankAccountRepository;

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

        if (isDepartmentFilled && !departmentRepository.existsById(department.getId())) {
            throw new BadRequestException(
                    String.format("Подразделения с id %d не существует", department.getId())
            );
        }

        if (isPositionFilled && !positionRepository.existsById(position.getId())) {
            throw new BadRequestException(
                    String.format("Должности с id %d не существует", position.getId())
            );
        }

        if (isImageFilled && !imageRepository.existsById(image.getId())) {
            throw new BadRequestException(
                    String.format("Изображения с id %d не существует", image.getId())
            );
        }

        if (rolesFilled) {
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
    public void checkForBadCompany(CompanyDto company) {

        if (company.getLegalDetailDtoId() != 0 &&
                legalDetailRepository.getById(company.getLegalDetailDtoId()) == null) {
            throw new BadRequestException(
                    String.format("Юридических деталей с id %d не существует", company.getLegalDetailDtoId())
            );
        }

        if (!company.getBankAccountDtoIds().isEmpty()) {
            company.getBankAccountDtoIds().stream()
                    .forEach(
                            id -> {
                                if (bankAccountRepository.getById(id) == null) {
                                    throw new BadRequestException(
                                            String.format("Банковского счёта с id %d не существует.", id));
                                }
                            }
                    );
        }
    }

    @Override
    public void checkExists(JpaRepository<Entity, Long> repository, Long id) {
        String repositoryName = repository.getClass().getInterfaces()[0].getSimpleName();

        if (!repository.existsById(id)) {
            throw new NotFoundEntityException(
                     "Запись с id=" + id + " не найдена в репозитории " + repositoryName
            );
        }
    }
}
