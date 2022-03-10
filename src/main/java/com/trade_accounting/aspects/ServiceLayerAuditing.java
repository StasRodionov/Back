package com.trade_accounting.aspects;

import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.services.impl.client.EmployeeDetailsServiceImpl;
import com.trade_accounting.services.interfaces.indicators.audit.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Slf4j
@Component
public class ServiceLayerAuditing extends ServiceLayerAspect {

    private final AuditService auditService;
    private final EmployeeDetailsServiceImpl employeeDetailsService;

    public ServiceLayerAuditing(AuditService auditService, EmployeeDetailsServiceImpl employeeDetailsService) {
        this.auditService = auditService;
        this.employeeDetailsService = employeeDetailsService;
    }

    /**
     * pointcuts
     */

    @Pointcut("within(com.trade_accounting.services.impl.indicators.audit..*)")
    public void auditService() {
    }


    /**
     * Advices
     */

    @AfterReturning(value = "inServiceLayer() && createExecution() && args(dto) && !auditService()")
    public void auditCreate(Object dto) {
        String clazz = dto.getClass().getSimpleName().replace("Dto", "");
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee currentEmployee = (Employee) employeeDetailsService.loadUserByUsername(email);
        auditService.create(AuditDto.builder()
                .description("Создание " + clazz)
                .employeeId(currentEmployee.getId())
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .build()
        );
    }

    @AfterReturning(value = "inServiceLayer() && updateExecution() && args(dto) && !auditService()")
    public void auditUpdate(Object dto) {
        String clazz = dto.getClass().getSimpleName().replace("Dto", "");
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee currentEmployee = (Employee) employeeDetailsService.loadUserByUsername(email);
        auditService.create(AuditDto.builder()
                .employeeId(currentEmployee.getId())
                .description("Обновление объекта " + clazz)
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .build()
        );
    }

    @AfterReturning(value = "inServiceLayer() && deleteExecution() && args(id) && !auditService()")
    public void auditDelete(JoinPoint joinPoint, Long id) {
        String clazz = joinPoint.getClass().getSimpleName().replace("Dto", "");
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee currentEmployee = (Employee) employeeDetailsService.loadUserByUsername(email);
        auditService.create(AuditDto.builder()
                .description("Удаление " + clazz)
                .employeeId(currentEmployee.getId())
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .build()
        );
    }
}
