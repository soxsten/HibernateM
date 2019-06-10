package com;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class Logger {
    public void logAroundAllMethods(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch timer = new StopWatch();
        timer.start();
        pjp.proceed();
        timer.stop();
        String methodName = pjp.getSignature().getName();
        System.out.println("Total time is: " + timer.getTotalTimeSeconds() + " for method: " + methodName);
    }
}
