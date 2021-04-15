package com.trade_accounting.aspects;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingPointcuts {

        @Pointcut("within(com.trade_accounting.services..*)")
        public void inServiceLayer() {}

        @Pointcut("execution(* getAll())")
        public void getAllExecution() {}

        @Pointcut("execution(* getById(..))")
        public void getByIdExecution() {}

        @Pointcut("execution(* create(..))")
        public void createExecution() {}

        @Pointcut("execution(* update(..))")
        public void updateExecution() {}

        @Pointcut("execution(* deleteById(..))")
        public void deleteExecution() {}

        @After("inServiceLayer() && getAllExecution()")
        public void logGetAll(JoinPoint joinPoint) {
                log.info("Запрошен список {}", getDtoName(joinPoint));
        }

        @After(value = "inServiceLayer() && getByIdExecution() && args(id)")
        public void logGetById(JoinPoint joinPoint, Long id) {
                log.info("Запрошен экземпляр {} с id={}", getDtoName(joinPoint), id);
        }

        @AfterReturning(value = "inServiceLayer() && createExecution()",  returning = "dto")
        public void logCreate(JoinPoint joinPoint, Object dto) {
                if(dto == null) {
                        log.info("Создан экземпляр {}", getDtoName(joinPoint));
                } else {
                        log.info("Создан экземпляр {}: {}", dto.getClass().getSimpleName(), dto);
                }

        }

        @After(value = "inServiceLayer() && updateExecution() && args(dto)")
        public void logUpdate(JoinPoint joinPoint, Object dto) {
                log.info("Обновлен экземпляр {} с id={}", dto.getClass().getSimpleName(), getId(dto));
        }

        @After(value = "inServiceLayer() && deleteExecution() && args(id)")
        public void logDelete(JoinPoint joinPoint, Long id) {
                log.info("Удален экземпляр {} с id={}", getDtoName(joinPoint), id);
        }

        private String getDtoName(JoinPoint joinPoint) {
                var serviceName = joinPoint.getTarget().getClass().getSimpleName();
                return serviceName.replaceFirst("ServiceImpl", "Dto") ;
        }

        @SneakyThrows
        private Long getId(Object dto) {
                return (Long) dto.getClass().getMethod("getId", new Class<?>[]{})
                                .invoke(dto, new Object[]{});
        }
}
