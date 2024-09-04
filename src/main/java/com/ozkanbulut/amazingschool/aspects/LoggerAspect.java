package com.ozkanbulut.amazingschool.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Slf4j
@Aspect

public class LoggerAspect {


    @Around("execution(* com.ozkanbulut.amazingschool..*.*(..))")
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

    @AfterThrowing(value = "execution(* com.ozkanbulut.amazingschool.*.*(..))", throwing = "e")
    public void logException(ProceedingJoinPoint joinPoint, Exception e){

        log.error(joinPoint.getSignature() + " An exception happened due to: "+ e.getMessage());
    }
}

