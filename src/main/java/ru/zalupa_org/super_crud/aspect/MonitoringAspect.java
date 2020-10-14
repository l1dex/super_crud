package ru.zalupa_org.super_crud.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonitoringAspect {
    @Around("@annotation(Monitoring)")
    public Object monitoring(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        System.out.println("Method :" + joinPoint.getSignature().getName() + " called\n" + "with args :" );
        for(Object o : joinPoint.getArgs()){
            System.out.print(o + " ");
        }
        System.out.println("Execution time = " + executionTime);
        return proceed;
    }
}
