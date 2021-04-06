package com.trade_accounting.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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

        @Before("inServiceLayer() && getAllExecution()")
        public void getAllMethod(JoinPoint joinPoint) {
                var serviceName = joinPoint.getTarget().getClass().getSimpleName();
                var entityTypeName = serviceName.replaceFirst("ServiceImpl", "") + "Dto";
                log.info("Запрошен список {}", entityTypeName);
        }

        @Before(value = "inServiceLayer() && getByIdExecution() && args(id)")
        public void getByIdMethod(JoinPoint joinPoint, Long id) {
                var serviceName = joinPoint.getTarget().getClass().getSimpleName();
                var entityTypeName = serviceName.replaceFirst("ServiceImpl", "") + "Dto";
                log.info("Запрошен экземпляр {} с id = {}", entityTypeName, id);
        }

}
