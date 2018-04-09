package com.lsouza.wallet.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
	
	@AfterReturning("execution(* com.lsouza.wallet..*.*(..))")
	public void logAfterReturning(JoinPoint joinPoint) {
		log.info("Completed: " + joinPoint.toShortString());
	}
	
	@Before("execution(* com.lsouza.wallet..*.*(..))")
	public void logBeforeExecution(JoinPoint joinPoint) {
		log.info("Started: " + joinPoint.toShortString());
	}
}
