package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

public interface CheckEntityService {

    void checkForBadEmployee(EmployeeDto employee);

    void checkForBadCompany(CompanyDto company);

    void checkExists(JpaRepository<Entity, Long> repository, Long id);
}
