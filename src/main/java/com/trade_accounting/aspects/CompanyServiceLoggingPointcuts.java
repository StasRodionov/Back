package com.trade_accounting.aspects;

import com.trade_accounting.models.Company;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CompanyServiceLoggingPointcuts {
    @Pointcut("within(com.trade_accounting.services.impl.CompanyServiceImpl)")
    public void companyService() {}

    @Pointcut("execution(* search(..))")
    public void searchExecution() {}

    @Pointcut("execution(* getByEmail(..))")
    public void findByEmailExecution() {}

    @After("companyService() && searchExecution() && args(spec)")
    public void logSearch(JoinPoint joinPoint, Specification<Company> spec) {
        log.info("Запрошен поиск CompanyDto в соответсвии с критериями: {}", spec.toString());
    }
    @After("companyService() && findByEmailExecution() && args(email)")
    public void logGetByEmail(JoinPoint joinPoint, String email) {
        log.info("Запрошен  CompanyDto c email: {}", email);
    }
}
