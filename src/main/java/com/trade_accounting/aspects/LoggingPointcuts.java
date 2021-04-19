package com.trade_accounting.aspects;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
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

        @Pointcut("execution(void create(..))")
        public void voidCreateExecution() {}

        @Pointcut("execution(* update(..))")
        public void updateExecution() {}

        @Pointcut("execution(* deleteById(..))")
        public void deleteExecution() {}

        @Pointcut("execution(* search(..))")
        public void searchExecution() {}

        @AfterReturning("inServiceLayer() && getAllExecution()")
        public void logGetAll(JoinPoint joinPoint) {
                log.info("Запрошен список {}", getDtoName(joinPoint));
        }

        @AfterReturning(value = "inServiceLayer() && getByIdExecution() && args(id)")
        public void logGetById(JoinPoint joinPoint, Long id) {
                log.info("Запрошен экземпляр {} с id={}", getDtoName(joinPoint), id);
        }

        @AfterReturning(value = "inServiceLayer() && createExecution()",  returning = "dto")
        public void logCreate(Object dto) {
                if(dto == null) {

                } else {
                        log.info("Создан экземпляр {}: {}", dto.getClass().getSimpleName(), dto);
                }
        }

        @AfterReturning(value = "inServiceLayer() && voidCreateExecution() && args(dto)")
        public void logVoidCreate(Object dto) {
                log.info("Создан экземпляр {}", dto.getClass().getSimpleName());
        }

        @AfterReturning(value = "inServiceLayer() && updateExecution() && args(dto)")
        public void logUpdate(Object dto) {
                log.info("Обновлен экземпляр {} с id={}", dto.getClass().getSimpleName(), getId(dto));
        }

        @AfterReturning(value = "inServiceLayer() && deleteExecution() && args(id)")
        public void logDelete(JoinPoint joinPoint, Long id) {
                log.info("Удален экземпляр {} с id={}", getDtoName(joinPoint), id);
        }

        @AfterReturning(value = "inServiceLayer() && searchExecution()",  returning = "result")
        public void logSearch(JoinPoint joinPoint, Object result) {
                if(result == null) {
                        log.info("Не найдено экземпляров {}", getDtoName(joinPoint));
                } else {
                        log.info("Найдены экземпляры {}: {}", getDtoName(joinPoint), result);
                }

        }

        private String getDtoName(JoinPoint joinPoint) {
                var serviceName = joinPoint.getTarget().getClass().getSimpleName();
                return serviceName.replaceFirst("ServiceImpl", "Dto") ;
        }

        @SneakyThrows
        private Long getId(Object dto) {
                return (Long) dto.getClass().getMethod("getId").invoke(dto, new Object[]{});
        }
}
