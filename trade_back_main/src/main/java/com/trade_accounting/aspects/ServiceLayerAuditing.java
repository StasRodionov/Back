package com.trade_accounting.aspects;

import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.services.impl.client.EmployeeDetailsServiceImpl;
import com.trade_accounting.utils.translator.Translator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Slf4j
@Component
public class ServiceLayerAuditing extends ServiceLayerAspect {

    private final EmployeeDetailsServiceImpl employeeDetailsService;
    private final Translator translator;
    private final JmsTemplate jmsTemplate;
    private final Queue queue;

    public ServiceLayerAuditing(EmployeeDetailsServiceImpl employeeDetailsService, Translator translator, JmsTemplate jmsTemplate, @Qualifier("auditCreateOrUpdateQueue") Queue queue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
        this.employeeDetailsService = employeeDetailsService;
        this.translator = translator;
    }

    /**
     * Advices
     */

    @AfterReturning(value = "inServiceLayer() && createExecution() && args(dto)")
    public void auditCreate(Object dto) {
        String clazz = dto.getClass().getSimpleName().replace("Dto", "");
        String businessName = translator.translate("en", "ru", clazz);
        Employee currentEmployee;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
             currentEmployee = (Employee) employeeDetailsService.loadUserByUsername(email);
        } else {
            Employee principal = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
             currentEmployee = (Employee) employeeDetailsService.loadUserByUsername(principal.getEmail());
        }
        log.info("Поймано создание " + clazz);
        jmsTemplate.convertAndSend(queue,
                AuditDto.builder()
                .description("Создание " + businessName)
                .employeeId(currentEmployee.getId())
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .build()
        );
    }

    @AfterReturning(value = "inServiceLayer() && updateExecution() && args(dto)")
    public void auditUpdate(Object dto) {
        String clazz = dto.getClass().getSimpleName().replace("Dto", "");
        String businessName = translator.translate("en", "ru", clazz);
        Employee currentEmployee;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            currentEmployee = (Employee) employeeDetailsService.loadUserByUsername(email);
        } else {
            Employee principal = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            currentEmployee = (Employee) employeeDetailsService.loadUserByUsername(principal.getEmail());
        }
        log.info("Поймано изменение " + clazz);
        jmsTemplate.convertAndSend(queue,
                AuditDto.builder()
                        .description("Изменение " + businessName)
                        .employeeId(currentEmployee.getId())
                        .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                        .build()
        );
    }

    @AfterReturning(value = "inServiceLayer() && deleteExecution() && args(id)")
    public void auditDelete(JoinPoint joinPoint, Long id) {
        String clazz = joinPoint.getClass().getSimpleName().replace("Dto", "");
        String businessName = translator.translate("en", "ru", clazz);
        Employee currentEmployee;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            currentEmployee = (Employee) employeeDetailsService.loadUserByUsername(email);
        } else {
            Employee principal = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            currentEmployee = (Employee) employeeDetailsService.loadUserByUsername(principal.getEmail());
        }
        jmsTemplate.convertAndSend(queue,
                AuditDto.builder()
                        .description("Удаление " + businessName)
                        .employeeId(currentEmployee.getId())
                        .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                        .build()
        );
    }
}

