package com.eazybytes.eazyschool.aspects;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Slf4j
@Aspect

public class LoggerAspect {


    @Around("execution(* com.eazybytes.eazyschool..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info(joinPoint.getSignature().getName() + "method execution start");
        Instant start = Instant.now();

        Object returnObj = joinPoint.proceed();
        Instant finish = Instant.now();

        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("Time took to execute " + joinPoint.getSignature() + " method is: " + timeElapsed);
        log.info(joinPoint.getSignature() + " method execution end");

        return returnObj;
    }

    @AfterThrowing(value = "execution(* com.eazybytes.eazyschool.*.*(..))", throwing = "e")
    public void logException(ProceedingJoinPoint joinPoint, Exception e){

        log.error(joinPoint.getSignature() + " An exception happened due to: "+ e.getMessage());
    }
}

